package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.dto.PreferenceDTO;
import writing.board.entity.Member;
import writing.board.repository.MemberRepository;
import writing.board.security.dto.AuthMemberDTO;
import writing.board.service.BoardService;
import writing.board.service.PreferenceService;

import java.util.Arrays;
import java.util.List;

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
        if(authMemberDTO != null) {
            String nickname = memberRepository.findByMember_no(authMemberDTO.getNo()).getNickname();
            Long member_no = authMemberDTO.getNo();
            log.info("현재 로그인 한 인원 닉네임 : " + nickname);
            model.addAttribute("nickname", nickname);
            model.addAttribute("member_no", member_no);
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

}
