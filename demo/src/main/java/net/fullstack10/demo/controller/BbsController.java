package net.fullstack10.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack10.demo.dto.BbsDTO;
import net.fullstack10.demo.repository.BbsRepository;
import net.fullstack10.demo.service.BbsServiceIf;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/bbs")
public class BbsController {
    private final BbsServiceIf bbsService;

    //게시판 목록
    @RequestMapping("/list")
    public String bbsList(
            @RequestParam(name = "page_no", required = false, defaultValue = "1") int page_no,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int page_size,
            Model model
    ) {
        List<BbsDTO> dtoList = bbsService.bbsList(page_no, page_size);
        log.info(dtoList);
        model.addAttribute("page_no", page_no);
        model.addAttribute("page_size", page_size);
        model.addAttribute("dtoList", dtoList);
        return "bbs/list";
    }

    //게시판 조회
    @RequestMapping("/view")
    public String bbsView(
            @RequestParam(name = "idx") String idx,
            Model model
    ) {
        BbsDTO dto = bbsService.getView(Long.parseLong(idx));
        log.info(dto);
        model.addAttribute("dto", dto);
        return "bbs/view";
    }

    //게시판 등록
    @GetMapping("/regist")
    public String bbsRegistGET(
            Model model
    ) {
        return "bbs/regist";
    }

    //게시판 등록
    @PostMapping("/regist")
    public String bbsRegistPOST(
            @ModelAttribute BbsDTO bbsDTO,
            Model model
    ) {
//        bbsDTO.setReg_date(LocalDateTime.now());
//        bbsDTO.setModify_date(LocalDateTime.now());

        bbsService.bbsRegist(bbsDTO);
        return "redirect:/bbs/list";
    }

    //게시판 수정
    @GetMapping("/modify")
    public String bbsModifyGET(
            @RequestParam(name = "idx") String idx,
            Model model
    ) {
        BbsDTO dto = bbsService.getView(Long.parseLong(idx));
        log.info(dto);
        model.addAttribute("dto", dto);
        return "bbs/modify";
    }

    //게시판 수정
    @PostMapping("/modify")
    public String bbsModifyPOST(
            @RequestParam(name = "idx") long idx,
            BbsDTO dto
    ) {
        log.info(dto);
        Long rs = bbsService.bbsModify(dto);
        if (rs != 0) {
            return "redirect:/bbs/view?idx=" + idx;
        } else {
            return "redirect:/bbs/modify?idx=" + idx;
        }
    }

    //게시판 삭제
    @PostMapping("/delete")
    public String bbsDelete(
            @RequestParam(name = "idx") long idx,
            Model model
    ) {
        BbsDTO dto = bbsService.bbsDelete(idx);
        log.info(dto);
        return "redirect:/bbs/list";
    }
}