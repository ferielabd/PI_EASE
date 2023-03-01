package com.example.pi_ease.Service.Interfaces;

import com.example.pi_ease.DAO.Entities.Project;

import java.util.List;

public interface IProjectService {
    Project add(Project project);
    Project edit(Project project);
    List<Project> selectAll();
    Project selectByID(int idP);
    void deleteById(int idP);
    void delete(Project project);
    List<Project> addAll(List<Project>listProject);
    void deleteAll(List<Project> listProject);


}
