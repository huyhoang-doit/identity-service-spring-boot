package com.hdi.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXEPTION(9999,"Uncategorized Exception Error" ),
    INVALID_KEY(9998,"Invalid Key Error" ),
    USER_EXISTED(1001, "User existed"),
    USERNAME_INVALID(1003, "Username is invalid"),
    PASSWORD_INVALID(1004, "Password is invalid"),

    ;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
