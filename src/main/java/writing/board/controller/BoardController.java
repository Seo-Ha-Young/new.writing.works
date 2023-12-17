package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.dto.PreferenceDTO;
import writing.board.entity.Member;
import writing.board.entity.MemberRole;
import writing.board.repository.MemberRepository;
import writing.board.security.dto.AuthMemberDTO;
import writing.board.service.BoardService;
import writing.board.service.PreferenceService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/html")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
private final BoardService boardService;
private final MemberRepository memberRepository;
    @GetMapping("/board")
    public void board(PageRequestDTO requestDTO, Model model) {
        log.info("board page................"+requestDTO);
        model.addAttribute("result", boardService.getList(requestDTO));

    }

    @GetMapping({"/view_image", "/view_essay"})
    public void view(long no, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, @AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        log.info("로그인 멤버 정보 "+authMemberDTO);
        if(authMemberDTO != null) {
            String nickname = memberRepository.findByMember_no(authMemberDTO.getNo()).getNickname();
            Long member_no = authMemberDTO.getNo();
            log.info("현재 로그인 한 인원 닉네임 : " + nickname);
            model.addAttribute("nickname", nickname);
            model.addAttribute("member_no", member_no);
            Set<MemberRole> member_role = memberRepository.findByMember_no(authMemberDTO.getNo()).getRoleSet();
            model.addAttribute("role_set", member_role);
        }
        log.info("게시글 번호 No = "+no);
        PostWrittenDTO dto = boardService.read(no);
        log.info("게시글 정보 :"+dto);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/member_info")
    public void member_info(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        log.info("header................");
        model.addAttribute("member", authMemberDTO);
    }

    @ResponseBody
    @DeleteMapping("/delete_post/{post_no}")
    public String removePost(@PathVariable Long post_no, @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("삭제하려는 번호 " + post_no);
        if(authMemberDTO != null) {
            Set<MemberRole> member = memberRepository.findByMember_no(authMemberDTO.getNo()).getRoleSet();
            log.info("로그인 멤버 정보 " + member.toString());
            if (member.toString().contains("ADMIN")) {
                log.info("게시글 삭제");
                log.info("삭제 번호 " + post_no);
                boardService.remove(post_no);
                return post_no+"번이 삭제되었습니다";
            }else {
                return "관리자만 삭제할 수 있습니다";
            }
        } else {
            return "관리자로 로그인하세요";
        }


    }

}
