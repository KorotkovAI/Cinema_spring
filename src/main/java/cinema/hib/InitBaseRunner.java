package cinema.hib;

import cinema.hib.model.AgeLimit;
import cinema.hib.model.Film;
import cinema.hib.model.Genre;
import cinema.hib.model.Hall;
import cinema.hib.repository.FilmRepository;
import cinema.hib.repository.GenreRepository;
import cinema.hib.repository.HallRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class InitBaseRunner implements CommandLineRunner {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    HallRepository hallRepository;

    @Override
    public void run(String... args) throws Exception {
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
    }
}
