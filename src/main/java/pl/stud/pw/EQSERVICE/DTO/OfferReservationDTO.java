package pl.stud.pw.EQSERVICE.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class OfferReservationDTO {

    /**
     * id is adequate to record from OfferReservation entity(table)
     */
    private Long id;
    private String name;
    private String description;
    private Double cost;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    public OfferReservationDTO(){}

    public OfferReservationDTO(Long id, String name, String description,
                               Double cost,String status, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
