package com.onlinevoting.candidate.service;

import com.onlinevoting.candidate.entity.Candidate;
import com.onlinevoting.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public List<Candidate>getCandidatesByStatus(String status) {
        return candidateRepository.findByStatus(status);
    }

    public Candidate updateCandidateStatus(Long id, String status) {
        if (!"Approved".equalsIgnoreCase(status) && !"Rejected".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Invalid status. Allowed values: Approved, Rejected");
        }
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));
        candidate.setStatus(status);
        return candidateRepository.save(candidate);
    }

}
