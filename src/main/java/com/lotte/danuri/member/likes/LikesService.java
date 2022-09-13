package com.lotte.danuri.member.likes;

import com.lotte.danuri.member.likes.dto.LikesDeleteReqDto;
import com.lotte.danuri.member.likes.dto.LikesInsertReqDto;
import com.lotte.danuri.member.likes.dto.LikesReqDto;
import java.util.List;

public interface LikesService {

    List<Long> getLikes(LikesReqDto dto);

    int register(LikesInsertReqDto dto);

    int delete(LikesDeleteReqDto dto);

}
