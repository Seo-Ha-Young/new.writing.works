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
public class EssayDTO {
    private Long no;
    private String genre;
    private String book_name;
    private String writer;
    private String essay_content;
    private LocalDateTime regDate;

}
