package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.security.dto.AuthMemberDTO;
import writing.board.security.security.MemberUserDetailsService;
import writing.board.service.PostServiceImpl;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PostWriteController {
    private final PostServiceImpl postService;
    private final MemberUserDetailsService memberUserDetailsService;

    @GetMapping("/html/write")
    public void write( @AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        if (authMemberDTO != null) {
            String nickname = authMemberDTO.getNickname();
            Long member_no = authMemberDTO.getNo();
            log.info("현재 로그인 한 인원 닉네임 : " + nickname);
            model.addAttribute("nickname", nickname);
            model.addAttribute("member_no", member_no);
        }
    }


    // 글 작성 처리
    @PostMapping("/savePost")
    @ResponseBody
    public ResponseEntity<PostWrittenDTO> savePost(@RequestBody PostWrittenDTO writeDTO, @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("글 작성 요청: " + writeDTO);
            String currentUserNickname = authMemberDTO.getNickname();
            Long imageNo = writeDTO.getImage_no(); //
            postService.savePost(writeDTO, currentUserNickname, imageNo, writeDTO.getPost_name());
            return ResponseEntity.ok().body(writeDTO);
    }
}