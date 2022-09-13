package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.likes.dto.LikesDeleteReqDto;
import com.lotte.danuri.member.likes.dto.LikesInsertReqDto;
import com.lotte.danuri.member.likes.dto.LikesReqDto;
import com.lotte.danuri.member.likes.dto.LikesRespDto;
import com.lotte.danuri.member.members.Member;
import com.lotte.danuri.member.members.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class LikesServiceImpl implements LikesService{

    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Long> getLikes(LikesReqDto dto) {

        List<Long> productList = new ArrayList<>();

        likesRepository.findByMemberId(dto.getMemberId()).orElseGet(ArrayList::new)
            .forEach(likes -> productList.add(likes.getProductId()));

        return productList;
    }

    @Override
    public int register(LikesInsertReqDto dto) {
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();

        Likes likes = dto.toEntity(member);
        likesRepository.save(likes);

        return 1;
    }

    @Override
    public int delete(LikesDeleteReqDto dto) {
        likesRepository.deleteById(dto.getId());

        return 1;
    }


}
