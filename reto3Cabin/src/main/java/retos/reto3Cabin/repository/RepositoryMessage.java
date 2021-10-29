
package retos.reto3Cabin.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import retos.reto3Cabin.model.Message;

/**
 *
 * @JhonR
 * @version 1.0
 */
@Repository
public class RepositoryMessage {
    @Autowired
    private InterfaceMessage crudMessage;
    
    public List<Message> getAll(){
        return (List<Message>) crudMessage.findAll();
    }
    public Optional<Message> getMessage(int id){
        return crudMessage.findById(id);
    }
    public Message save(Message mensaje){
        return crudMessage.save(mensaje);
    }
    public void delete(Message mensaje){
        crudMessage.delete(mensaje);
    }
    
}
