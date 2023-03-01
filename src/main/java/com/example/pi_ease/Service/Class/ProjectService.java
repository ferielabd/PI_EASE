package com.example.pi_ease.Service.Class;

import com.example.pi_ease.DAO.Entities.Project;
import com.example.pi_ease.Repositories.ProjectRepository;
import com.example.pi_ease.Service.Interfaces.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProjectService implements IProjectService {
    private ProjectRepository projectRepository;
    @Override
    public Project add(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project edit(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> selectAll() {
        return projectRepository.findAll();

    }

    @Override
    public Project selectByID(int idP) {
        return projectRepository.findById(idP).get();
    }

    @Override
    public void deleteById(int idP) {
        projectRepository.deleteById(idP);
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public List<Project> addAll(List<Project> listProject) {
        return projectRepository.saveAll(listProject);
    }

    @Override
    public void deleteAll(List<Project> listProject) {
        projectRepository.deleteAll(listProject);

    }
}
