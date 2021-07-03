package com.costinel.StudentByGrade.DAO.dao;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface StudentDao {

    List<String> getStudents(Path path) throws IOException;
    void bubbleSortStudents(List<String> sourceList);
    List<String> mergeSortStudents(List<String> sourceList);
    Path uploadFile(MultipartFile file, String path) throws IOException;
    File downloadFile(List<String> list, String path) throws IOException;
}
