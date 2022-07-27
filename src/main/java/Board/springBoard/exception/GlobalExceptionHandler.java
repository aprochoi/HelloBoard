package Board.springBoard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // @ControllerAdvice + @ResponseBody 가 적용된 형태, 컨트롤러 전역에서 발생할수 있는 예외를 잡아 throw 한다.
@Slf4j  //롬복에서 제공하는 기능, 자동으로 로그 객체를 생성한다. log.error(), log.debug() 같이 로깅 관련 메서드를 사용할 수 있다.
public class GlobalExceptionHandler {

    // Custom Exception
    @ExceptionHandler(CustomException.class)   // 특정 클래스에서 발생할 수 있는 예외를 잡아 throw 한다.
    protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException : {} ", e.getExceptionCode());
        return ResponseEntity.status(e.getExceptionCode().getHttpStatus().value())
                .body(new ExceptionResponse(e.getExceptionCode()));
    }

    // HTTP 405 Exception
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException : {} ", e.getMessage());
        return ResponseEntity.status(ExceptionCode.METHOD_NOT_ALLOWED.getHttpStatus().value())
                .body(new ExceptionResponse(ExceptionCode.METHOD_NOT_ALLOWED));
    }

    // HTTP 500 Exception
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("handleException : {} ", e.getMessage());
        return ResponseEntity.status(ExceptionCode.INTERNAL_SERVER_ERROR.getHttpStatus().value())
                .body(new ExceptionResponse(ExceptionCode.INTERNAL_SERVER_ERROR));
    }
}
