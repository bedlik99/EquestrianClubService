package pl.stud.pw.EQSERVICE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stud.pw.EQSERVICE.Entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {

}
