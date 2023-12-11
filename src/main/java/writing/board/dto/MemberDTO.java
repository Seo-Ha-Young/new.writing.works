package writing.board.dto;


import lombok.Builder;
import lombok.Data;
import writing.board.entity.Member;
import writing.board.entity.MemberRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class MemberDTO {
    private Long no;
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일을 입력해주세요.")
    private String email; // 이메일
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password; // 비밀번호
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 15, message = "닉네임은 2 ~ 15자 사이로 입력해주세요")
    private String nickname; // 닉네임
    @NotBlank(message = "이름을 입력해주세요.")
    private String name; // 이름
    @NotBlank(message = "생년월일을 입력해주세요.")
    private String birth; // 생년월일
//    @NotBlank(message = "주소를 입력해주세요.")
//    private String address; // 주소
    private String mainaddress;
    private String subaddress;

    /* DTO -> Entity */
    public Member toEntity() {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .birth(birth)
                //.address(address)
                .mainaddress(mainaddress)
                .subaddress(subaddress)
                .build();
        member.addMemberRole(MemberRole.USER);
        return member;
    }

    public static MemberDTO toDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setNo(member.getNo());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setNickname(member.getNickname());
        memberDTO.setName(member.getName());
        memberDTO.setBirth(member.getBirth());
        //memberDTO.setAddress(member.getAddress());
        memberDTO.setMainaddress(member.getMainaddress());
        memberDTO.setSubaddress(member.getSubaddress());
        return memberDTO;
    }
}
