package RMS.com.example.RMS.common.expection;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already exists", HttpStatus.BAD_REQUEST),
    USER_FORBIDDEN(1003, "User has been deleted or blocked", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Incorrect password", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have access permission", HttpStatus.FORBIDDEN),
    INVALID_PASSWORDS(1008, "Incorrect password", HttpStatus.BAD_REQUEST),
    FILE_NOT_EXISTED(1009, "Cannot store empty file", HttpStatus.NOT_FOUND),
    POST_NOT_EXISTED(1010, "Post does not exist", HttpStatus.NOT_FOUND),
    FEEDBACK_NOT_EXISTED(1011, "Feedback does not exist", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1012, "Invalid token format", HttpStatus.BAD_REQUEST),
    DATA_EXISTED(1013, "Data already exists", HttpStatus.CONFLICT),
    DATABASE_ERROR(1014, "Cannot save record", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND(1015,"No data found",HttpStatus.NOT_FOUND  );

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}

