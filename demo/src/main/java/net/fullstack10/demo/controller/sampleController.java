package net.fullstack10.demo.controller;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Log4j2
@Controller
@RequestMapping("/sample")
public class sampleController {

    @GetMapping("/test")
    public void hello(
            @RequestParam (name="msg", required = false, defaultValue = "World") String msg,
            Model model
    ){
        model.addAttribute("msg", "아 배부장찌개 먹고싶다");
    }

    @GetMapping("/test2")
    public String[] hello2() {
        return new String[]{"Hello", "Spring", "boot"};
    }

    @GetMapping("/ex1")
    public void ex1(Model model) {
        model.addAttribute("num11", "11");
    }

    @GetMapping("/ex2")
    public void ex2(Model model) {
        List<String> list = Arrays.asList("Hello", "Spring", "boot");
        model.addAttribute("list", list);
    }

    @GetMapping("/ex3")
    public void ex3(Model model) {
        List<String> list = Arrays.asList("Hello", "Spring", "boot");
        model.addAttribute("list", list);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) {
        List<String> list = Arrays.asList("Hello", "Spring", "boot");
        model.addAttribute("list", list);
    }

    @GetMapping("/ex5")
    public void ex5(Model model) {
        List<String> list = Arrays.asList("Hello", "Spring", "boot");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "이재명");
        map.put("key2", "53");

        SampleDTO dto = new SampleDTO();
        dto.p1 = "값p1";
        dto.p2 = "값p2";
        dto.p3 = "값p3";

        model.addAttribute("list", list);
        model.addAttribute("map", map);
        model.addAttribute("dto", dto);
    }

    @GetMapping("/ex6")
    public void ex6(Model model) {
        List<SampleMember> list = Arrays.asList(
                new SampleMember("홍길동1", 20),
                new SampleMember("홍길동2", 30),
                new SampleMember("홍길동3", 40),
                new SampleMember("홍길동4", 10)
        );
        model.addAttribute("list", list);
    }
}

class SampleDTO {
    public String p1;
    public String p2;
    public String p3;

    public String getP1() { return p1;}
    public String getP2() { return p2;}
    public String getP3() { return p3;}
}


class SampleMember {
    public SampleMember(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String name;
    public int age;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + ' ' + age;
    }
}
