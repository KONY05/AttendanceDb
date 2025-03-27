package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.ClassEntity;
import com.example.AttendanceDB.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepo;

    public ClassEntity saveClass(ClassEntity classEntity){
        ClassEntity classEntity1 = classRepo.findByClassName(classEntity.getClassName()).orElse(null);

        if(classEntity1 == null){
            return classRepo.save(classEntity);
        }else {
            return null;
        }
    }

    public List<ClassEntity> getAllClasses(){
        return classRepo.findAll();
    }
}
