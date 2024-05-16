package com.unicalsocial.backend.exception;

import java.util.NoSuchElementException;

public class UserNotInitializedException extends NoSuchElementException {
    public UserNotInitializedException(String roleUserWasNotInitiated) {
    }
}
