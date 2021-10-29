
package retos.reto3Cabin.repository;

import org.springframework.data.repository.CrudRepository;
import retos.reto3Cabin.model.Message;

/**
 *
 * @JhonR
 * @version 1.0
 */
public interface InterfaceMessage extends CrudRepository<Message, Integer>{
    
}
