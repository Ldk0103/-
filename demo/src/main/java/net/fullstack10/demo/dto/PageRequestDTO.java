package net.fullstack10.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.net.URLEncoder;

@Log4j2
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequestDTO {
    @Builder.Default
    @Positive
    @Min(1)
    private long page_no=1;
    @Builder.Default
    @Min(1)
    private int page_size=10;
    @Builder.Default
    @Min(0)
    private long page_skip_count=0;
    @Builder.Default
    @Min(1)
    private int page_block_size=10;

    private String search_category;
    private String search_word;
    private String search_date_from;
    private String search_date_to;
    private String linkParams;

    public long getPage_skip_count(){
        return (this.page_no-1)*this.page_size;
    }

    public String getLinkParams(){
        StringBuilder sb = new StringBuilder();
        sb.append("page_size="+this.page_size);
        if(search_category!=null) sb.append("&search_category="+search_category);
        if(search_word!=null) {
            try{
                sb.append("&search_word="+ URLEncoder.encode(search_word, "UTF-8"));
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }

        if(search_date_from!=null) sb.append("&search_date_from="+search_date_from);
        if(search_date_to!=null) sb.append("&search_date_to="+search_date_to);
        return sb.toString();
    }
}
