package com.unicalsocial.backend.post;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PostTypeServiceImpl implements PostTypeService {
    private final PostTypeRepository postTypeRepository;
    @Override
    public ResponseEntity<PostTypeDTO> findPostTypeByName(String postTypeString) {
        var postType = this.postTypeRepository.findByPostTypeName(postTypeString);
        if (postType == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(PostTypeMapper.INSTANCE.postTypeToDto(postType));
    }
}
