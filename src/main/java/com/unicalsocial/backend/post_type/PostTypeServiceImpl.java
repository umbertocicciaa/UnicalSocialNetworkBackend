package com.unicalsocial.backend.post_type;

import com.unicalsocial.backend.exception.PostTypeNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Hidden
public class PostTypeServiceImpl implements PostTypeService {
    private final PostTypeRepository postTypeRepository;
    @Override
    public PostTypeDTO findPostTypeByName(String postTypeString) {
        var postType = this.postTypeRepository.findByPostTypeName(postTypeString).orElseThrow(PostTypeNotFoundException::new);
        return PostTypeMapper.INSTANCE.postTypeToDto(postType);
    }
}
