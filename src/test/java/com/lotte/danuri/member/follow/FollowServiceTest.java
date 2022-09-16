package com.lotte.danuri.member.follow;

import static org.assertj.core.api.Assertions.assertThat;

import com.lotte.danuri.member.follow.dto.FollowDto;
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

    @Test
    void 스토어_팔로우_등록() {

        FollowDto dto = FollowDto.builder().memberId(4L).storeId(10L)
            .build();

        FollowDto dto2 = FollowDto.builder().memberId(4L).storeId(11L)
            .build();

        int result = followService.register(dto);
        followService.register(dto2);

        int size = memberRepository.findById(4L).get().getFollowList().size();


        assertThat(size).isEqualTo(4);
        assertThat(result).isEqualTo(1);

    }
    @Test
    void 스토어_팔로우_목록_조회_스토어() {

        List<StoreDto> list = followService.getStoresByFollow(4L);

        list.forEach(s -> System.out.println(s.getName()));

        assertThat(list.size()).isGreaterThanOrEqualTo(0);

    }

    @Test
    void 스토어_팔로우_목록_조회_회원() {

        Long storeId = 10L;

        List<Long> list = followService.getMembersByFollow(storeId);

        list.forEach(System.out::println);

        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }
    @Test
    void 스토어_팔로우_목록_취소() {

        FollowDto dto = FollowDto.builder().id(14L).memberId(4L)
            .build();

        int result = followService.delete(dto);

        assertThat(result).isEqualTo(1);
    }
}
