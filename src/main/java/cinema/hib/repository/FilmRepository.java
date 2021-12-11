package cinema.hib.repository;

import cinema.hib.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
