package pl.stud.pw.EQSERVICE.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.stud.pw.EQSERVICE.DTO.OfferReservationDTO;
import pl.stud.pw.EQSERVICE.DTO.ReservationInfoDTO;
import pl.stud.pw.EQSERVICE.Entity.Employee;
import pl.stud.pw.EQSERVICE.Entity.Offer;
import pl.stud.pw.EQSERVICE.Entity.OfferReservation;
import pl.stud.pw.EQSERVICE.Entity.WebUser;
import pl.stud.pw.EQSERVICE.Repository.OfferRepository;
import pl.stud.pw.EQSERVICE.Repository.OfferReservationRepository;
import pl.stud.pw.EQSERVICE.Repository.WebUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferReservationRepository offerReservationRepository;
    private final WebUserRepository webUserRepository;

    public OfferService(OfferRepository offerRepository,
                        OfferReservationRepository offerReservationRepository,
                        WebUserRepository webUserRepository) {
        this.offerRepository = offerRepository;
        this.offerReservationRepository = offerReservationRepository;
        this.webUserRepository = webUserRepository;
    }

    @Transactional
    public List<Offer> findAllOffers(){
        return offerRepository.findAll();
    }

    public Offer findOfferById(Long id){
        Optional<Offer> searchedOffer = offerRepository.findById(id);
        return searchedOffer.orElse(null);
    }

    public void deleteReservation(OfferReservation offerReservation){
        offerReservationRepository.delete(offerReservation);
    }

    public OfferReservation findOfferReservationById(Long id){
        Optional<OfferReservation> searchedReservation = offerReservationRepository.findById(id);
        return searchedReservation.orElse(null);
    }

    @Transactional
    public List<OfferReservation> findAllReservations() {
        return offerReservationRepository.findAll();
    }

    @Transactional
    public List<ReservationInfoDTO> findEmployeeReservedTasks(Employee employee){
        return offerReservationRepository.findOfferReservationsByEmployee(employee)
                .stream()
                .map(offerReservation -> new ReservationInfoDTO(
                        offerReservation.getId(),
                        findOfferById(offerReservation.getOffer().getId()).getName(),
                        webUserRepository.getById(offerReservation.getWebUser().getId()).getFirstName(),
                        webUserRepository.getById(offerReservation.getWebUser().getId()).getLastName(),
                        webUserRepository.getById(offerReservation.getWebUser().getId()).getEmail(),
                        offerReservation.getStatus(),
                        offerReservation.getDateTime()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<OfferReservationDTO> findUsersReservations(WebUser webUser){
        return offerReservationRepository.findOfferReservationByWebUser(webUser)
                .stream()
                .map(offerReservation -> new OfferReservationDTO(
                        offerReservation.getId(),
                        findOfferById(offerReservation.getOffer().getId()).getName(),
                        findOfferById(offerReservation.getOffer().getId()).getDescription(),
                        findOfferById(offerReservation.getOffer().getId()).getCost(),
                        offerReservation.getStatus(),
                        offerReservation.getDateTime()))
                .collect(Collectors.toList());
    }

    public void saveOfferReservation(OfferReservation offerReservation){
        offerReservationRepository.save(offerReservation);
    }

    public void removeOffer(Offer offer){
        offerRepository.delete(offer);
    }

    @Transactional
    public void updateReservationStatus(OfferReservation offerReservation) {
        offerReservationRepository.updateReservationStatus(offerReservation.getId());
    }

}
