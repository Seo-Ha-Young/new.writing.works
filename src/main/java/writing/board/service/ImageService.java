package writing.board.service;


import writing.board.dto.ImageDTO;

import writing.board.dto.PageRequestDTO;
import writing.board.dto.PageResultDTO;
import writing.board.entity.Image;


import java.util.HashMap;
import java.util.Map;

public interface ImageService {
    Long register(ImageDTO imageDTO);
    PageResultDTO<ImageDTO, Image> getList(PageRequestDTO requestDTO);

    ImageDTO getImage(long no);
    default Map<String, Object> dtoToEntity(ImageDTO imageDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Image image = Image.builder()
                .img_name(imageDTO.getImg_name()).build();
        entityMap.put("image", image);

        return entityMap;
    }
    default ImageDTO entityToDto(Image entity) {
        return ImageDTO.builder()
                .no(entity.getNo())
                .img_name(entity.getImg_name())
                .regDate(entity.getRegDate())
                .build();
    }
}
