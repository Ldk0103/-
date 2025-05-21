package net.fullstack10.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.swing.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Table(name = "tbl_board") // 테이블명 지정
public class BbsEntity extends BbsBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idx;

    @Column(columnDefinition = "varchar(20) not null comment '멤버 아이디' collate 'utf8mb4_unicode_ci'")
    private String user_id;

    @Column(columnDefinition = "varchar(200) not null comment '글 제목' collate 'utf8mb4_unicode_ci'")
    private String title;

    @Column(columnDefinition = "text not null comment '글 내용' collate 'utf8mb4_unicode_ci'")
    private String content;

    @Column(columnDefinition = "varchar(20) null comment '출력날짜' collate 'utf8mb4_unicode_ci'")
    private String display_date;

    @Builder.Default
    @Min(0)
    @Column(columnDefinition = "int null default 0 comment '조회수'")
    private int read_cnt=0;

    public void modify(Long idx, String user_id, String title, String content, String display_date) {
        this.idx = idx;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.display_date = display_date;
        super.setModify_date(LocalDateTime.now());
    }
}
