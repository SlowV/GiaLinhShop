package com.slowv.fruit.web.errors;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public class BusinessException extends RuntimeException {

    private final int errorCode;
    private final String messageKey;

    public BusinessException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.messageKey = msg;
    }

}
