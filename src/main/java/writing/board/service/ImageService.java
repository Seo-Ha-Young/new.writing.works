package writing.board.service;

import writing.board.dto.EssayDTO;
import writing.board.dto.ImageDTO;
import writing.board.entity.Essay;
import writing.board.entity.Image;

import java.util.HashMap;
import java.util.Map;

public interface ImageService {
    Long register(ImageDTO imageDTO);

    default Map<String, Object> dtoToEntity(ImageDTO imageDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Image image = Image.builder()
                .img_name(imageDTO.getImg_name()).build();
        entityMap.put("image", image);

        return entityMap;
    }

}
