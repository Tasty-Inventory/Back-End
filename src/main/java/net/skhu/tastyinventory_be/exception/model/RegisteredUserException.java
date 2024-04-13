package net.skhu.tastyinventory_be.exception.model;

import net.skhu.tastyinventory_be.exception.ErrorCode;

public class RegisteredUserException extends CustomException {
    public RegisteredUserException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
