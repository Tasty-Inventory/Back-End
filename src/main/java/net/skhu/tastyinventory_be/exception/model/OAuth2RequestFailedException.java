package net.skhu.tastyinventory_be.exception.model;

import net.skhu.tastyinventory_be.exception.ErrorCode;

public class OAuth2RequestFailedException extends CustomException {
    public OAuth2RequestFailedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
