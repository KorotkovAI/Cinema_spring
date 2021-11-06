package cinema.hib.repository;

import cinema.hib.model.Film;
import cinema.hib.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Slot> getAllByName (String name);

    Film findAllByGenres (Film film);

    Optional <Film> findFilmByName (String name);
}
