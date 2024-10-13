package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dtos.CreatedMipiceResponse;
import com.unicalsocial.backend.dtos.EliminatoMipiaceResponse;
import com.unicalsocial.backend.dtos.EsisteMipiaceResponse;
import com.unicalsocial.backend.dtos.MipiaceResponse;
import org.springframework.security.core.Authentication;

public interface MipiaceService {
    CreatedMipiceResponse createMipiace(int postId, Authentication authentication);
    MipiaceResponse getMipiace(int postid, Authentication authentication);
    EliminatoMipiaceResponse deleteMipiace(int postId, Authentication authentication);
    EsisteMipiaceResponse existMipiace(int postIs, Authentication authentication);
}
