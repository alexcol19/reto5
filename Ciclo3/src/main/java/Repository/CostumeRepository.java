package Repository;

import Model.Costume;
import Repository.RepositoryCrudRepository.CostumeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CostumeRepository {
    @Autowired
    private CostumeCrudRepository costumeCrudRepository;

    public List<Costume> getAll(){
        return (List<Costume>) costumeCrudRepository.findAll();
    }
    public Optional<Costume> getCostume(int id){
        return costumeCrudRepository.findById(id);
    }
    public Costume save(Costume costume){
        return costumeCrudRepository.save(costume);
    }
    public void delete(Costume costume){
        costumeCrudRepository.delete(costume);
    }

}
