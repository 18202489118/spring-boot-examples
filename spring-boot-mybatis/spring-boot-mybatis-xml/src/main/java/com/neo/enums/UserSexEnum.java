package com.neo.enums;

public enum UserSexEnum implements CodeBaseEnum {
    MAN(1, "男"),
    WOMAN(2, "女"),
    UNKOWN(0, "未知");

    final private int code;
    final private String desc;

    UserSexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int code() {
        return code;
    }
}
