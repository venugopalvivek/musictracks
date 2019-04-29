package com.swiggy.vivek.exceptions;

public class MusicTracksException extends Exception {

    private int code;

    public MusicTracksException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MusicTracksException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
