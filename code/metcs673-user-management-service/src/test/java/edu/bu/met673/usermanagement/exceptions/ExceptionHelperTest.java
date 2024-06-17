package edu.bu.met673.usermanagement.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExceptionHelperTest {
    /**
     * Method under test: {@link ExceptionHelper#getStackTrace(Throwable)}
     */
    @Test
    void testGetStackTrace() {
        // Arrange, Act and Assert
        assertEquals("", ExceptionHelper.getStackTrace(null));
    }

    /**
     * Method under test: {@link ExceptionHelper#getException(Throwable)}
     */
    @Test
    void testGetException() {
        // Arrange, Act and Assert
        assertEquals("java.lang.Throwable", ExceptionHelper.getException(new Throwable()));
        assertEquals("", ExceptionHelper.getException(null));
    }
}
