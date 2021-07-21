package org.joinsip.core;

import org.joinsip.common.JsUtil;

import java.lang.reflect.Constructor;

public final class JsHeader {
    public static final org.joinsip.core.JsHeader ACCEPT = new org.joinsip.core.JsHeader("Accept", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader ACCEPT_ENCODING = new org.joinsip.core.JsHeader("Accept-Encoding", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader ACCEPT_LANGUAGE = new org.joinsip.core.JsHeader("Accept-Language", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader ALERT_INFO = new org.joinsip.core.JsHeader("Alert-Info", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader ALLOW = new org.joinsip.core.JsHeader("Allow", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader ALLOW_EVENTS = new org.joinsip.core.JsHeader("Allow-Events", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader AUTHENTICATION_INFO = new org.joinsip.core.JsHeader("Authentication-Info", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader AUTHORIZATION = new org.joinsip.core.JsHeader("Authorization", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader CALL_ID = new org.joinsip.core.JsHeader("Call-ID", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader CALL_INFO = new org.joinsip.core.JsHeader("Call-Info", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader CONTACT = new org.joinsip.core.JsHeader("Contact", JsHeaderAddressData.class);
    public static final org.joinsip.core.JsHeader CONTENT_DISPOSITION = new org.joinsip.core.JsHeader("Content-Disposition", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader CONTENT_ENCODING = new org.joinsip.core.JsHeader("Content-Encoding", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader CONTENT_LANGUAGE = new org.joinsip.core.JsHeader("Content-Language", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader CONTENT_LENGTH = new org.joinsip.core.JsHeader("Content-Length", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader CONTENT_TYPE = new org.joinsip.core.JsHeader("Content-Type", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader CSEQ = new org.joinsip.core.JsHeader("CSeq", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader DATE = new org.joinsip.core.JsHeader("Date", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader ERROR_INFO = new org.joinsip.core.JsHeader("Error-Info", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader EVENT = new org.joinsip.core.JsHeader("Event", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader EXPIRES = new org.joinsip.core.JsHeader("Expires", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader FROM = new org.joinsip.core.JsHeader("From", JsHeaderAddressData.class);
    public static final org.joinsip.core.JsHeader IN_REPLY_TO = new org.joinsip.core.JsHeader("In-Reply-To", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader MAX_FORWARDS = new org.joinsip.core.JsHeader("Max-Forwards", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader MIME_VERSION = new org.joinsip.core.JsHeader("Mime-Version", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader MIN_EXPIRES = new org.joinsip.core.JsHeader("Min-Expires", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader MIN_SE = new org.joinsip.core.JsHeader("Min-SE", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader ORGANIZATION = new org.joinsip.core.JsHeader("Organization", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader PRIORITY = new org.joinsip.core.JsHeader("Priority", JsHeaderData.class);
    public static final String PROTCOL = "SIP/2.0";
    public static final org.joinsip.core.JsHeader PROXY_AUTHENTICATE = new org.joinsip.core.JsHeader("Proxy-Authenticate", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader PROXY_AUTHORIZATION = new org.joinsip.core.JsHeader("Proxy-Authorization", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader PROXY_REQUIRE = new org.joinsip.core.JsHeader("Proxy-Require", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader RACK = new org.joinsip.core.JsHeader("RAck", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader REASON = new org.joinsip.core.JsHeader("Reason", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader RECORD_ROUTE = new org.joinsip.core.JsHeader("Record-Route", JsHeaderAddressData.class);
    public static final org.joinsip.core.JsHeader REFERRED_BY = new org.joinsip.core.JsHeader("Referred-By", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader REPLACES = new org.joinsip.core.JsHeader("Replaces", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader REPLY_TO = new org.joinsip.core.JsHeader("Reply-To", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader REQUIRE = new org.joinsip.core.JsHeader("Require", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader RETRY_AFTER = new org.joinsip.core.JsHeader("Retry-After", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader ROUTE = new org.joinsip.core.JsHeader("Route", JsHeaderAddressData.class);
    public static final org.joinsip.core.JsHeader RSEQ = new org.joinsip.core.JsHeader("RSeq", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader SERVER = new org.joinsip.core.JsHeader("Server", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader SESSION_EXPIRES = new org.joinsip.core.JsHeader("Session-Expires", JsHeaderIntData.class);
    public static final org.joinsip.core.JsHeader SIP_ETAG = new org.joinsip.core.JsHeader("SIP-ETag", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader SIP_IF_MATCH = new org.joinsip.core.JsHeader("SIP-If-Match", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader SUBJECT = new org.joinsip.core.JsHeader("Subject", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader SUBSCRIPTION_STATE = new org.joinsip.core.JsHeader("Subscription-State", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader SUPPORTED = new org.joinsip.core.JsHeader("Supported", JsHeaderData.class);
    public static final String TCP = "TCP";
    public static final org.joinsip.core.JsHeader TIMESTAMP = new org.joinsip.core.JsHeader("Timestamp", JsHeaderIntData.class);
    public static final String TLS = "TLS";
    public static final org.joinsip.core.JsHeader TO = new org.joinsip.core.JsHeader("To", JsHeaderAddressData.class);
    public static final String UDP = "UDP";
    public static final org.joinsip.core.JsHeader UNSUPPORTED = new org.joinsip.core.JsHeader("Unsupported", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader USER_AGENT = new org.joinsip.core.JsHeader("User-Agent", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader VIA = new org.joinsip.core.JsHeader("Via", JsHeaderViaData.class);
    public static final org.joinsip.core.JsHeader WARNING = new org.joinsip.core.JsHeader("Warning", JsHeaderData.class);
    public static final org.joinsip.core.JsHeader WWW_AUTHENTICATE = new org.joinsip.core.JsHeader("WWW-Authenticate", JsHeaderData.class);
    private static final org.joinsip.core.JsHeader[] headers = new org.joinsip.core.JsHeader[]{VIA, RECORD_ROUTE, ROUTE, TO, FROM, CALL_ID, CONTACT, CSEQ, MAX_FORWARDS, CONTENT_LENGTH, CONTENT_TYPE, ALLOW, EXPIRES, USER_AGENT, REASON, CALL_INFO, ACCEPT, ACCEPT_ENCODING, ACCEPT_LANGUAGE, ALERT_INFO, ALLOW_EVENTS, AUTHENTICATION_INFO, AUTHORIZATION, CONTENT_DISPOSITION, CONTENT_ENCODING, CONTENT_LANGUAGE, DATE, ERROR_INFO, EVENT, IN_REPLY_TO, MIME_VERSION, MIN_EXPIRES, MIN_SE, ORGANIZATION, PRIORITY, PROXY_AUTHENTICATE, PROXY_AUTHORIZATION, PROXY_REQUIRE, RACK, REFERRED_BY, REPLACES, REPLY_TO, REQUIRE, RETRY_AFTER, RSEQ, SERVER, SESSION_EXPIRES, SIP_ETAG, SIP_IF_MATCH, SUBJECT, SUBSCRIPTION_STATE, SUPPORTED, TIMESTAMP, UNSUPPORTED, WARNING, WWW_AUTHENTICATE};
    private final Constructor<?> constructor;
    private final Object[] constructorParam = new Object[]{this};
    private final String name;

    private JsHeader(String name, Class<?> dataClass) {
        this.name = name;
        Constructor<?>[] constructors = dataClass.getConstructors();
        Constructor<?> tmpConstructor = null;
        for (Constructor<?> tmpConstructor2 : constructors) {
            if (tmpConstructor2.getParameterTypes().length == 1 && tmpConstructor2.getParameterTypes()[0].equals(org.joinsip.core.JsHeader.class)) {
               tmpConstructor = tmpConstructor2;
                break;
            }
        }
        this.constructor = tmpConstructor;
    }

    public String toString() {
        return this.name;
    }

    public JsHeaderData newInstance() {
        try {
            return (JsHeaderData) this.constructor.newInstance(this.constructorParam);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static org.joinsip.core.JsHeader getHeader(String headerName) {
        int count = headers.length;
        for (int index = 0; index < count; index++) {
            if (headerName.equalsIgnoreCase(headers[index].toString())) {
                return headers[index];
            }
        }
        return new org.joinsip.core.JsHeader(headerName, JsHeaderData.class);
    }

    public static JsHeaderData[] parse(String line) throws JsCoreException {
        int cIndex = line.indexOf(58);
        if (cIndex > 0) {
            org.joinsip.core.JsHeader header = getHeader(line.substring(0, cIndex).trim());
            String tmpValue = line.substring(cIndex + 1).trim();
            if (header != null) {
                JsHeaderData[] ret;
                if ((header == VIA || header == RECORD_ROUTE || header == ROUTE) && tmpValue.indexOf(44) >= 0) {
                    String[] tmpValues = JsUtil.split(tmpValue, ",");
                    ret = new JsHeaderData[tmpValues.length];
                    for (int index = 0; index < ret.length; index++) {
                        ret[index] = header.newInstance();
                        ret[index].setValue(tmpValues[index]);
                    }
                    return ret;
                }
                ret = new JsHeaderData[]{header.newInstance()};
                ret[0].setValue(tmpValue);
                return ret;
            }
        }
        return null;
    }
}
