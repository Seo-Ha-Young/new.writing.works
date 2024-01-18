package writing.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import writing.board.entity.PostWritten;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostWrittenDTO {

    private Long no;
    private String post_name;
    private String post_content;
    private String writer;
    private Long image_no;
    private LocalDateTime regDate;
    private int badCnt;
    private int goodCnt;

}
