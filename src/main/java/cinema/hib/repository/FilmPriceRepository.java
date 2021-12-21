package cinema.hib.repository;

import cinema.hib.model.Film;
import cinema.hib.model.FilmPrice;
import cinema.hib.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmPriceRepository extends JpaRepository<Film, Long> {

    List<FilmPrice> getAllBySlotsContains(Slot slot);
}
