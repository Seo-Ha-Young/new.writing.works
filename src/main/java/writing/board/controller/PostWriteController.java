package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.service.BoardService;
import writing.board.service.PostService;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PostWriteController {
    private final PostService postService;

    // 글 작성 페이지로 이동
    @GetMapping("/html/write")
    public void write(PageRequestDTO requestDTO, Model model) {
        log.info("board page................"+requestDTO);
        //model.addAttribute("result", boardService.getList(requestDTO));
    }


    // 글 작성 처리
    @PostMapping("/savePost")
    @ResponseBody
    public String savePost(@RequestBody PostWrittenDTO writeDTO) {
        log.info("글 작성 요청: " + writeDTO);
       postService.savePost(writeDTO); // BoardService를 통해 글 작성 로직 수행
        return "redirect:/html/board"; // 작성 후 목록 페이지로 리다이렉트
    }
}
