package com.studentManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentManager.dto.StudentDTO;
import com.studentManager.exception.ResourceNotFoundException;
import com.studentManager.model.Student;
import com.studentManager.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService{
	
	//constructor injection
	private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }
    

	@Override
	public Student create(StudentDTO dto) {
		Student student = new Student(null, dto.getName(), dto.getEmail(), dto.getAge());
        return repository.save(student);
	}

	@Override
	public List<Student> getAll() {
		return repository.findAll();
	}

	@Override
	public Student getById(Long id) {
		
		return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
	}

	@Override
	public Student update(Long id, StudentDTO dto) {
		Student existing = getById(id);
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setAge(dto.getAge());
        return repository.save(existing);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}

}
