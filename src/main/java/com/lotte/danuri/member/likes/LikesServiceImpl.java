package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.kafka.service.KafkaProducerService;
import com.lotte.danuri.member.likes.dto.LikesReqDto;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class LikesServiceImpl implements LikesService{

    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public boolean checkLike(Long memberId, String productCode) {
        memberRepository.findByIdAndDeletedDateIsNull(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );
        return likesRepository.findByMemberIdAndProductCode(memberId, productCode).isPresent();
    }

    @Override
    public List<String> getLikes(String memberId) {

        Long id = Long.parseLong(memberId);

        memberRepository.findByIdAndDeletedDateIsNull(id).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        return likesRepository.findByMemberId(id).orElseGet(ArrayList::new)
            .stream().map(Likes::getProductCode).collect(Collectors.toList());
    }

    @Override
    public int register(Long memberId, String productCode) {
        Member member = memberRepository.findByIdAndDeletedDateIsNull(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        likesRepository.save(Likes.builder()
            .productCode(productCode)
            .member(member)
            .build());

        kafkaProducerService.send("like-insert", LikesReqDto.builder().productCode(productCode).build());

        return 1;
    }

    @Override
    public int delete(Long memberId, String productCode) {
        memberRepository.findByIdAndDeletedDateIsNull(memberId).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        likesRepository.deleteByMemberIdAndProductCode(memberId, productCode);

        kafkaProducerService.send("like-delete", LikesReqDto.builder().productCode(productCode).build());

        return 1;
    }

    @Override
    public int deleteAllByMember(Long memberId) {
        likesRepository.deleteAllByMemberId(memberId);
        return 1;
    }


}
