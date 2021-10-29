package retos.reto3Cabin.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import retos.reto3Cabin.model.Client;

/**
 *
 * @JhonR @version 1.0
 */
@Repository
public class RepositoryClient {

    @Autowired
    private InterfaceClient crudClient;

    public List<Client> getAll() {
        return (List<Client>) crudClient.findAll();
    }

    public Optional<Client> getCliente(int id) {
        return crudClient.findById(id);
    }

    public Client save(Client cliente) {
        return crudClient.save(cliente);
    }

    public void delete(Client cliente) {
        crudClient.delete(cliente);
    }

}
