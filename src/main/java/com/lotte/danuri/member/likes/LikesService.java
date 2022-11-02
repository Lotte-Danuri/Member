package com.lotte.danuri.member.likes;

import java.util.List;

public interface LikesService {

    boolean checkLike(Long memberId, String productCode);
    List<String> getLikes(String memberId);

    int register(Long memberId, String productCode);

    int delete(Long memberId, String productCode);

    int deleteAllByMember(Long memberId);

}
