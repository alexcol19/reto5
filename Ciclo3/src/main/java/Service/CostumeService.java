package Service;

import Model.Costume;
import Repository.CostumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumeService {
    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll(){
        return (List<Costume>) costumeRepository.getAll();
    }
    public Optional<Costume> getCostume(int id){
        return costumeRepository.getCostume(id);
    }
    public Costume save(Costume costume){
        if(costume.getId() == null){
            return costumeRepository.save(costume);
        }else{
            Optional<Costume> costume1 = costumeRepository.getCostume(costume.getId());
            if(costume1.isEmpty()){
                return costumeRepository.save(costume);
            }else {
                return costume;
            }
        }
    }
    public Costume update(Costume costume){
        if(costume.getId() != null){
            Optional<Costume> costume1 = costumeRepository.getCostume(costume.getId());
            if(!costume1.isEmpty()){
                if (costume.getCategory() != null){
                    costume1.get().setCategory(costume.getCategory());
                }
                if (costume.getName() != null){
                    costume1.get().setName(costume.getName());
                }
                if (costume.getBrand() != null){
                    costume1.get().setBrand(costume.getBrand());
                }
                if (costume.getDescription() != null){
                    costume1.get().setDescription(costume.getDescription());
                }
                if (costume.getYear() != null){
                    costume1.get().setYear(costume.getYear());
                }
                return costumeRepository.save(costume1.get());
            }
        }
        return costume;

    }
    public boolean deleteCostume(int id){
        Boolean resultado = getCostume(id).map(costumePorEliminar ->{
            costumeRepository.delete(costumePorEliminar);
            return true;
        }).orElse(false);

        return resultado;
    }
}
