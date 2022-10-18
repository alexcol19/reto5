package Controller;

import Model.Category;
import Model.Costume;
import Service.CostumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Costume")
//@CrossOrigin(originis = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class CostumeController {
    @Autowired
    private CostumeService costumeService;

    @GetMapping("/all")
    public Optional<Costume> getCostume(@PathVariable("id") int id){
        return costumeService.getCostume(id);
    }
    @PostMapping("/save")
    public Costume save(@RequestBody Costume costume){
        return costumeService.save(costume);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Costume update(@RequestBody Costume costume){
        return costumeService.update(costume);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return costumeService.deleteCostume(id);
    }
}

