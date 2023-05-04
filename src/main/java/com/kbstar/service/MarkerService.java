package com.kbstar.service;

import com.kbstar.dto.Marker;
import com.kbstar.dto.MarkerSearch;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.MarkerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MarkerService implements KBService<Integer, Marker> {
    @Autowired
    MarkerMapper mapper;
    /**
     * 등록 및 가입 진행
     * argument: Object
     * return: null
     *
     * @param marker
     */
    @Override
    public void register(Marker marker) throws Exception {
        mapper.insert(marker);
    }

    @Override
    public void remove(Integer i) throws Exception {
        mapper.delete(i);
    }

    @Override
    public void modify(Marker marker) throws Exception {
        mapper.update(marker);
    }

    @Override
    public Marker get(Integer i) throws Exception {
        return mapper.select(i);
    }

    @Override
    public List<Marker> get() throws Exception {
        return mapper.selectall();
    }

    public List<Marker> getloc(String loc) throws Exception{
        return mapper.getloc(loc);
    }

    public List<Marker> search(MarkerSearch ms) throws Exception{
        return mapper.search(ms);
    }
}
