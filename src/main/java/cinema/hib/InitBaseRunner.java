package cinema.hib;

import cinema.hib.model.*;
import cinema.hib.repository.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

@Component
public class InitBaseRunner implements CommandLineRunner {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SlotRepository slotRepository;

    @Autowired
    SheduleRepository sheduleRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("uk"));
        filmRepository.deleteAll();
        for (int i = 0; i < 15; i++) {
            Film film = new Film();
            film.setId(i);
            film.setName(faker.gameOfThrones().character());
            film.setDuration(faker.random().nextInt(60, 120));
            film.setDescription("static/description/1.txt");
            film.setRate(Math.random());
            film.setAgeLimit(AgeLimit.FROM16);
            filmRepository.save(film);
        }

        genreRepository.deleteAll();
        for (int i = 0; i < 5; i++) {
            Genre genre = new Genre();
            genre.setName(faker.dog().name() + "grdtrd");
            genre.setId(i);
            genreRepository.save(genre);
        }

        hallRepository.deleteAll();
        Hall hall1 = new Hall();
        hall1.setId(1);
        hall1.setName("First");
        hallRepository.save(hall1);
        Hall hall2 = new Hall();
        hall2.setId(2);
        hall2.setName("Second");
        hallRepository.save(hall2);
/*
        sheduleRepository.deleteAll();
        Shedule shedule1 = new Shedule();
        shedule1.setHall(hall1);
        shedule1.setId(1);
        shedule1.setSlots(new ArrayList<>());

        slotRepository.deleteAll();
        Slot slot1 = new Slot();
        slot1.setId(1);
        slot1.setDateOfFilm(LocalDate.of(2022, 1, 23));
        slot1.setFilm(filmRepository.getById(1L));
        slot1.setStartTime(LocalTime.of(11, 0));
        slot1.setEndTime(LocalTime.of(15, 0));
        slot1.setShedule(shedule1);
 */
    }
}
