package writing.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends BaseEntity {

    private String name;
    private Date dateOfBirth;
    private String address;
    private String id;
    private String password;
    private String nickName;
    private String email;

    @LastModifiedDate
    @Column(name ="modDate")
    private LocalDateTime modDate;

}
