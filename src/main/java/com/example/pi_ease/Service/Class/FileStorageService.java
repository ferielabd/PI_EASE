package com.example.pi_ease.Service.Class;

import com.example.pi_ease.DAO.Entities.FileDb;
import com.example.pi_ease.Repositories.FileDBRepository;
import com.example.pi_ease.Service.Interfaces.IFileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor

@Service
public class FileStorageService implements IFileStorageService {
    private FileDBRepository fileDBRepository;

    @Override
    public void store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDb FileDB = new FileDb(fileName, file.getContentType(), file.getBytes());

        fileDBRepository.save(FileDB);
    }

    @Override
    public FileDb getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<FileDb> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
