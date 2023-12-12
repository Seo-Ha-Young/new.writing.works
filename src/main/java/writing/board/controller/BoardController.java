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
private final PreferenceService preferenceService;
    @GetMapping("/board")
    public void board(PageRequestDTO requestDTO, Model model) {
        log.info("board page................"+requestDTO);
        model.addAttribute("result", boardService.getList(requestDTO));

    }

    @GetMapping({"/view_image", "/view_essay"})
    public void view(long no, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, @AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        if(authMemberDTO != null) {
            String member = authMemberDTO.getNickname();
            log.info("현재 로그인 한 인원 닉네임 : " + member);
            model.addAttribute("member", member);
        }
        log.info("게시글 번호 No = "+no);
        PostWrittenDTO dto = boardService.read(no);
        log.info("게시글 정보 :"+dto);
        model.addAttribute("dto", dto);
        Long good_no = dto.getNo();
        List<PreferenceDTO> like = preferenceService.find_post_no(good_no);
        log.info("좋아요 정보 :"+Arrays.asList(like));
        log.info("좋아요 정보2 :"+like);
        model.addAttribute("like", like);
    }

    @PostMapping("/member_info")
    public void member_info(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        log.info("header................");
        model.addAttribute("member", authMemberDTO);
    }

}
