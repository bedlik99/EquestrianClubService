package pl.stud.pw.EQSERVICE.DTO;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ReservationInfoDTO {

    /**
     * reservationId is adequate to record from OfferReservation entity(table)
     */
    private Long reservationId;
    private String offersName;
    private String clientsFirstName;
    private String clientsLastName;
    private String clientsEmail;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    public ReservationInfoDTO(){}

    public ReservationInfoDTO(Long reservationId, String offersName,
                              String clientsFirstName, String clientsLastName,
                              String clientsEmail, String status, LocalDateTime dateTime) {
        this.reservationId = reservationId;
        this.offersName = offersName;
        this.clientsFirstName = clientsFirstName;
        this.clientsLastName = clientsLastName;
        this.clientsEmail = clientsEmail;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getOffersName() {
        return offersName;
    }

    public void setOffersName(String offersName) {
        this.offersName = offersName;
    }

    public String getClientsFirstName() {
        return clientsFirstName;
    }

    public void setClientsFirstName(String clientsFirstName) {
        this.clientsFirstName = clientsFirstName;
    }

    public String getClientsLastName() {
        return clientsLastName;
    }

    public void setClientsLastName(String clientsLastName) {
        this.clientsLastName = clientsLastName;
    }

    public String getClientsEmail() {
        return clientsEmail;
    }

    public void setClientsEmail(String clientsEmail) {
        this.clientsEmail = clientsEmail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
