package retos.reto3Cabin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @JhonR
 * @version 1.0
 */
@Entity
@Table(name = "cabin")
public class Cabin implements Serializable{
    /**
     * creacion de la tabla con sus campos
     * atributo id de la tabla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * atributo nombre de la tabla
     */
    private String name;
    /**
     * atributo marca de la tabla
     */
    private String brand;
    /**
     * atributo número de cuartos de la tabla
     */
    private Integer rooms;
    /**
     * atributo descripción de la tabla
     */
    private String description;
    
    /**
     * relación de muchos a uno con la tabla categoria
     */
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("cabins")
    private Category category;
    
    /**
     * relación de uno a muchos con la tabla mensajes
     */
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "cabin")
    @JsonIgnoreProperties({"cabin", "client"})
    private List<Message> messages;
    
    /**
     * relación de uno a muchos con la tabla reservaciones
     */
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "cabin")
    @JsonIgnoreProperties({"cabin", "client"})
    private List<Reservation> reservations;
    
    /**
     * getter and setters de los atributos de la tabla
     * @return id
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    
    
}
