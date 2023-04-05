package com.example.pi_ease.Service.Class;

import com.example.pi_ease.DAO.Entities.Project;
import com.example.pi_ease.Repositories.ProjectRepository;
import com.example.pi_ease.Service.Interfaces.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Project> getProjectsSortedByROIScore() {
        List<Project> projects = projectRepository.findAll();
        List<Project> projectsWithROIScore = new ArrayList<>();

        // Calculate ROI score for each project
        for (Project project : projects) {
            if (project.getCurrentInvestingAmount() > 0 && project.getNetRevenueProject() > 0) {
                double roi = (double) project.getNetRevenueProject() / project.getCostProject();
                project.setRoiScore(roi);
            } else {
                project.setRoiScore(0);
            }
            projectsWithROIScore.add(project);
        }

        // Sort projects by ROI score
        projectsWithROIScore.sort((p1, p2) -> Double.compare(p2.getRoiScore(), p1.getRoiScore()));

        return projectsWithROIScore;    }
}
