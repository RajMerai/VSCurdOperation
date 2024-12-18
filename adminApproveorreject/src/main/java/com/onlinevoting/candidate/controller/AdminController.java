package com.onlinevoting.candidate.controller;

import com.onlinevoting.candidate.dto.CandidateDTO;
import com.onlinevoting.candidate.entity.Candidate;
import com.onlinevoting.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final CandidateService candidateService;

    @Autowired
    public AdminController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Display all candidates
    @GetMapping("/candidates")
    public List<CandidateDTO> displayAllCandidates() {
        return candidateService.getAllCandidates().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Display candidates by status (Approved/Rejected)
    @GetMapping("/candidates/status/{status}")
    public List<CandidateDTO> getCandidatesByStatus(@PathVariable String status) {
        return candidateService.getCandidatesByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Approve or reject a candidate
    @PutMapping("/candidates/{id}")
    public CandidateDTO approveOrRejectCandidate(@PathVariable Long id, @RequestParam String status) {
        Candidate updatedCandidate = candidateService.updateCandidateStatus(id, status);
        return convertToDTO(updatedCandidate);
    }

    private CandidateDTO convertToDTO(Candidate candidate) {
        return new CandidateDTO(candidate.getId(), candidate.getName(), candidate.getStatus());
    }
}

