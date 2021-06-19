package pl.stud.pw.EQSERVICE.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double cost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer")
    private List<OfferReservation> offerReservations;

    public Offer(){}

    public Offer(String name, String description, Double cost, List<OfferReservation> offerReservations) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.offerReservations = offerReservations;
    }

    public List<OfferReservation> getOfferReservations() {
        return offerReservations;
    }

    public void setOfferReservations(List<OfferReservation> offerReservations) {
        this.offerReservations = offerReservations;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Offer{" + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", cost=" + cost + '}';
    }
}
