package com.costinel.StudentByGrade.service;

import com.costinel.StudentByGrade.DAO.dao.StudentDao;
import com.costinel.StudentByGrade.DAO.daoImplementation.HeapDaoImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    private List<String> sourceStudentList;
    private String path;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private HeapDaoImplementation heapDaoImplementation;

    public List<String> getStudents(MultipartFile file, String path) throws IOException {
        this.path = path;
        sourceStudentList = studentDao.getStudents(studentDao.uploadFile(file, path));

        return studentDao.getStudents(studentDao.uploadFile(file, path));
    }

    public List<String> bubbleSortStudents(){
        studentDao.bubbleSortStudents(sourceStudentList);
        return sourceStudentList;
    }

    public List<String> mergeSortStudents(){
        sourceStudentList = studentDao.mergeSortStudents(sourceStudentList);
        return sourceStudentList;
    }

    public List<String> heapSortStudents(){
        heapDaoImplementation.setSize(20);
        heapDaoImplementation.insert(sourceStudentList);
        sourceStudentList = heapDaoImplementation.returnList();
        return sourceStudentList;
    }

    public File downloadFile() throws IOException {
        return studentDao.downloadFile(sourceStudentList, path);
    }
}
