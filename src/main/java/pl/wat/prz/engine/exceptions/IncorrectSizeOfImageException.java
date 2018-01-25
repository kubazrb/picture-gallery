package pl.wat.prz.engine.exceptions;

import java.io.IOException;

public class IncorrectSizeOfImageException extends IOException {

    @Override
    public String getMessage() {
        return "incorrect-image-size";
    }
}
