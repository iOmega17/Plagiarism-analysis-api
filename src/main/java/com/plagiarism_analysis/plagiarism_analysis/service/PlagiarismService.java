package com.plagiarism_analysis.plagiarism_analysis.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlagiarismService {

    public Map<String, Double> checkPlagiarism() {
        Map<String, Double> plagiarismResults = new HashMap<>();
        try {
            // Specify the Python script's path
            File script = new File("src/main/resources/plagiarism.py");

            // Run the Python script using ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder("python", script.getAbsolutePath());
            Process process = pb.start();

            // Capture the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // Wait for the process to complete
            process.waitFor();

            // Print raw output for debugging
            System.out.println("Python Script Output: " + output);

            // Parse output only if it starts with '{'
            if (output.toString().trim().startsWith("{")) {
                JSONObject json = new JSONObject(output.toString());
                json.keys().forEachRemaining(key ->
                        plagiarismResults.put(key, json.getDouble(key))
                );
            } else {
                System.err.println("Invalid JSON Output: " + output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plagiarismResults;
    }
}
