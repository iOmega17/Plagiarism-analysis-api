package com.plagiarism_analysis.plagiarism_analysis.controller;
import com.plagiarism_analysis.plagiarism_analysis.service.PlagiarismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.lang.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

@Controller
public class PlagiarismController {

    @Autowired
    private PlagiarismService plagiarismService;

    // GET request for the upload page
    @GetMapping("/upload")
    public java.lang.String showUploadPage() {
        return "upload";
    }

    // POST request to upload a file and add it to the input directory
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            plagiarismService.saveFile(file);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }

    // GET request to run the plagiarism check and display the results
    @GetMapping("/check")
    public java.lang.String checkPlagiarism(Model model) throws java.lang.Exception {
        try {
            List<String> jsonResponse = plagiarismService.runPlagiarismCheck();
            // Add the JSON response to the result.jsp view
            System.out.println(jsonResponse);
            model.addAttribute("jsonResponse", jsonResponse);
            return "result"; // JSP view to show the results
        } catch (Exception e) {
            model.addAttribute("error", "Error running plagiarism check: " + e.getMessage());
            return "error"; // JSP view to show errors
        }
    }
}
