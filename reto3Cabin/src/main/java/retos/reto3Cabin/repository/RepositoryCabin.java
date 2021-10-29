
package retos.reto3Cabin.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import retos.reto3Cabin.model.Cabin;

/**
 *
 * @JhonR
 * @version 1.0
 */
@Repository
public class RepositoryCabin {
    @Autowired
    private InterfaceCabin crudCabin;
    
    public List<Cabin> getAll(){
        return (List<Cabin>) crudCabin.findAll();
    }
    
    public Optional<Cabin> getCabin(int id){
        return crudCabin.findById(id);
    }
    
    public Cabin save(Cabin cabana){
        return crudCabin.save(cabana);
    }
    
    public void delete(Cabin cabana){
        crudCabin.delete(cabana);
    }
    
}
