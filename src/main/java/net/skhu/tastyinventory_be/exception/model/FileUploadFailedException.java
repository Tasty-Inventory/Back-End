package net.skhu.tastyinventory_be.exception.model;

import net.skhu.tastyinventory_be.exception.ErrorCode;

public class FileUploadFailedException extends CustomException {
    public FileUploadFailedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
