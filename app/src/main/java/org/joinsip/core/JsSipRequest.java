package org.joinsip.core;

public final class JsSipRequest {
    public static final org.joinsip.core.JsSipRequest ACK = new org.joinsip.core.JsSipRequest("ACK");
    public static final org.joinsip.core.JsSipRequest BYE = new org.joinsip.core.JsSipRequest("BYE");
    public static final org.joinsip.core.JsSipRequest CANCEL = new org.joinsip.core.JsSipRequest("CANCEL");
    public static final org.joinsip.core.JsSipRequest INFO = new org.joinsip.core.JsSipRequest("INFO");
    public static final org.joinsip.core.JsSipRequest INVITE = new org.joinsip.core.JsSipRequest("INVITE");
    public static final org.joinsip.core.JsSipRequest MESSAGE = new org.joinsip.core.JsSipRequest("MESSAGE");
    public static final org.joinsip.core.JsSipRequest NOTIFY = new org.joinsip.core.JsSipRequest("NOTIFY");
    public static final org.joinsip.core.JsSipRequest OPTIONS = new org.joinsip.core.JsSipRequest("OPTIONS");
    public static final org.joinsip.core.JsSipRequest PRACK = new org.joinsip.core.JsSipRequest("PRACK");
    public static final org.joinsip.core.JsSipRequest PUBLISH = new org.joinsip.core.JsSipRequest("PUBLISH");
    public static final org.joinsip.core.JsSipRequest REFFER = new org.joinsip.core.JsSipRequest("REFFER");
    public static final org.joinsip.core.JsSipRequest REGISTER = new org.joinsip.core.JsSipRequest("REGISTER");
    public static final org.joinsip.core.JsSipRequest SUBSCRIBE = new org.joinsip.core.JsSipRequest("SUBSCRIBE");
    public static final org.joinsip.core.JsSipRequest UPDATE = new org.joinsip.core.JsSipRequest("UPDATE");
    private final String name;

    private JsSipRequest(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
