package com.costinel.StudentByGrade.controller;


import com.costinel.StudentByGrade.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

   @RequestMapping("/submit")
    public String getStudents(MultipartHttpServletRequest request, Model model) throws IOException {
        MultipartFile multipartFile = request.getFile("upload_file");
        var uploadsDir = "/uploads/";
        var pathToUploadsDir = request.getServletContext().getRealPath(uploadsDir);
        model.addAttribute("student_list", studentService.getStudents(multipartFile, pathToUploadsDir));
        return "students";
    }


    @RequestMapping("/bubble_sort")
    public String bubbleSort(Model model){
        Instant start = Instant.now();
        model.addAttribute("bubble_sorted_list", studentService.bubbleSortStudents());
        Instant end = Instant.now();
        model.addAttribute("duration1", Duration.between(start, end).toNanos());
        model.addAttribute("duration2", Duration.between(start, end).toMillis());
        return "sort/bubble";
    }

    @RequestMapping("/merge_sort")
    public String mergeSort(Model model){
        Instant start = Instant.now();
        model.addAttribute("merge_sorted_list", studentService.mergeSortStudents());
        Instant end = Instant.now();
        model.addAttribute("duration1", Duration.between(start, end).toNanos());
        model.addAttribute("duration2", Duration.between(start, end).toMillis());
        return "sort/merge";
    }

    @RequestMapping("/heap_sort")
    public String heapSort(Model model){
        Instant start = Instant.now();
        model.addAttribute("heap_sorted_list", studentService.heapSortStudents());
        Instant end = Instant.now();
        model.addAttribute("duration1", Duration.between(start, end).toNanos());
        model.addAttribute("duration2", Duration.between(start, end).toMillis());
        return "sort/heap";
    }

    @RequestMapping("/downloadSortedList")
    protected void downloadStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String filename = studentService.downloadFile().getName();
        String filepath = studentService.downloadFile().getParent();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");

        FileInputStream fileInputStream = new FileInputStream(filepath + "\\" + filename);

        int i;
        while ((i=fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }
}
