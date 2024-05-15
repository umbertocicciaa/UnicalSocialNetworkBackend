package com.unicalsocial.backend.mipiace;

public interface MipiaceService {
    MipiaceDTO createMipiace(int userId, int postId);
    MipiaceDTO getMipiace(int userid, int postid);
    Boolean deleteMipiace(int userid,int postId);
    Boolean existMipiace(int userid,int postIs);
}
