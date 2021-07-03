package com.costinel.StudentByGrade.DAO.daoImplementation;



import com.costinel.StudentByGrade.DAO.dao.StudentDao;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentDaoImplementation implements StudentDao {
    @Override
    public List<String> getStudents(Path path) throws IOException {
        return Files.readString(Path.of(String.valueOf(path))).lines().collect(Collectors.toList());
    }

    @Override
    public void bubbleSortStudents(List<String> sourceList) {
        for(int i=0; i< sourceList.size() - 1; i++){
            for(int j = i+1; j>0; j--){

                if((Double.parseDouble(sourceList.get(j).substring(sourceList.get(j).indexOf(",") + 1))) <
                        (Double.parseDouble(sourceList.get(j-1).substring(sourceList.get(j-1).indexOf(",") + 1)))){
                    var temp = sourceList.get(j);
                    sourceList.set(j, sourceList.get(j-1));
                    sourceList.set(j-1, temp);
                }
            }
        }
    }

    @Override
    public List<String> mergeSortStudents(List<String> sourceList) {
        return sorted(sourceList);
    }

    public static List<String> sorted(List<String> list) {
        if (list.size() < 2) {
            return list;
        }
        int mid = list.size()/2;
        return merged(
                sorted(list.subList(0, mid)),
                sorted(list.subList(mid, list.size())));
    }

    private static List<String> merged(List<String> left, List<String> right) {
        int leftIndex = 0;
        int rightIndex = 0;
        List<String> merged = new ArrayList<String>();

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ((Double.parseDouble(left.get(leftIndex).substring(left.get(leftIndex).indexOf(",") + 1)))
                    < (Double.parseDouble(right.get(rightIndex).substring(right.get(rightIndex).indexOf(",") + 1)))) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }
        merged.addAll(left.subList(leftIndex, left.size()));
        merged.addAll(right.subList(rightIndex, right.size()));
        return merged;
    }

    @Override
    public Path uploadFile(MultipartFile file, String path) throws IOException {

        if(!new File(path).exists()){
            new File(path).mkdir();
        }

        String orgName = file.getOriginalFilename();
        String filePath = path + orgName;
        File dest = new File(filePath);
        file.transferTo(dest);

        return dest.getAbsoluteFile().toPath();
    }

    @Override
    public File downloadFile(List<String> list, String path) throws IOException {
        if(!new File(path).exists()){
            new File(path).mkdir();
        }
        File file = new File(path + "sorted.txt");

        Files.write(Path.of(String.valueOf(file)), list,Charset.defaultCharset());
      return file;
    }
}
