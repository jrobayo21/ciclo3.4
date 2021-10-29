
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
import retos.reto3Cabin.service.ServicesCabin;
import retos.reto3Cabin.model.Cabin;

/**
 *
 * @JhonR
 * @version 1.0
 */
@RestController
@RequestMapping("/api/Cabin")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ControllerCabin {
    @Autowired
    private ServicesCabin servicioCabana;
    
    @GetMapping("/all")
    public List<Cabin> getCabin(){
        return servicioCabana.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Cabin> getCabinId(@PathVariable("id") int cabinId){
        return servicioCabana.getCabin(cabinId);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cabin save(@RequestBody Cabin cabana){
        return servicioCabana.save(cabana);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Cabin update(@RequestBody Cabin cabana){
        return servicioCabana.update(cabana);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int cabinId) {
        return servicioCabana.deleteCabin(cabinId);
    }
}
