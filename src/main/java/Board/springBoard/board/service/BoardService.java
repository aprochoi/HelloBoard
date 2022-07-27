package Board.springBoard.board.service;

import Board.springBoard.board.dto.BoardRequestDto;
import Board.springBoard.board.dto.BoardResponseDto;
import Board.springBoard.board.entity.Board;
import Board.springBoard.board.entity.BoardRepository;
import Board.springBoard.exception.CustomException;
import Board.springBoard.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 생성
    @Transactional
    public Long save(BoardRequestDto requestDto) {
        Board entity = boardRepository.save(requestDto.toEntity());
        return entity.getId();
    }

    // 게시글 리스트 조회
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Board> boards = boardRepository.findAll(sort);
        return boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    // 게시글 수정
    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        Board entity = boardRepository.findById(id).orElseThrow(() ->
                new CustomException(ExceptionCode.POST_NOT_FOUND));

        entity.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getWriter());
        return id;
    }
}
