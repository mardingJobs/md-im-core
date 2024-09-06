package cn.md.im.core.common.model;

public enum IMMsgType {
    LOGIN(0, "登录"),
    HEART_BEAT(1, "心跳"),
    FORCE_LOGUT(2, "强制下线"),
    PRIVATE_MESSAGE(3, "私聊消息"),
    GROUP_MESSAGE(4, "群发消息");

    private final Integer code;
    private final String desc;

    private IMMsgType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IMMsgType fromCode(Integer code) {
        IMMsgType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            IMMsgType typeEnum = var1[var3];
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }

        return null;
    }

    public Integer code() {
        return this.code;
    }
}