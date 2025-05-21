package net.fullstack10.demo.dto;

import lombok.*;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BbsDTO {
    private Long idx;
    private String user_id;
    private String title;
    private String content;
    private String display_date;
    private int read_cnt=0;
    private LocalDateTime reg_date;
    private LocalDateTime modify_date;
}
