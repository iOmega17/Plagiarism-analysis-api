package com.plagiarism_analysis.plagiarism_analysis.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;

@Service
public class PlagiarismService {

    private static final String UPLOAD_DIR = "src/main/resources/input_files/";
    private static final String PYTHON_SCRIPT = "src/python/plagiarism.py";

    public String runPythonScript(MultipartFile file) {
        try {
            // Save uploaded file to input directory
            Path filepath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.write(filepath, file.getBytes());

            // Run Python script with ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder("python", PYTHON_SCRIPT);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Capture output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int i  = 10;
            Integer i = new Integer(10);
            process.waitFor();
            return output.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
