package writing.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long no;
    private String name;
    private Date dateOfBirth;
    private String address;
    private String id;
    private String password;
    private String nickName;
    private String email;
    private LocalDateTime modDate;
    private LocalDateTime regDate;

}
