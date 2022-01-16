package cinema.hib.service;

import cinema.hib.dto.mapper.HallMapper;
import cinema.hib.model.Hall;
import cinema.hib.repository.HallRepository;
import cinema.hib.service.impl.HallServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class HallServiceImplTest {

    @Resource
    HallServiceImpl hallService;

    @MockBean
    HallRepository hallRepository;

    @MockBean
    HallMapper hallMapper;

    @TestConfiguration
    static class hallServiceImplTestConfiguration {
        @Bean
        public HallServiceImpl hallService() {
            return new HallServiceImpl();
        }
    }

    @Test
    public void positivGetAll() {
        Hall hall1 = new Hall();
        hall1.setId(1);
        hall1.setName("First");
        Hall hall2 = new Hall();
        hall2.setId(2);
        hall2.setName("Second");

        List<Hall> halls = new ArrayList<>();
        halls.add(hall1);
        halls.add(hall2);

        when(hallRepository.findAll()).thenReturn(halls);
        when(hallMapper.toHallDtoList(hallRepository.findAll())).thenCallRealMethod();

        assertEquals(hallService.getAll().size(), halls.size());
    }

}
