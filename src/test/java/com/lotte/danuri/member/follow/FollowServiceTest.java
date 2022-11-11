package com.lotte.danuri.member.follow;

import static org.assertj.core.api.Assertions.assertThat;

import com.lotte.danuri.member.follow.dto.FollowDto;
import com.lotte.danuri.member.follow.dto.FollowRespDto;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.members.MemberService;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FollowServiceTest {

    @Autowired
    FollowService followService;

    @Autowired
    MemberRepository memberRepository;

    Long memberId = 131L;

    @Test
    void 스토어_팔로우_등록() {

        int result = followService.register(memberId, 13L);
        followService.register(memberId, 14L);

        int size = memberRepository.findById(memberId).get().getFollowList().size();


        assertThat(size).isEqualTo(2);
        assertThat(result).isEqualTo(1);

    }
    @Test
    void 스토어_팔로우_목록_조회_스토어() {

        List<StoreDto> list = followService.getStoresByFollow(memberId);

        list.forEach(s -> System.out.println(s.getName()));

        assertThat(list.size()).isGreaterThanOrEqualTo(0);

    }

    @Test
    void 스토어_팔로우_목록_조회_회원() {

        Long storeId = 13L;

        List<Long> list = followService.getMembersByFollow(storeId);

        list.forEach(System.out::println);

        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 회원_스토어_팔로우_여부_조회() {

        FollowRespDto dto = followService.check(memberId, 13L);

        assertThat(dto.getStatus()).isEqualTo(true);
    }

    @Test
    void 스토어_팔로우_목록_취소() {

        FollowDto dto = FollowDto.builder().id(14L).storeId(13L)
            .build();

        int result = followService.delete(memberId, dto);

        assertThat(result).isEqualTo(1);
    }
}
