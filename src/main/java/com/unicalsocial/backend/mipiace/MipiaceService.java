package com.unicalsocial.backend.mipiace;

import org.springframework.security.core.Authentication;

public interface MipiaceService {
    MipiaceDTO createMipiace(int postId, Authentication authentication);
    MipiaceDTO getMipiace(int postid,Authentication authentication);
    Boolean deleteMipiace(int postId,Authentication authentication);
    Boolean existMipiace(int postIs,Authentication authentication);
}
