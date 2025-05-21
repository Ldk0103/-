package net.fullstack10.demo.service;

import net.fullstack10.demo.dto.BbsDTO;

import java.util.List;

public interface BbsServiceIf {
    public long getTotlaCount();
    public List<BbsDTO> bbsList(int page_no, int page_size);
    public BbsDTO getView(long id);
    public long bbsRegist(BbsDTO dto);
    public long bbsModify(BbsDTO dto);
    public BbsDTO bbsDelete(long idx);
}
