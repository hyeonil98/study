package com.example.proj.controller;

import com.example.domain.PostRender;
import com.example.proj.repository.BoardJpaRepository;
import com.example.proj.repository.CafeMemberJpaRepository;
import com.example.proj.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/cafe/{cafeId}/boards/{boardId}/posts")
public class PostController {
    private final CafeMemberJpaRepository cafeMemberJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final PostJpaRepository postJpaRepository;

}
