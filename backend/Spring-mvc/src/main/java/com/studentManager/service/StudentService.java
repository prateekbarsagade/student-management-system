package com.studentManager.service;

import java.util.List;

import com.studentManager.dto.StudentDTO;
import com.studentManager.model.Student;

public interface StudentService {
	
	Student create(StudentDTO dto);
	
	List<Student> getAll();
	
	Student getById(Long id);
	
	Student update(Long id , StudentDTO dto);
	
	void delete(Long id);

}
