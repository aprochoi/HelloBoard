package Board.springBoard.board.controller;

import Board.springBoard.exception.CustomException;
import Board.springBoard.exception.ExceptionCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @GetMapping("/test")
    public String test() {
        throw new CustomException(ExceptionCode.POST_NOT_FOUND);
    }
}
