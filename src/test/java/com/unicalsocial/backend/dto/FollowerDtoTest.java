package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.follower.FollowerDTO;
import com.unicalsocial.backend.follower.FollowerMapper;
import com.unicalsocial.backend.follower.FollowerEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowerDtoTest {
    @Test
    public void shouldBeFollowerDto() {
        FollowerEntity follower = new FollowerEntity();
        follower.setFollowerUserId(1);
        follower.setFollowingUserId(1);

        FollowerDTO followerDTO = FollowerMapper.ISTANCE.followerToDto(follower);

        assertThat(follower.getFollowerUserId()).isEqualTo(followerDTO.getFollowerUserId());
        assertThat(follower.getFollowingUserId()).isEqualTo(followerDTO.getFollowingUserId());
    }

    @Test
    public void shouldBeFollower() {
        FollowerDTO followerDTO = new FollowerDTO(1,1);

        FollowerEntity follower = FollowerMapper.ISTANCE.followerDtoToFollower(followerDTO);

        assertThat(follower.getFollowerUserId()).isEqualTo(followerDTO.getFollowerUserId());
        assertThat(follower.getFollowingUserId()).isEqualTo(followerDTO.getFollowingUserId());
    }
}
