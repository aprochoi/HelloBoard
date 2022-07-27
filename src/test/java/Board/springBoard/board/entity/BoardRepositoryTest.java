package Board.springBoard.board.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

//    @AfterEach  // 단위 테스트 끝날때 마다 실행될 메서드
//    public void cleanup() {
//        postsRepository.deleteAll();    //테스트가 끝나면 그 내용도 지워준다.
//    }

    @DisplayName("저장 테스트")
    @Test
    public void Board_save() {
        //given 게시글 파라미터 생성
        Board params = Board.builder()
                .title("1번 게시글 제목")
                .content("1번 게시글 내용")
                .writer("최영민")
                .hits(0)
                .deleteYn('N')
                .build();

        //when  게시글 저장
        boardRepository.save(params);


        //then
        Board entity = boardRepository.findById(1L).get();

        assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
        assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
        assertThat(entity.getWriter()).isEqualTo("최영민");
    }

    @DisplayName("전체 게시글 조회 테스트")
    @Test
    public void findAll() {
        //given 전체 게시글 조회
        long boardsCount = boardRepository.count();

        //when then 전체 게시글 리스트 조회
        List<Board> boardList = boardRepository.findAll();
    }

    @DisplayName("삭제 테스트")
    @Test
    public void delete() {
        //given 게시글 파라미터 생성
//        Posts params = Posts.builder()
//                .title("1번 게시글 제목")
//                .content("1번 게시글 내용")
//                .writer("최영민")
//                .hits(0)
//                .deleteYn('N')
//                .build();
//
//        //when  게시글 저장
//        postsRepository.save(params);

        //when then 게시글 삭제
        Board board = boardRepository.findById(1L).get();
        boardRepository.delete(board);
    }
}
