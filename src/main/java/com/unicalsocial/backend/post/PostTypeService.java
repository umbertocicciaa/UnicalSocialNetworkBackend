package com.unicalsocial.backend.post;

import org.springframework.http.ResponseEntity;

public interface PostTypeService {
    ResponseEntity<PostTypeDTO> findPostTypeByName(String postTypeString);
}
