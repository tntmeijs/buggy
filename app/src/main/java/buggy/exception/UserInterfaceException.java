package buggy.exception;

import lombok.NonNull;

public final class UserInterfaceException extends RuntimeException {

    /**
     * Runtime exception thrown when a non-recoverable user interface related error occurs
     *
     * @param message Message with more details about the error
     */
    public UserInterfaceException(@NonNull final String message) {
        super(message);
    }

}
