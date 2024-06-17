package edu.bu.met673.usermanagement.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ErrorResponseTest {
    /**
     * Method under test:
     * {@link ErrorResponse#ErrorResponse(HttpStatus, ErrorType, String, String)}
     */
    @Test
    void testNewErrorResponse() {
        // Arrange and Act
        ErrorResponse actualErrorResponse = new ErrorResponse(HttpStatus.CONTINUE, ErrorType.FUNCTIONAL, "Code",
                "test code to get it working");

        // Assert
        assertEquals("Code", actualErrorResponse.getCode());
        assertEquals("test code to get it working", actualErrorResponse.getMessage());
        assertEquals(ErrorType.FUNCTIONAL, actualErrorResponse.getErrorType());
        assertEquals(HttpStatus.CONTINUE, actualErrorResponse.getHttpStatus());
    }
}
