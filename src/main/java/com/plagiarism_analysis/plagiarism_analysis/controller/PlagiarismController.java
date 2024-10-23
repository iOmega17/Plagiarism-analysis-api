package com.plagiarism_analysis.plagiarism_analysis.controller;
import com.oracle.wls.shaded.org.apache.xpath.operations.String;
import com.plagiarism_analysis.plagiarism_analysis.service.PlagiarismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PlagiarismController {

    @Autowired
    private PlagiarismService plagiarismService;

    // GET request for the upload page
    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }

    // POST request to upload a file and add it to the input directory
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            plagiarismService.saveFile(file);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    // GET request to run the plagiarism check and display the results
    @GetMapping("/check")
    public String checkPlagiarism(Model model) {
        try {
            String jsonResponse = plagiarismService.runPlagiarismCheck();
            model.addAttribute("result", jsonResponse);
            return "check"; // JSP view to display the result
        } catch (Exception e) {
            model.addAttribute("error", "Error running plagiarism check: " + e.getMessage());
            return "error"; // JSP view to show errors
        }
    }
}
