package com.unicalsocial.backend.mipiace;

import org.springframework.security.core.Authentication;

public interface MipiaceService {
    CreatedMipiceResponse createMipiace(int postId, Authentication authentication);
    MipiaceResponse getMipiace(int postid,Authentication authentication);
    EliminatoMipiaceResponse deleteMipiace(int postId,Authentication authentication);
    EsisteMipiaceResponse existMipiace(int postIs,Authentication authentication);
}
