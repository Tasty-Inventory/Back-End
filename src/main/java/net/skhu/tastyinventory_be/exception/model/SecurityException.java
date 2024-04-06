package net.skhu.tastyinventory_be.exception.model;

import net.skhu.tastyinventory_be.exception.ErrorCode;

public class SecurityException extends CustomException {
    public SecurityException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
