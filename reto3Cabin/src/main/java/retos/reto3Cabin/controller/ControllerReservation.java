package retos.reto3Cabin.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import retos.reto3Cabin.model.ContadorClientes;
import retos.reto3Cabin.model.Reservation;
import retos.reto3Cabin.service.ServicesReservation;
import retos.reto3Cabin.model.StatusReservas;

/**
 *
 * @JhonR @version 1.0
 */
@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ControllerReservation {

    @Autowired
    private ServicesReservation serviciosReservation;

    @GetMapping("/all")
    public List<Reservation> getReservations() {
        return serviciosReservation.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int reservationId) {
        return serviciosReservation.getReservation(reservationId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return serviciosReservation.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return serviciosReservation.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int reservationId) {
        return serviciosReservation.deleteReservation(reservationId);
    }

    @GetMapping("/report-status")
    public StatusReservas getReservas() {
        return serviciosReservation.getRepStatusRes();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservasTiempo(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) {
        return serviciosReservation.reporteTiempoServicio(dateOne, dateTwo);
    }

    @GetMapping("/report-clients")
    public List<ContadorClientes> getClientes() {
        return serviciosReservation.reporteClientesServicio();
    }

}
