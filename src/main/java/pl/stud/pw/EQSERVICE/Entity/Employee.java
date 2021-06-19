package pl.stud.pw.EQSERVICE.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workingHours;
    private Double grossWage;
    private Double bonus;

    @OneToOne(mappedBy = "employee")
    private WebUser webUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<OfferReservation> offerReservation;

    public Employee(){}

    public Employee(String workingHours, Double grossWage, Double bonus, WebUser webUser, List<OfferReservation> offerReservation) {
        this.workingHours = workingHours;
        this.grossWage = grossWage;
        this.bonus = bonus;
        this.webUser = webUser;
        this.offerReservation = offerReservation;
    }

    public List<OfferReservation> getOfferReservation() {
        return offerReservation;
    }

    public void setOfferReservation(List<OfferReservation> offerReservation) {
        this.offerReservation = offerReservation;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public Double getGrossWage() {
        return grossWage;
    }

    public void setGrossWage(Double grossWage) {
        this.grossWage = grossWage;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
