package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import writing.board.dto.PageRequestDTO;
import writing.board.dto.PostWrittenDTO;
import writing.board.service.BoardService;
import writing.board.service.PreferenceService;

@Controller
@RequestMapping("/html")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
private final BoardService boardService;
    @GetMapping("/board")
    public void board(PageRequestDTO requestDTO, Model model) {
        log.info("board page................"+requestDTO);
        model.addAttribute("result", boardService.getList(requestDTO));

    }

    @GetMapping({"/view_image", "/view_essay"})
    public void view(long no, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("게시글 번호 No = "+no);
        PostWrittenDTO dto = boardService.read(no);
        model.addAttribute("dto", dto);
    }


}
