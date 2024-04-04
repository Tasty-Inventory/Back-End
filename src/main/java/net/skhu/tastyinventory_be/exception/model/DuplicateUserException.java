package net.skhu.tastyinventory_be.exception.model;

import net.skhu.tastyinventory_be.exception.ErrorCode;

public class DuplicateUserException extends CustomException {
    public DuplicateUserException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
