package com.lotte.danuri.member.follow;

import com.lotte.danuri.member.follow.dto.FollowDto;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.List;
import javax.persistence.FetchType;

public interface FollowService {

    int register(FollowDto dto);

    List<StoreDto> getStoresByFollow(Long memberId);

    int delete(FollowDto dto);

    List<Long> getMembersByFollow(Long storeId);

}
