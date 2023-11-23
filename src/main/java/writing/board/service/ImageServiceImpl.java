package writing.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import writing.board.dto.EssayDTO;
import writing.board.dto.ImageDTO;
import writing.board.entity.Essay;
import writing.board.entity.Image;
import writing.board.repository.EssayRepository;
import writing.board.repository.ImageRepository;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Long register(ImageDTO imageDTO) {
        Map<String, Object> entityMap = dtoToEntity(imageDTO);
        Image image = (Image) entityMap.get("image");
        imageRepository.save(image);
        return image.getNo();
    }


}
