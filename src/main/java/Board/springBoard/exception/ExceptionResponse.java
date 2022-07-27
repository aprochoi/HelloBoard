package Board.springBoard.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ExceptionResponse(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getHttpStatus().value();
        this.error = exceptionCode.getHttpStatus().name();
        this.code = exceptionCode.name();
        this.message = exceptionCode.getMessage();
    }
}
