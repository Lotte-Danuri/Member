package com.lotte.danuri.member.follow;

import com.lotte.danuri.member.common.exception.codes.CommonErrorCode;
import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.codes.StoreErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.common.exception.exceptions.NoResourceException;
import com.lotte.danuri.member.common.exception.exceptions.NoStoreException;
import com.lotte.danuri.member.follow.dto.FollowDto;
import com.lotte.danuri.member.follow.dto.FollowRespDto;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import com.lotte.danuri.member.members.dto.MemberRespDto;
import com.lotte.danuri.member.store.Store;
import com.lotte.danuri.member.store.StoreRepository;
import com.lotte.danuri.member.store.dto.StoreDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Long register(Long memberId, Long storeId) {

        Member member = memberRepository.findByIdAndDeletedDateIsNull(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        Store store = storeRepository.findById(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS)
        );

        if (followRepository.findByMemberIdAndStoreIdAndDeletedDateIsNull(member.getId(), store.getId()).isPresent()) {
            return 0L;
        }

        Follow result = followRepository.save(Follow.builder().member(member).store(store).build());
        member.updateFollows(result);
        return result.getId();
    }

    @Override
    public List<StoreDto> getStoresByFollow(Long memberId) {

        Member member = memberRepository.findByIdAndFollow(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        List<Long> list = member.getFollowList().stream()
            .map(f -> f.getStore().getId()).toList();

        List<Store> storeList = storeRepository.findByIdIn(list).orElseThrow();

        return storeList.stream().map(s ->
            StoreDto.builder()
                .id(s.getId())
                .name(s.getName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public int delete(Long memberId, FollowDto dto) {

        Member member = memberRepository.findByIdAndDeletedDateIsNull(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS)
        );

        Follow follow = followRepository.findById(dto.getId()).orElseThrow(
            () -> new NoResourceException(CommonErrorCode.RESOURCE_NOT_FOUND.getMessage(), CommonErrorCode.RESOURCE_NOT_FOUND)
        );

        member.getFollowList().remove(follow);
        store.getFollowList().remove(follow);
        follow.delete();

        return 1;
    }

    @Override
    public int deleteAllByMember(Long memberId) {
        followRepository.findByMemberIdAndDeletedDateIsNull(memberId).orElseGet(ArrayList::new)
            .forEach(Follow::delete);
        return 1;
    }

    @Override
    public List<Long> getMembersByFollow(Long storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow(
            () -> new NoStoreException(StoreErrorCode.NO_STORE_EXISTS.getMessage(), StoreErrorCode.NO_STORE_EXISTS)
        );

        return store.getFollowList().stream().map(
            f -> f.getMember().getId()).toList();

    }

    @Override
    public FollowRespDto check(Long memberId, Long storeId) {
        Optional<Follow> result = followRepository.findByMemberIdAndStoreIdAndDeletedDateIsNull(memberId,storeId);

        if(result.isPresent()) {
            return FollowRespDto.builder()
                .id(result.get().getId())
                .status(true)
                .build();
        }

        return FollowRespDto.builder()
            .status(false)
            .build();
    }

}
