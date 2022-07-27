package Board.springBoard.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor      // 기본 생성자 자동 추가 == public Posts{}
@Getter     // getter 메서드 자동 추가
@Entity     // 게시판 post 에 관한 entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // PK -> auto increment

    @Column(length = 500, nullable = false)     // varchar(255)가 기본 -> 500으로 확장, 제목
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)    // varchar(255)가 기본 -> type 을 TEXT 로 바꿔줌, 내용
    private String content;

    private String writer;  // 작성자

    private int hits;   // 조회 수

    private char deleteYn;   // 삭제 여부

    private LocalDateTime createdDate = LocalDateTime.now(); // 생성일

    private LocalDateTime modifiedDAte; //수정일

    @Builder
    public Board(String title, String content, String writer, int hits, char deleteYn) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.deleteYn = deleteYn;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDAte = LocalDateTime.now();
    }
}
