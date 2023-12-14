package writing.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long no;
    private String review_content;
    private Long member_no;
    private String nickname;
    private Long post_no;
    private LocalDateTime regDate;

}
