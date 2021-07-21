package org.joinsip.usipserver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.joinsip.core.JsConsole;
import org.joinsip.core.transport.JsUdpEventSource;
import org.joinsip.impl.proxy.JsSimpleRegisterInfo;
import org.joinsip.impl.proxy.JsSimpleRegisterPersistenceLogic;
import org.joinsip.impl.proxy.JsSipProxyService;
import org.org.joinsip.usipserver.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Hashtable;

public class USipServerService extends Service implements JsConsole, JsSimpleRegisterPersistenceLogic {
    public static final String INTENT_ACTION = "SSS_BROADCAST";
    public static final String INTENT_KEY_REGISTER = "SSS_REGISTER";
    public static final String INTENT_KEY_REGLOG = "SSS_REGLOG";
    public static final String INTENT_KEY_SIPLOG = "SSS_SIPLOG";
    public static final String INTENT_KEY_STATUS = "SSS_STATUS";
    static final String REG_DATA_FILENAME = "reg_data.txt";
    static final String TAG = "SSS";

    // Notification
    public static int AIS_SIP_NOTIFICATION_ID = 1234;
    public static final String AIS_SIP_CHANNEL_ID = "AisSipServiceChannelId";
    Notification mAisServiceNotification;

    public final JsSipProxyService disp = new JsSipProxyService();
    private String domain = null;
    private String localIp = null;
    private int localPort = 0;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(org.joinsip.usipserver.USipServerService.INTENT_ACTION)) {
                boolean flag;
                if (intent.hasExtra(org.joinsip.usipserver.USipServerService.INTENT_KEY_SIPLOG)) {
                    flag = intent.getBooleanExtra(org.joinsip.usipserver.USipServerService.INTENT_KEY_SIPLOG, false);
                    if (flag) {
                        org.joinsip.usipserver.USipServerService.this.disp.out("[Set SipLog: On]");
                    } else {
                        org.joinsip.usipserver.USipServerService.this.disp.out("[Set SipLog: Off]");
                    }
                    org.joinsip.usipserver.USipServerService.this.disp.setLogEnable(flag);
                }
                if (intent.hasExtra(org.joinsip.usipserver.USipServerService.INTENT_KEY_REGLOG)) {
                    flag = intent.getBooleanExtra(org.joinsip.usipserver.USipServerService.INTENT_KEY_REGLOG, false);
                    if (flag) {
                        org.joinsip.usipserver.USipServerService.this.disp.out("[Set RegisterLog: On]");
                    } else {
                        org.joinsip.usipserver.USipServerService.this.disp.out("[Set RegisterLog: Off]");
                    }
                    org.joinsip.usipserver.USipServerService.this.disp.registrar.setLogEnable(flag);
                }
                if (intent.hasExtra(org.joinsip.usipserver.USipServerService.INTENT_KEY_STATUS)) {
                    org.joinsip.usipserver.USipServerService.this.disp.out(org.joinsip.usipserver.USipServerService.this.disp.showStatus());
                    if (org.joinsip.usipserver.USipServerService.this.regDataPersistence) {
                        org.joinsip.usipserver.USipServerService.this.disp.out("  Register Data Persistence: On");
                    }
                }
                if (intent.hasExtra(org.joinsip.usipserver.USipServerService.INTENT_KEY_REGISTER)) {
                    org.joinsip.usipserver.USipServerService.this.disp.out(org.joinsip.usipserver.USipServerService.this.disp.showRegister());
                }
            }
        }
    };
    private boolean regDataPersistence = false;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.disp.getRegistrar().setPersistenceLogic(this);
        registerReceiver(this.receiver, new IntentFilter(INTENT_ACTION));
        Log.d(TAG, "onCreate");
        //
    }

    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String domain = intent.getStringExtra("pref_domain");
            String localIp = intent.getStringExtra("pref_localip");
            int localPort = intent.getIntExtra("pref_localport", 5060);
            boolean regDataPersistence = intent.getBooleanExtra("pref_register_data_persistence", false);

            createNotificationChannel();

            Intent goToAppView = new Intent(getApplicationContext(), USipServerActivity.class);
            int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
            goToAppView.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent goToApp = PendingIntent.getActivity(getApplicationContext(), iUniqueId, goToAppView, PendingIntent.FLAG_UPDATE_CURRENT);

            mAisServiceNotification = new NotificationCompat.Builder(this, AIS_SIP_CHANNEL_ID)
                    .setContentTitle("AIS SIP")
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setContentText("@" + localIp)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setSound(null)
                    .setContentIntent(goToApp)
                    .build();

            startForeground(AIS_SIP_NOTIFICATION_ID, mAisServiceNotification);

            if (startServer(domain, localIp, localPort, regDataPersistence)) {}
            if (!regDataPersistence) {
                File file = getFileStreamPath(REG_DATA_FILENAME);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        if (this.regDataPersistence) {
            this.disp.getRegistrar().setPersistenceLogic(this);
        }
        return 0;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    AIS_SIP_CHANNEL_ID,
                    "AI-Speaker SIP",
                    NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.receiver);
        stopServer();
        stopForeground(true);
        Log.d(TAG, "onDestroy()");
    }

    public boolean startServer(String domain, String localIp, int localPort, boolean regDataPersistence) {
        this.domain = null;
        this.localIp = null;
        this.localPort = 0;
        this.regDataPersistence = false;
        if (this.disp.isRunning()) {
            return false;
        }
        this.disp.setLogEnable(false);
        this.disp.registrar.setLogEnable(false);
        if (domain == null || domain.trim().length() == 0) {
            domain = "127.0.0.1";
            try {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    Enumeration<InetAddress> addresses = ((NetworkInterface) interfaces.nextElement()).getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        String address = ((InetAddress) addresses.nextElement()).getHostAddress();
                        if (!address.equals("127.0.0.1") && !address.contains(":")) {
                            domain = address;
                            break;
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        out("[Start SipServer]");
        out("  Domain: " + domain);
        this.disp.setConsoleOut(this);
        JsUdpEventSource udpEvent = new JsUdpEventSource();
        udpEvent.setConsoleOut(this);
        this.disp.registEventSource(udpEvent);
        this.disp.setSipDomain(domain, localPort);
        final String tmpLocalIp = localIp;
        final int tmpLocalPort = localPort;
        Thread th = new Thread(new Runnable() {
            public void run() {
                try {
                    org.joinsip.usipserver.USipServerService.this.disp.start(tmpLocalIp, tmpLocalPort);
                    org.joinsip.usipserver.USipServerService.this.out("  LocalIp: " + org.joinsip.usipserver.USipServerService.this.disp.getEventSource().getMyHost() + "\n  LocalPort: " + org.joinsip.usipserver.USipServerService.this.disp.getEventSource().getMyPort());
                    Thread.currentThread().setName("OK");
                } catch (Exception e) {
                    e.printStackTrace();
                    org.joinsip.usipserver.USipServerService.this.err("! " + e.getClass().getSimpleName());
                    String emsg = e.getMessage();
                    if (emsg != null && emsg.length() > 0) {
                        org.joinsip.usipserver.USipServerService.this.err(emsg);
                    }
                    org.joinsip.usipserver.USipServerService.this.disp.stop();
                    Thread.currentThread().setName("NG");
                }
            }
        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
        }
        if (!th.getName().equals("OK")) {
            return false;
        }
        this.domain = domain;
        this.localIp = localIp;
        this.localPort = localPort;
        this.regDataPersistence = regDataPersistence;
        return true;
    }

    public void stopServer() {
        if (this.disp.isRunning()) {
            out("[Stop SipServer]");
            Thread th = new Thread(new Runnable() {
                public void run() {
                    org.joinsip.usipserver.USipServerService.this.disp.stop();
                }
            });
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
            }
            if (!this.regDataPersistence) {
                File file = getFileStreamPath(REG_DATA_FILENAME);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public void setSipLogEnable(boolean flag) {
        if (this.disp != null) {
            this.disp.setLogEnable(flag);
        }
    }

    public void setRegisterLogEnable(boolean flag) {
        if (this.disp != null) {
            this.disp.registrar.setLogEnable(flag);
        }
    }

    public String getServerInfo() {
        return "sip:****@" + this.domain + " [" + this.localIp + ":" + this.localPort + "]";
    }

    public void out(String text) {
        Intent intent = new Intent(USipServerActivity.INTENT_ACTION);
        intent.putExtra(USipServerActivity.INTENT_KEY, text);
        sendBroadcast(intent);
    }

    public void err(String text) {
        Intent intent = new Intent(USipServerActivity.INTENT_ACTION);
        intent.putExtra(USipServerActivity.INTENT_KEY, text);
        sendBroadcast(intent);
    }

    public boolean writeRegisterTable(Hashtable<String, JsSimpleRegisterInfo> regTable) {
        if (this.regDataPersistence) {
            try {
                FileOutputStream fos = openFileOutput(REG_DATA_FILENAME, 0);
                for (String key : regTable.keySet()) {
                    JsSimpleRegisterInfo info = (JsSimpleRegisterInfo) regTable.get(key);
                    if (!info.isExpired()) {
                        fos.write(info.toString().getBytes());
                        fos.write(10);
                    }
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean readRegisterTable(Hashtable<String, JsSimpleRegisterInfo> regTable) {
        if (this.regDataPersistence) {
            try {
                this.disp.out("[Register Data Persistence]");
                int registerDataCount = 0;
                if (getFileStreamPath(REG_DATA_FILENAME).exists()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(REG_DATA_FILENAME)));
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        JsSimpleRegisterInfo info = JsSimpleRegisterInfo.parseLine(line);
                        if (!(info == null || info.isExpired())) {
                            regTable.put(info.getToUri().getUser(), info);
                            registerDataCount++;
                        }
                    }
                    br.close();
                }
                if (registerDataCount > 0) {
                    this.disp.out("  Recovered " + registerDataCount + " Register Data.");
                } else {
                    this.disp.out("  No Persistence Data.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
