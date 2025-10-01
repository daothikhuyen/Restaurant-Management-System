package RMS.com.example.RMS.common.expection;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi chưa được phân loại", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Lỗi chưa được phân loại", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Tài khoản đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_FORBIDDEN(1003, "Tài khoản đã bị xoá hoặc bị khoá!", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Mật khẩu không đúng", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "Tài khoản không tồn tại", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    INVALID_PASSWORDS(1008, "Mật khẩu không đúng", HttpStatus.BAD_REQUEST),
    FILE_NOT_EXISTED(1009,"Không thể lưu trữ file trống",HttpStatus.NOT_FOUND),
    POST_NOT_EXISTED(1010,"Bài đăng không tồn tại",HttpStatus.NOT_FOUND),
    FEEDBACK_NOT_EXISTED(1011, "Bình luận không tồn tại", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1012, "Mã Không đúng định dạng",HttpStatus.BAD_REQUEST),
    DATA_EXISTED(1013, "Dữ liệu đã tồn tại", HttpStatus.CONFLICT),
    DATABASE_ERROR(1014, "Không thể lưu bản", HttpStatus.INTERNAL_SERVER_ERROR);


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}

