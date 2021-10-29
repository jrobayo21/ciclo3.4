
package retos.reto3Cabin.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import retos.reto3Cabin.model.Reservation;

/**
 *
 * @JhonR
 * @version 1.0
 */
public interface InterfaceReservation extends CrudRepository<Reservation, Integer>{
    
    public List<Reservation> findAllByStatus(String status);
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateone, Date datetwo);
    
    //SELECT clientid, COUNT(*) AS total FROM reservacion group by clientid order by desc;
    @Query ("SELECT c.client, COUNT(c.client) from Reservation AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationsByClient();

}
