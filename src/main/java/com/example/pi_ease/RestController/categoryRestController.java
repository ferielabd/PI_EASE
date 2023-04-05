package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Category;
import com.example.pi_ease.Service.Interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Category")
public class categoryRestController {
    private ICategoryService iCategoryService;
    @GetMapping("/afficherCatgorie")
    public List<Category> afficher(){
        return iCategoryService.selectAll();
        //http://localhost:8086/swagger-ui/index.html#/category-rest-controller/afficher_2
    }
    @PostMapping("/ajouterCategorie")
    public Category ajouter ( @RequestBody Category category){
        return iCategoryService.add(category);    }
    @GetMapping("/afficherCategorieAvecId/{idC}")
    public Category afficherAvecId(@PathVariable int idC){
        return iCategoryService.selectByID(idC);
    }
    @PostMapping("/ajouterAllCtagorie")
    public List<Category> addAll(@RequestBody List<Category> list){
        return iCategoryService.addAll(list);
    }
    @PutMapping("/modifierCategorie")
    public Category edit(@RequestBody Category category){
        return iCategoryService.edit(category);
    }
    @DeleteMapping("/supprimerCategorieById")
    public void deleteById(@RequestParam int idC){
        iCategoryService.deleteById(idC);
    }
    @DeleteMapping("/supprimerCategorie")
    public void delete(@RequestBody Category category){
        iCategoryService.delete(category);
    }
}
