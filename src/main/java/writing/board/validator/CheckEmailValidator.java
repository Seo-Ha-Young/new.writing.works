package writing.board.validator;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import writing.board.dto.MemberDTO;
import writing.board.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<MemberDTO> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDTO memberDTO, Errors errors) {
        if(memberRepository.existsByEmail(memberDTO.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}