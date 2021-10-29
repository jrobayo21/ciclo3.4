package retos.reto3Cabin.service;

import retos.reto3Cabin.model.StatusReservas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retos.reto3Cabin.model.ContadorClientes;
import retos.reto3Cabin.model.Reservation;
import retos.reto3Cabin.repository.RepositoryReservation;

/**
 *
 * @JhonR 
 * @version 1.0
 */
@Service
public class ServicesReservation {

    /**
     * creación de variable de tipo Repositorio con la anotación
     */
    @Autowired
    private RepositoryReservation mCrReservation;

    /**
     * metodo para obtener todos los datos de la tabla reservaciones
     *
     * @return List de clase Reservacion
     */
    public List<Reservation> getAll() {
        return mCrReservation.getAll();
    }

    /**
     * metodo para obtener dato de la tabla reservaciones por Id
     *
     * @param reservationId
     * @return Optional de clase Reservacion
     */
    public Optional<Reservation> getReservation(int reservationId) {
        return mCrReservation.getReservation(reservationId);
    }

    /**
     * metodo para registrar valores en la tabla reservaciones
     *
     * @param reservation
     * @return valor de calse Reservacion
     */
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return mCrReservation.save(reservation);
        } else {
            Optional<Reservation> dateVerify = mCrReservation.getReservation(reservation.getIdReservation());
            if (dateVerify.isEmpty()) {
                return mCrReservation.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    /**
     * metodo para actualizar un dato de la tabla Reservaciones
     *
     * @param reservation
     * @return valor de calse Reservacion
     */
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> dateVerify = mCrReservation.getReservation(reservation.getIdReservation());
            if (!dateVerify.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    dateVerify.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    dateVerify.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    dateVerify.get().setStatus(reservation.getStatus());
                }
                mCrReservation.save(dateVerify.get());
                return dateVerify.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    /**
     * metodo para borrar un dato de la tabla Reservaciones por Id
     *
     * @param reservationId
     * @return boolean
     */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            mCrReservation.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public StatusReservas getRepStatusRes() {
        List<Reservation> completed = mCrReservation.ReservationStatus("completed");
        List<Reservation> cancelled = mCrReservation.ReservationStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }

    public List<Reservation> reporteTiempoServicio(String datoA, String datoB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date datoUno = new Date();
        Date datoDos = new Date();

        try {
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        } catch (ParseException evt) {
            evt.printStackTrace();
        }
        if (datoUno.before(datoDos)) {
            return mCrReservation.ReservacionTiempoRepositorio(datoUno, datoDos);
        } else {
            return new ArrayList<>();
        }
    }

    public List<ContadorClientes> reporteClientesServicio() {
        return mCrReservation.getClientesRepositorio();
    }

}
