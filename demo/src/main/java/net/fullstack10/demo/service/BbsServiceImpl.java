package net.fullstack10.demo.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack10.demo.domain.BbsEntity;
import net.fullstack10.demo.dto.BbsDTO;
import net.fullstack10.demo.repository.BbsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class BbsServiceImpl implements BbsServiceIf {
    private final BbsRepository bbsRepository;
    private final ModelMapper modelMapper;

    @Override
    public long getTotlaCount() {
        return bbsRepository.getTotalCount();
    }

    @Override
    public List<BbsDTO> bbsList(int page_no, int page_size ) {
        Pageable pageable = PageRequest.of(page_no, page_size, Sort.by("idx").descending());
        Page<BbsEntity> entityList = bbsRepository.findAll(pageable);
        List<BbsDTO> bbsDTOList = entityList.stream().map(entity -> modelMapper.map(entity, BbsDTO.class)).collect(Collectors.toList());
        return bbsDTOList;
    }

    @Override
    public BbsDTO getView(long id) {
        Optional<BbsEntity> bbs = bbsRepository.findById(id);
        if(bbs.isPresent()) {
            return modelMapper.map(bbs.get(), BbsDTO.class);
        }
        return null;
    }

    @Override
    public long bbsRegist(BbsDTO dto) {
        BbsEntity entity = modelMapper.map(dto, BbsEntity.class);
        BbsEntity result = bbsRepository.save(entity);
        long rtnResult = result.getIdx();
        return (result != null) ? result.getIdx() : 0;
    }

    @Override
    public long bbsModify(BbsDTO dto) {
        BbsEntity entity = modelMapper.map(dto, BbsEntity.class);
        entity.setModify_date(LocalDateTime.now());
        BbsEntity result = bbsRepository.save(entity);
        return (result != null) ? result.getIdx() : 0;
    }

    @Override
    public BbsDTO bbsDelete(long idx) {
        bbsRepository.deleteById(idx);
        return null;
    }

}
