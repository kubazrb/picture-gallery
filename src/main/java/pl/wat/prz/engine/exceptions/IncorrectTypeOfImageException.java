package pl.wat.prz.engine.exceptions;

import java.io.IOException;

public class IncorrectTypeOfImageException extends IOException {

    @Override
    public String getMessage() {
        return "incorrect-image-format";
    }
}
