package writing.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import writing.board.dto.EssayDTO;
import writing.board.dto.ImageDTO;
import writing.board.service.EssayService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@Log4j2
@RequestMapping("/upload")
@RequiredArgsConstructor
public class ImageController {
    @Value("${writing.board.upload.path}")
    private String uploadPath;

    @GetMapping("/image")
    public void image() { };

    @PostMapping("/image")
    public ResponseEntity<List<ImageDTO>> addImage(MultipartFile[] uploadFiles) {
        List<ImageDTO> resultDTOList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("fileName : " + fileName);
            Path f_name = Paths.get(fileName);
            try {
                uploadFile.transferTo(f_name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }


}
