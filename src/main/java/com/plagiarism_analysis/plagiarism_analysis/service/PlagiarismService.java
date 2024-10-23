package com.plagiarism_analysis.plagiarism_analysis.service;
import com.oracle.wls.shaded.org.apache.xalan.xslt.Process;
import com.oracle.wls.shaded.org.apache.xpath.operations.String;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PlagiarismService {

    private static final String INPUT_DIR = "D:/Downloads/Projects/plagiarism-analysis/src/main/resources/Inputs";  // Directory to store files

    // Save the uploaded file to the input directory
    public void saveFile(MultipartFile file) throws IOException {
        File dir = new File(INPUT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();  // Create the directory if it doesn't exist
        }

        File outputFile = new File(dir, file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(file.getBytes());
        }
    }

    // Run the plagiarism.py script and return the JSON response
    public String runPlagiarismCheck() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "plagiarism.py");
        processBuilder.directory(new File(System.getProperty("user.dir")));  // Set working directory

        Process process = processBuilder.start();
        process.waitFor();  // Wait for the process to complete

        // Read the JSON output from the script
        List<String> lines = Files.readAllLines(Paths.get("output.json"));
        return String.join("\n", lines);
    }
}
