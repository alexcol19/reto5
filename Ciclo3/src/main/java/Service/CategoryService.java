package Service;

import Model.Category;
import Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return (List<Category>) categoryRepository.getAll();
    }
    public Optional<Category> getCategory(int id){
        return categoryRepository.getById(id);
    }
    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> category1 = categoryRepository.getById(category.getId());
            if (category1.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }
    public Category update(Category category){
        if(category.getId() != null){
            Optional<Category> category1 = categoryRepository.getById(category.getId());
            if(!category1.isEmpty()){
                if (category.getDescription() != null){
                    category1.get().setDescription(category.getDescription());
                }
                if (category.getName() != null){
                    category1.get().setName(category.getName());
                }
                return categoryRepository.save(category1.get());
            }
        }
        return category;

    }
    public boolean deleteCategory(int id){
        Boolean resultado = getCategory(id).map(categoryPorEliminar ->{
            categoryRepository.delete(categoryPorEliminar);
            return true;
        }).orElse(false);

        return resultado;



}
}

