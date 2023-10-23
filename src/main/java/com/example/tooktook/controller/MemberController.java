package com.example.tooktook.controller;

import com.example.tooktook.common.response.ApiResponse;
import com.example.tooktook.common.response.ResponseCode;
import com.example.tooktook.model.repository.MemberNeo4jRepository;
import com.example.tooktook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberNeo4jRepository memberNeo4jRepository;
    private final MemberService memberService;
    @PutMapping("/nickname/{memberId}")
    public ApiResponse<?> setNickName(
            @RequestParam @Valid @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,6}$", message = "닉네임은 2~6자의 영문, 숫자, 한글만 사용 가능합니다.")
            String nickName,
            @AuthenticationPrincipal UserDetails loginMember
    ) {

        Long memberId = memberNeo4jRepository.findByLoginEmail(loginMember.getUsername())
                .get().getMemberId();

        memberService.setNickName(nickName, memberId);
        return ApiResponse.ok(ResponseCode.Normal.UPDATE,memberId);
    }

}
