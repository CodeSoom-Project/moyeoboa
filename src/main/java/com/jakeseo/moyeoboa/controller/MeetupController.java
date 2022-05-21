package com.jakeseo.moyeoboa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetups")
public class MeetupController {
    @GetMapping("/")
    public String list() {
        // TODO: meetup 리스트를 가져온다. 리스트 검색은 추후에 지원할 예정이다.
        return "meetup 리스트";
    }

    @PostMapping("/")
    public String create() {
        // TODO: 생성된 meetup 의 정보를 보여준다.
        return "meetup 생성 정보";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id) {
        // TODO: 해당 id 를 가진 meetup 의 상세 정보를 보여준다.
        return "meetup 상세정보";
    }
}
