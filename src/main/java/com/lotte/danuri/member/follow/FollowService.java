package com.lotte.danuri.member.follow;

import com.lotte.danuri.member.follow.dto.FollowDto;
import com.lotte.danuri.member.follow.dto.FollowRespDto;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.List;
import javax.persistence.FetchType;

public interface FollowService {

    Long register(Long memberId, Long storeId);

    List<StoreDto> getStoresByFollow(Long memberId);

    int delete(Long memberId, FollowDto dto);

    int deleteAllByMember(Long memberId);

    List<Long> getMembersByFollow(Long storeId);

    FollowRespDto check(Long memberId, Long storeId);

}
