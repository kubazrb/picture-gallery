package pl.wat.prz.engine.exceptions;

import java.io.IOException;

public class IncorrectResolutionOfImageException extends IOException {

    @Override
    public String getMessage() {
        return "incorrect-image-res";
    }
}
