package cinema.hib.repository;

import cinema.hib.model.Hall;
import cinema.hib.model.Shedule;
import cinema.hib.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheduleRepository extends JpaRepository<Shedule, Long> {

    Shedule getShedulesByHall (Hall hall);

    Shedule getSheduleBySlotsContains (Slot slot);

}
