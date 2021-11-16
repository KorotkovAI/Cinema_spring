package cinema.hib.repository;

import cinema.hib.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findAllByFilmName (String filmName);
}
