package com.plagiarism_analysis.plagiarism_analysis.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plagiarism")
public class PlagiarismController {

    private static final String INPUT_DIR = "src/main/resources/Inputs";  // Directory for input files
    private static final String PYTHON_SCRIPT_PATH = "python/plagiarism.py";  // Adjust the path

    // Endpoint to upload a new file
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Ensure input directory exists
            Files.createDirectories(Paths.get(INPUT_DIR));

            // Save the uploaded file in the input directory
            String filePath = INPUT_DIR + file.getOriginalFilename();
            Files.write(Paths.get(filePath), file.getBytes());

            return "File uploaded successfully: " + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
    }

    // Endpoint to trigger the plagiarism check and get the JSON result
    @GetMapping("/check")
    public String checkPlagiarism() {
        ProcessBuilder processBuilder = new ProcessBuilder("python3", PYTHON_SCRIPT_PATH);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Collect the JSON output from the Python script
            String result = reader.lines().collect(Collectors.joining("\n"));

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return result;  // Return the JSON result
            } else {
                return "Error: Python script exited with code " + exitCode;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Exception occurred: " + e.getMessage();
        }
    }
}
