package com.example.pi_ease.RestController;

import com.example.pi_ease.DAO.Entities.Project;
import com.example.pi_ease.Service.Interfaces.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("Project")
public class projectRestController {
    @Autowired
    private IProjectService iProjectService;
    @GetMapping("/afficherProjet")
    public List<Project> afficher(){
        return iProjectService.selectAll();
    }

    @PostMapping("/addProject")
    public Project add( @RequestBody Project project){
        return iProjectService.add(project);    }
    /*@GetMapping("/addProjectWithId")
    public Project addProjectWithId(@PathVariable int idP){
        return iProjectService.selectByID(idP);
    }*/
    @PostMapping("/addProjectAllProject")
    public List<Project> addAll(@RequestBody List<Project> list){
        return iProjectService.addAll(list);
    }
    @DeleteMapping("/deleteProject")
    public void delete(@RequestBody Project project) {
        iProjectService.delete(project);
    }
    @PutMapping("/modifierProject")
    public Project edit(@RequestBody Project project){
        return iProjectService.edit(project);
    }
    @DeleteMapping("/deleteProjectById")
    public void deleteById(@RequestParam int idP){
        iProjectService.deleteById(idP);
    }

    @GetMapping("/sortedByROIScore")
    public List<Project> getProjectsSortedByROIScore(){        return iProjectService.getProjectsSortedByROIScore();
    }
}
