package net.skhu.tastyinventory_be.exception.model;

import net.skhu.tastyinventory_be.exception.ErrorCode;

public class OAuth2NotSupportException extends CustomException {
    public OAuth2NotSupportException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
