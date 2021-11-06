package cinema.hib.repository;

import cinema.hib.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre findAllByFilms (Genre genre);
}
