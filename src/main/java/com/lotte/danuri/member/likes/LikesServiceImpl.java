package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.common.exception.codes.ErrorCode;
import com.lotte.danuri.member.common.exception.codes.MemberErrorCode;
import com.lotte.danuri.member.common.exception.exceptions.NoMemberException;
import com.lotte.danuri.member.kafka.service.KafkaProducerService;
import com.lotte.danuri.member.likes.dto.LikesDeleteReqDto;
import com.lotte.danuri.member.likes.dto.LikesInsertReqDto;
import com.lotte.danuri.member.likes.dto.LikesReqDto;
import com.lotte.danuri.member.likes.dto.LikesRespDto;
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
    public List<Long> getLikes(LikesReqDto dto) {
        return likesRepository.findByMemberId(dto.getMemberId()).orElseGet(ArrayList::new)
            .stream().map(Likes::getProductId).collect(Collectors.toList());
    }

    @Override
    public int register(LikesReqDto dto) {
        Member member = memberRepository.findByIdAndDeletedDateIsNull(dto.getMemberId()).orElseThrow(
            () -> new NoMemberException(MemberErrorCode.NO_MEMBER_EXISTS.getMessage(), MemberErrorCode.NO_MEMBER_EXISTS)
        );

        Likes likes = dto.toEntity(member);
        likesRepository.save(likes);

        kafkaProducerService.send("like-insert-delete", dto);

        return 1;
    }

    @Override
    public int delete(LikesReqDto dto) {
        likesRepository.deleteById(dto.getId());

        kafkaProducerService.send("like-insert-delete", dto);

        return 1;
    }

    @Override
    public int deleteAllByMember(Long memberId) {
        likesRepository.deleteAllByMemberId(memberId);
        return 1;
    }


}
