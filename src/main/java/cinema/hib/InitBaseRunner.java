package cinema.hib;

import cinema.hib.model.AgeLimit;
import cinema.hib.model.Film;
import cinema.hib.repository.FilmRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class InitBaseRunner implements CommandLineRunner {

    @Autowired
    FilmRepository filmRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("uk"));
        filmRepository.deleteAll();
        for (int i = 0; i < 15; i++) {
            Film film = new Film();
            film.setId(i);
            film.setName(faker.gameOfThrones().character());
            film.setDuration(faker.random().nextInt(60, 120));
            film.setDescription(faker.internet().url());
            film.setRate(Math.random());
            film.setAgeLimit(AgeLimit.FROM16);
            filmRepository.save(film);
        }
    }
}
