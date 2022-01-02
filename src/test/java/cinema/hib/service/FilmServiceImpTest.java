package cinema.hib.service;

import cinema.hib.HibApplication;
import cinema.hib.TestConfig;
import cinema.hib.dto.mapper.FilmMapper;
import cinema.hib.dto.model.FilmDto;
import cinema.hib.dto.model.FilmDtoShort;
import cinema.hib.model.AgeLimit;
import cinema.hib.model.Film;
import cinema.hib.repository.FilmRepository;
import cinema.hib.service.impl.FilmServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = {HibApplication.class, TestConfig.class})
public class FilmServiceImpTest {

    @Resource
    FilmServiceImpl filmService;

    @Mock
    FilmRepository filmRepository;

    @Mock
    FilmMapper mapper;

    @BeforeAll
    public void before() {
        MockitoAnnotations.openMocks(this);
       // filmService = MethodValidationProxyFactory.createProxy(filmService);
    }

    @Test
    public void getFilmById() {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(42);
        filmDto.setName("First");
        filmDto.setAgeLimit(AgeLimit.ADULT);
        filmDto.setDescription("My first film");
        filmDto.setDuration(50);
        when(mapper.toFilm(filmDto)).thenReturn(new Film());
        when(filmRepository.save(mapper.toFilm(filmDto))).thenReturn(new Film());
       // FilmDto filmSaved = filmService.saveDtoToFilm(filmDto);
        //FilmDto result = filmService.getFilmById(1);
//System.out.println(filmSaved.getId());
        assertEquals(filmDto, "");
    }

    @Test
    public void nullGetFilmById() {
       // when(filmService.getFilmById(-1)).thenCallRealMethod();
        FilmDto result = filmService.getFilmById(-1);
        assertNull(result);
    }

    @Test
    public void falseGetFilmByIdMoreThenHave() {
        FilmDto result = filmService.getFilmById(145);
        assertNull(result);
    }

    //TODO
    @Test
    public void nullSaveDtoShortToFilm() {
        //assertThatExceptionOfType(ConstraintViolationException.class).
              //  isThrownBy(() -> filmService.saveDtoToFilm(null));
        filmService.saveDtoToFilm(null);
//        assertNotNull();
    }
    /*
//TODO
    @Test
    public void saveDtoShortToFilm() {
        FilmDtoShort filmDto = new FilmDtoShort();
        filmDto.setName("First");
        filmDto.setAgeLimit(AgeLimit.ADULT);
        filmDto.setDescription("My first film");
        filmDto.setDuration(50);

        when(filmService.saveDtoShortToFilm(filmDto)).thenCallRealMethod();

        FilmDto result = filmService.saveDtoShortToFilm(filmDto);

        assertEquals(filmDto.getName(), result.getName());
        assertEquals(filmDto.getAgeLimit(), result.getAgeLimit());
        assertEquals(filmDto.getDescription(), result.getDescription());
        assertEquals(filmDto.getDuration(), result.getDuration());
    }

    @Test
    public void testtt() {
        when(filmService.findAll()).thenCallRealMethod();
        when(filmRepository.findAll()).thenCallRealMethod();

        System.out.println(filmService.findAll());
        assertNotNull(filmService.findAll());
    }



 */
}
