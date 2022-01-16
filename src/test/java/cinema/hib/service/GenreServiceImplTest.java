package cinema.hib.service;

import cinema.hib.dto.mapper.GenreMapper;
import cinema.hib.dto.model.GenreDto;
import cinema.hib.model.Genre;
import cinema.hib.repository.GenreRepository;
import cinema.hib.service.impl.GenreServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GenreServiceImplTest {

    @Resource
    GenreServiceImpl genreService;

    @MockBean
    GenreMapper genreMapper;

    @MockBean
    GenreRepository genreRepository;

    @TestConfiguration
    static class genreServiceImplTestConfiguration {
        @Bean
        public GenreServiceImpl genreService() {
            return new GenreServiceImpl();
        }
    }

    @Before
    public void before() {
        Genre genre = new Genre();
        genre.setId(4);
        genre.setName("Comedy");
        when(genreRepository.findById(4)).thenReturn(Optional.of(genre));

        GenreDto genreDto = new GenreDto();
        genreDto.setId(4);
        genreDto.setName("Comedy");
        when(genreMapper.toGenreDto(genreService.readById(4))).thenReturn(genreDto);

        Genre genre2 = new Genre();
        genre2.setId(3);
        genre2.setName("Documental");
        List<Genre> genresList = new ArrayList<>();
        genresList.add(genre);
        genresList.add(genre2);
        System.out.println(genresList);
        when(genreRepository.findAll()).thenReturn(genresList);
        when(genreMapper.toGenreDtoList(genreRepository.findAll())).thenCallRealMethod();
        when(genreMapper.toGenreDto(genre)).thenCallRealMethod();
        when(genreMapper.toGenreDto(genre2)).thenCallRealMethod();
    }

    @Test
    public void readByIdPositiv() {
        String genreName = "Comedy";
        Genre testGenre = genreService.readById(4);
        assertEquals(testGenre.getName(), genreName);
    }

    @Test(expected = EntityNotFoundException.class)
    public void readByIdException() {
        genreService.readById(5);
    }

    @Test
    public void dtoReadByIdPositiv() {
        String genreName = "Comedy";
        GenreDto result = genreService.dtoReadById(4);
        assertEquals(result.getName(), genreName);
    }

    @Test(expected = EntityNotFoundException.class)
    public void dtoReadByIdException() {
        genreService.dtoReadById(5);
    }


    @Test
    public void getAllPositiv() {
        List<GenreDto> genreDtos = genreService.getAll();
        System.out.println(genreDtos);
        assertEquals(2, genreDtos.size());
    }
}
