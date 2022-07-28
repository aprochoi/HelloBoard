package Board.springBoard.board.controller;

import Board.springBoard.board.dto.BoardRequestDto;
import Board.springBoard.board.dto.BoardResponseDto;
import Board.springBoard.board.service.BoardService;
import Board.springBoard.exception.CustomException;
import Board.springBoard.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    //예외 처리 테스트
    @GetMapping("/test")
    public String test() {
        throw new CustomException(ExceptionCode.POST_NOT_FOUND);
    }

    //게시글 작성
    @PostMapping("/boards")
    public Long save(@RequestBody BoardRequestDto requestDto) {
        return boardService.save(requestDto);
    }

    //게시글 리스트 조회
    @GetMapping("/boards")
    public List<BoardResponseDto> findAll() {
        return boardService.findAll();
    }

    //게시글 수정
    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable("id") Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }
}
