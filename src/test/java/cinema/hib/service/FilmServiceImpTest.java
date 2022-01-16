package cinema.hib.service;

import cinema.hib.dto.mapper.FilmMapper;
import cinema.hib.dto.mapper.GenreMapper;
import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.FilmDtoShort;
import cinema.hib.model.AgeLimit;
import cinema.hib.model.Film;
import cinema.hib.repository.FilmRepository;
import cinema.hib.service.impl.FilmServiceImpl;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class FilmServiceImpTest {

    @Resource
    FilmServiceImpl filmService;

    @MockBean
    FilmRepository filmRepository;

    @MockBean
    FilmMapper mapper;

    @MockBean
    GenreMapper genreMapper;

    @TestConfiguration
    static class filmServiceImplTestConfiguration {
        @Bean
        public FilmServiceImpl filmService() {
            return new FilmServiceImpl();
        }
    }

    @Before
    public void before() {

    }

    @Test
    public void getFilmById() {
        Film film = new Film();
        film.setId(42);
        film.setName("First");
        film.setAgeLimit(AgeLimit.ADULT);
        film.setDescription("My first film");
        film.setDuration(50);
        when(mapper.toFilmDto(film)).thenCallRealMethod();
        when(filmRepository.getById(42L)).thenReturn(film);

        assertEquals(filmService.getFilmById(42L).getId(), film.getId());
        assertEquals(filmService.getFilmById(42L).getName(), film.getName());
        assertEquals(filmService.getFilmById(42L).getDuration(), film.getDuration());
        assertEquals(filmService.getFilmById(42L).getAgeLimit(), film.getAgeLimit());
        assertEquals(filmService.getFilmById(42L).getDescription(), film.getDescription());
    }

    @Test
    public void nullGetFilmById() {
        FilmDto result = filmService.getFilmById(-2);
        assertNull(result);
    }

    @Test
    public void falseGetFilmByIdMoreThenHave() {
        FilmDto result = filmService.getFilmById(145);
        assertNull(result);
    }

    @Test
    public void positivGetFilmByName() {
        Film film = new Film();
        film.setId(42);
        film.setName("First");
        film.setAgeLimit(AgeLimit.ADULT);
        film.setDescription("My first film");
        film.setDuration(50);
        Film film2 = new Film();
        film2.setId(3);
        film2.setName("Second");
        film2.setAgeLimit(AgeLimit.FROM16);
        film2.setDescription("My second film");
        film2.setDuration(155);
        List<Film> films = new ArrayList<>();
        films.add(film);
        films.add(film2);
        when(mapper.toFilmDto(film)).thenCallRealMethod();
        when(filmRepository.findAll()).thenReturn(films);

        assertEquals(filmService.getFilmByName("First").getId(), film.getId());
        assertEquals(filmService.getFilmByName("First").getName(), film.getName());
        assertEquals(filmService.getFilmByName("First").getDuration(), film.getDuration());
        assertEquals(filmService.getFilmByName("First").getAgeLimit(), film.getAgeLimit());
        assertEquals(filmService.getFilmByName("First").getDescription(), film.getDescription());
    }

    @Test
    public void nullNameGetFilmByName() {
        assertNull(filmService.getFilmByName(null));
    }

    @Test(expected = EntityNotFoundException.class)
    public void notFoundGetFilmByName() {
        filmService.getFilmByName("Bruce");
    }

    @Test
    public void positivSaveDtoShortToFiilm() {
        FilmDtoShort filmDto = new FilmDtoShort();
        filmDto.setName("First");
        filmDto.setAgeLimit(AgeLimit.ADULT);
        filmDto.setDescription("My first film");
        filmDto.setDuration(50);

        Film film = new Film();
        film.setId(2);
        film.setName("First");
        film.setAgeLimit(AgeLimit.ADULT);
        film.setDescription("My first film");
        film.setDuration(50);

        when(mapper.toFilmFromShort(filmDto)).thenCallRealMethod();
        when(filmRepository.save(mapper.toFilmFromShort(filmDto))).thenReturn(film);
        when(mapper.toFilmDto(film)).thenCallRealMethod();
        when(genreMapper.toGenreDtoList(film.getGenres())).thenCallRealMethod();
        FilmDto filmDtoNew = filmService.saveDtoShortToFilm(filmDto);
        assertEquals(filmDtoNew.getId(), film.getId());
        /*
        assertEquals(filmService.saveDtoShortToFilm(filmDto).getDescription(), filmDto.getDescription());
        assertEquals(filmService.saveDtoShortToFilm(filmDto).getAgeLimit(), filmDto.getAgeLimit());
        assertEquals(filmService.saveDtoShortToFilm(filmDto).getDescription(), filmDto.getDescription());
        assertEquals(filmService.saveDtoShortToFilm(filmDto).getDuration(), filmDto.getDuration());
        assertEquals(filmService.saveDtoShortToFilm(filmDto).getId(), film.getId());

         */
    }

    @Test
    public void nullDtoSaveDtoShortToFiilm() {
        assertNull(filmService.saveDtoShortToFilm(null));
    }

    @Test
    public void positivSaveDtoToFilm() {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(2);
        filmDto.setName("First");
        filmDto.setAgeLimit(AgeLimit.ADULT);
        filmDto.setDescription("My first film");
        filmDto.setDuration(50);

        Film film = new Film();
        film.setId(2);
        film.setName("First");
        film.setAgeLimit(AgeLimit.ADULT);
        film.setDescription("My first film");
        film.setDuration(50);

        when(mapper.toFilm(filmDto)).thenCallRealMethod();
        when(filmRepository.save(mapper.toFilm(filmDto))).thenReturn(film);
        when(mapper.toFilmDto(film)).thenCallRealMethod();

        FilmDto result = filmService.saveDtoToFilm(filmDto);
        assertEquals(result.getName(), filmDto.getName());
        assertEquals(result.getDescription(), filmDto.getDescription());
        assertEquals(result.getAgeLimit(), filmDto.getAgeLimit());
        assertEquals(result.getDescription(), filmDto.getDescription());
        assertEquals(result.getDuration(), filmDto.getDuration());
        assertEquals(result.getId(), filmDto.getId());
    }

    @Test
    public void nullDtoSaveDtoToFilm() {
        assertNull(filmService.saveDtoToFilm(null));
    }

    @Test
    public void positivFindAll() {
        Film film = new Film();
        film.setId(42);
        film.setName("First");
        film.setAgeLimit(AgeLimit.ADULT);
        film.setDescription("My first film");
        film.setDuration(50);
        Film film2 = new Film();
        film.setId(3);
        film.setName("Second");
        film.setAgeLimit(AgeLimit.FROM16);
        film.setDescription("My second film");
        film.setDuration(155);
        List<Film> films = new ArrayList<>();
        films.add(film);
        films.add(film2);

        when(filmRepository.findAll()).thenReturn(films);
        when(mapper.toFilmDtoList(films)).thenCallRealMethod();

        assertEquals(filmService.findAll().size(), films.size());
    }

    @Test
    public void nullFilmDtoDeleteFilm() {
        assertFalse(filmService.deleteFilm(null));
    }

    @Test
    public void positivDeleteFilm() {
        Film film = new Film();
        film.setId(42);
        film.setName("First");
        film.setAgeLimit(AgeLimit.ADULT);
        film.setDescription("My first film");
        film.setDuration(50);
        Film film2 = new Film();
        film.setId(3);
        film.setName("Second");
        film.setAgeLimit(AgeLimit.FROM16);
        film.setDescription("My second film");
        film.setDuration(155);
        List<Film> films = new ArrayList<>();
        films.add(film);
        films.add(film2);

        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setName(film.getName());
        filmDto.setDescription(film.getDescription());
        filmDto.setAgeLimit(film.getAgeLimit());
        filmDto.setDuration(film.getDuration());

        when(mapper.toFilm(filmDto)).thenCallRealMethod();
        //when(filmRepository.delete(film)).then(films.remove(film));
    }

}
