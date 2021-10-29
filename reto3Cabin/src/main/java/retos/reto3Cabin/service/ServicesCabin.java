package retos.reto3Cabin.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retos.reto3Cabin.model.Cabin;
import retos.reto3Cabin.repository.RepositoryCabin;

/**
 *
 * @JhonR 
 * @version 1.0
 */
@Service
public class ServicesCabin {

    @Autowired
    private RepositoryCabin metodosCrudCabin;

    public List<Cabin> getAll() {
        return metodosCrudCabin.getAll();
    }

    public Optional<Cabin> getCabin(int cabinId) {
        return metodosCrudCabin.getCabin(cabinId);
    }

    public Cabin save(Cabin cabana) {
        if (cabana.getId() == null) {
            return metodosCrudCabin.save(cabana);
        } else {
            Optional<Cabin> e = metodosCrudCabin.getCabin(cabana.getId());
            if (e.isEmpty()) {
                return metodosCrudCabin.save(cabana);
            } else {
                return cabana;
            }
        }
    }

    public Cabin update(Cabin cabana) {
        if (cabana.getId() != null) {
            Optional<Cabin> e = metodosCrudCabin.getCabin(cabana.getId());
            if (!e.isEmpty()) {
                if (cabana.getBrand() != null) {
                    e.get().setBrand(cabana.getBrand());
                }
                if (cabana.getName() != null) {
                    e.get().setName(cabana.getName());
                }
                if (cabana.getRooms() != null) {
                    e.get().setRooms(cabana.getRooms());
                }
                if (cabana.getDescription() != null) {
                    e.get().setDescription(cabana.getDescription());
                }
                if (cabana.getCategory() != null) {
                    e.get().setCategory(cabana.getCategory());
                }
                metodosCrudCabin.save(e.get());
                return e.get();
            } else {
                return cabana;
            }
        } else {
            return cabana;
        }
    }

    public boolean deleteCabin(int cabinId) {
        Boolean aBoolean = getCabin(cabinId).map(cabin -> {
            metodosCrudCabin.delete(cabin);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
