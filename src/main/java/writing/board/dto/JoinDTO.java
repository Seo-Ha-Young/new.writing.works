package writing.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import writing.board.entity.User;
import writing.board.entity.UserRole;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class JoinDTO {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String login_id;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    @NotBlank(message = "이메일이 비어있습니다.")
    private String email;

    @NotBlank(message = "이름이 비어있습니다.")
    private String name;
    
    @NotBlank(message = "생년월일이 비어있습니다.")
    private String birth;

    @NotBlank(message = "주소가 비어있습니다.")
    private String address;
    
    public User toEntity(String encodedPassword) {
        return User.builder()
                .login_id(this.login_id)
                .password(this.password)
                .nickname(this.nickname)
                .email(this.email)
                .name(this.name)
                .birth(this.birth)
                .address(this.address)
                .role(UserRole.USER)
                .build();
    }


}
