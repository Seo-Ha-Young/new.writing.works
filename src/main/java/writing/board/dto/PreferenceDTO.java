package writing.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import writing.board.entity.BaseEntity;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceDTO extends BaseEntity {

    private Long no;
    private String id;
    private Long post_no;
    private Long good;
    private Long bad;
    private LocalDateTime regDate;

}
