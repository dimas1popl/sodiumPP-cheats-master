package com.dimsteams.sodiumpp.font;

public class CannotLoadFontException extends RuntimeException {

    public CannotLoadFontException(String message) {
        super(message);
    }

    public CannotLoadFontException(Throwable cause) {
        super(cause);
    }
}