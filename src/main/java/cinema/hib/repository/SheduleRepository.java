package cinema.hib.repository;

import cinema.hib.model.Shedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SheduleRepository extends JpaRepository<Shedule, Long> {

    List<Shedule> getAllByHallName (String hallName);

    Shedule getByHallName (String hallName);
}
