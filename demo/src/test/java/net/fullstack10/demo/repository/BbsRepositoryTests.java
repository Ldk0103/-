package net.fullstack10.demo.repository;

import lombok.extern.log4j.Log4j2;
import net.fullstack10.demo.domain.BbsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BbsRepositoryTests  {
    @Autowired
    private BbsRepository bbsRepository;

    @Test
    public void testGetNow() {
        log.info("=========================");
        log.info(bbsRepository.getNow());
        log.info("=========================");
    }

    @Test
    public void testBbsRegister() {
        log.info("testBbsRegister()");
        IntStream.rangeClosed(1, 100).forEach(i -> {
            BbsEntity bbsEntity = BbsEntity.builder()
                    .title("테스트 제목" + i)
                    .content("테스트 내용" + i)
                    .user_id("user" + (i % 10 == 0 ? 10 : i % 10))
                    .reg_date(LocalDateTime.now())
                    .display_date(new SimpleDateFormat("yyyy-MM-dd").format(
                            new Date(2025 - 1900, 4, (i % 10 == 0 ? 10 : i % 10)
                            )
                    ))
                    .build();
            BbsEntity result = bbsRepository.save(bbsEntity);
            log.info(result.toString());
        });
    }

    @Test
    public void testSelectAll() {
        log.info("testSelectAll()");
        List<BbsEntity> result = bbsRepository.findAll();
        result.forEach(System.out::println);
    }

    @Test
    public void testBbsModify() {
        log.info("testBbsModify()");
//		Optional<BbsEntity> result = bbsRepository.findById(1);
//		BbsEntity bbsEntity = result.orElse(
//				BbsEntity.builder()
//						.title("수정 테스트 제목 1")
//						.content("수정 테스트 내용 1")
//						.user_id("user1")
//						.display_date("2025-05-20")
//						.build()
//		);

        BbsEntity bbsEntity = BbsEntity.builder()
                .idx(1L)
                .title("수정 테스트 제목 1")
                .content("수정 테스트 내용 1")
                .user_id("user1")
                .display_date("2025-05-20")
                .build();
//		bbsEntity.modify(1L, "user1", "수정 테스트 제목 1", "수정 테스트 내용 1", "2025-05-20");
        BbsEntity resultEntity = bbsRepository.save(bbsEntity);
        log.info("bbsEntity: {}", bbsEntity.toString());
        log.info("resultEntity: {}", resultEntity.toString());
    }

//    @Test
//    public void testBbsDelete() {
//        log.info("testBbsDelete()");
//        bbsRepository.deleteById(101);
//    }

    @Test
    public void testBbsList(){
        log.info("testBbsList()");
        // PageRequest.of() 메서드로 페이징 및 정렬 기준을 지정
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        Page<BbsEntity> result = bbsRepository.findAll(pageable);

        List<BbsEntity> bbsList = result.getContent();

//		log.info(result.toString());
        bbsList.stream().forEach(log::info);
    }

}
