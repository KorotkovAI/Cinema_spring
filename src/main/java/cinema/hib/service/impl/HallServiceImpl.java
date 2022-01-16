package cinema.hib.service.impl;

import cinema.hib.dto.mapper.HallMapper;
import cinema.hib.dto.model.HallDto;
import cinema.hib.model.Hall;
import cinema.hib.repository.HallRepository;
import cinema.hib.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private HallRepository hallRepository;

    @Resource
    private HallMapper hallMapper;

    @Override
    public List<HallDto> getAll() {
        return hallMapper.toHallDtoList(hallRepository.findAll());
    }

    @Override
    public HallDto getHallById(int id) {
        if (id > 0) {
            Hall currenthall = hallRepository.getById(id);
            return hallMapper.toHallDto(currenthall);
        }
        return null;
    }

    @Override
    public Hall getHallByName(String hallName) {
        if (hallName != null) {
            return hallRepository.getByName(hallName);
        }
        return null;
    }

    @Override
    public HallDto save(HallDto hallDto) {
        Hall hall = hallRepository.save(hallMapper.toHall(hallDto));
        return hallMapper.toHallDto(hall);
    }
}
