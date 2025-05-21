package net.fullstack10.demo.service;

import lombok.extern.log4j.Log4j2;
import net.fullstack10.demo.domain.BbsEntity;
import net.fullstack10.demo.dto.BbsDTO;
import net.fullstack10.demo.repository.BbsRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Log4j2
@SpringBootTest
public class BbsServiceTests {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BbsRepository bbsRepository;
    @Autowired
    private BbsServiceIf bbsService;

    @Test
    public void testGetTotalCount() {
        log.info("================================");
        long totalCount = bbsRepository.getTotalCount();
        log.info("BbsServices >> testGetTotalCount >> totalCount : {}", + totalCount);
        log.info("================================");
    }

    @Test
    public void testBbsList() {
        log.info("================================");
//        bbsService.bbsList().forEach(bbsDTO -> {
//           log.info("bsServiceTests >> testBbsList >> bbsDTO : {}", bbsDTO);
//        });
        log.info("================================");
    }

    @Test
    public void testBbsView() {
        log.info("================================");
        BbsDTO dto = bbsService.getView(1);
        log.info("bsServiceTests >> testBbsView >> dto : {}", dto);
        log.info("================================");
    }

    @Test
    public void testBbsRegist() {
        log.info("================================");
        BbsDTO dto = BbsDTO.builder()
                .title("테스트 타이틀")
                .content("테스트 내용")
                .user_id("choho01")
                .display_date("2025-05-20")
                .reg_date(LocalDateTime.now())
                .build();
        long rtnResult = bbsService.bbsRegist(dto);
        log.info("bsServiceTests >> testBbsView >> rtnResult : {}", rtnResult);
        log.info("================================");
    }

    @Test
    public void testBbsModify() {
        log.info("================================");
        BbsDTO dto = BbsDTO.builder()
                .title("테스트 타이틀 수정 101")
                .content("테스트 내용 수정 101")
                .user_id("choho120")
                .display_date("2025-05-20")
                .reg_date(LocalDateTime.now())
                .build();
        long rtnResult = bbsService.bbsModify(dto);
        log.info("bsServiceTests >> testBbsView >> rtnResult : {}", rtnResult);
        log.info("================================");
    }
}
