package pl.stud.pw.EQSERVICE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.stud.pw.EQSERVICE.Entity.Employee;
import pl.stud.pw.EQSERVICE.Entity.OfferReservation;
import pl.stud.pw.EQSERVICE.Entity.WebUser;
import java.util.List;

@Repository
public interface OfferReservationRepository extends JpaRepository<OfferReservation,Long> {

    List<OfferReservation> findOfferReservationByWebUser(WebUser webUser);
    List<OfferReservation> findOfferReservationsByEmployee(Employee employee);
    void deleteAllByEmployee(Employee employee);
    void deleteAllByWebUser(WebUser webUser);

    @Modifying
    @Query(value = "UPDATE OfferReservation off SET off.status='Accepted' WHERE off.id=:reservationId")
    void updateReservationStatus(@Param("reservationId")Long reservationId);
}
