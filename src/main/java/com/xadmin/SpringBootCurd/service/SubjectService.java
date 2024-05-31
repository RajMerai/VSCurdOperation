package com.xadmin.SpringBootCurd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xadmin.SpringBootCurd.bean.Subject;
import com.xadmin.SpringBootCurd.repository.SubjectRepository;

@Service
public class SubjectService {
	@Autowired
	public SubjectRepository subjectRepo;

	public List<Subject> getAllSubject() {
		List<Subject> subjects = new ArrayList<>();
		subjectRepo.findAll().forEach(subjects::add);
		return subjects;
	}

	public void addSubject(Subject subject) {
		subjectRepo.save(subject);
	}

	public void updateSubject(String id, Subject subject) {
		subjectRepo.save(subject);
	}

	public void deleteSubject(String id) {
		subjectRepo.deleteById(id);
	}
}