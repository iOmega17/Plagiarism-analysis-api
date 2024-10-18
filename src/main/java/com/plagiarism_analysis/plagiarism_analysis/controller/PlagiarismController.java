package com.plagiarism_analysis.plagiarism_analysis.controller;

import com.plagiarism_analysis.plagiarism_analysis.service.PlagiarismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@RestController
@RequestMapping("/api/plagiarism")
public class PlagiarismController {

    @Autowired
    private PlagiarismService plagiarismService;

    // Expose a GET endpoint to check for plagiarism
    @GetMapping("/check")
    public Map<String, Double> checkPlagiarism() {
        return plagiarismService.checkPlagiarism();  // Return the plagiarism results
    }
}
