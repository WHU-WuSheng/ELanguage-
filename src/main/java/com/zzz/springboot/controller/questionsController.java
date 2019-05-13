package com.zzz.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzz.springboot.entity.Questions;

@RequestMapping(value = "/api")
@RestController
public class questionsController {


    @RequestMapping("/chinese")
    public Questions chinese_json() {
    	Questions chinese = new Questions();
        chinese.que_1 = "1、지난주에 실시된 글쓰기 대회의 ( )가 조금 전에 나왔다.";
        chinese.que_2 = "2、김 과장은 회사를 ( ) 자신의 사업을 시작하였다";
        chinese.que_3 = "3、오랜만에 만난 친구가 ( ) 처음 만난 사람처럼 어색하게 느껴졌다";
        chinese.que_4 = "4、책상 위쪽에 손이 ( ) 않아서 책을 꺼내기가 어렵다.";
        chinese.que_5 = "5、학교 운동장은 늘 ( ) 뛰노는 학생들로 가득하다.";
        return chinese;
    }

    @RequestMapping("/english")
    public Questions english_json() {
    	Questions english = new Questions();
        english.que_1 = "1、I just heard ____ bank where Dora works was robbed by____ gunman wearing a mask.";
        english.que_2 = "2、Jane's grandmother had wanted to write _____ children’s book for many years, but one thing or another always got in ______ way.";
        english.que_3 = "3、Brian is gifted in writing music; he is very likely to be ____ Beethoven. ";
        english.que_4 = "4、_____ more learned a man is,_____ more modest be usually become.";
        english.que_5 = "5、There is no need to tell me your answer now. Give it some ______ and then let me know.";
        return english;
    }

}
