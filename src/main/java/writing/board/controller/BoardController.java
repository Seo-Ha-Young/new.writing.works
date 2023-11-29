package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import writing.board.dto.PageRequestDTO;
import writing.board.service.BoardService;

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

}
