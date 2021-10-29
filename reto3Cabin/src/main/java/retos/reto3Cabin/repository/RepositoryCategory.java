
package retos.reto3Cabin.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import retos.reto3Cabin.model.Category;

/**
 *
 * @JhonR
 * @version 1.0
 */
@Repository
public class RepositoryCategory {
    @Autowired
    private InterfaceCategory crudCategory;
    
    public List<Category> getAll(){
        return (List<Category>) crudCategory.findAll();
    }
    public Optional<Category> getCategory(int id){
        return crudCategory.findById(id);
    }
    public Category save(Category categoria){
        return crudCategory.save(categoria);
    }
    public void delete(Category categoria){
        crudCategory.delete(categoria);
    }
}
