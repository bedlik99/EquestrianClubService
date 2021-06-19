package pl.stud.pw.EQSERVICE.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.stud.pw.EQSERVICE.Entity.Employee;
import pl.stud.pw.EQSERVICE.Entity.Offer;
import pl.stud.pw.EQSERVICE.Entity.OfferReservation;
import pl.stud.pw.EQSERVICE.Entity.WebUser;
import pl.stud.pw.EQSERVICE.Service.OfferService;
import pl.stud.pw.EQSERVICE.Service.WebUserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final WebUserService webUserService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public OfferController(OfferService offerService,WebUserService webUserService) {
        this.offerService = offerService;
        this.webUserService = webUserService;
    }

    @PostMapping("/reserve")
    public String chooseOffer(@RequestParam(name = "offer") Long offerId,
                              @RequestParam(name = "reservation") String offerDate,
                              @RequestParam(name = "employee") Long employeeId){
        Employee employee = webUserService.getEmployeeById(employeeId);
        Offer offer = offerService.findOfferById(offerId);
        LocalDateTime dateTime = LocalDateTime.parse(offerDate,formatter);
        WebUser webUser = webUserService.getLoggedUser();

        OfferReservation offerReservation = new OfferReservation(dateTime,"Pending",webUser,offer,employee);
        offerService.saveOfferReservation(offerReservation);
        return "redirect:/logged/offers";
    }

    @PostMapping("/removeReservation")
    public String removeReservation(@RequestParam(name = "reservationId") Long reservationId){
        offerService.deleteReservation(offerService.findOfferReservationById(reservationId));
        return "redirect:/logged/cart";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/removeOffer")
    public String removeOffer(@RequestParam(name = "deleteOfferId") Long offerId){
        Offer offerToDelete = offerService.findOfferById(offerId);
        offerService.removeOffer(offerToDelete);
        return "redirect:/logged/offers";
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/declineAppointment")
    public String declineAppoinment(@RequestParam(name = "declineServiceId") Long id){
        OfferReservation offerReservation = offerService.findOfferReservationById(id);
        offerService.deleteReservation(offerReservation);
        return "redirect:/logged/tasks";
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/acceptAppointment")
    public String acceptAppoinment(@RequestParam(name = "acceptServiceId") Long id){
        OfferReservation offerReservation = offerService.findOfferReservationById(id);
        offerService.updateReservationStatus(offerReservation);
        return "redirect:/logged/tasks";
    }

}
