package org.enriqueboronat.pruebahexagonal.config.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    public NotFoundException(String msg) {
        super(msg);
    }
}
