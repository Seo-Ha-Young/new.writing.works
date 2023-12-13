package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PostWrittenDTO;
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
    public ResponseEntity<PostWrittenDTO> savePost(@RequestBody PostWrittenDTO writeDTO) {
        log.info("글 작성 요청: " + writeDTO);
        return ResponseEntity.ok().body(writeDTO);
    }
}