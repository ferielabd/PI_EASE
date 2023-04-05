package com.example.pi_ease.Service.Interfaces;

import com.example.pi_ease.DAO.Entities.FileDb;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface IFileStorageService {
    public void store(MultipartFile file) throws IOException;


    public FileDb getFile(String id) ;



    public Stream<FileDb> getAllFiles();

}
