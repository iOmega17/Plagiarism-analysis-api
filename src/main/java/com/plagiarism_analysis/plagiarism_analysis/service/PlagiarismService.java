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

    // Method to execute the Python script and fetch results
    public Map<String, Double> checkPlagiarism() {
        Map<String, Double> plagiarismResults = new HashMap<>();
        try {
            // Specify the Python script's path inside the resources folder
            File script = new File("src/main/resources/plagiarism.py");

            // Create a ProcessBuilder instance to execute the Python script
            ProcessBuilder pb = new ProcessBuilder("python", script.getAbsolutePath());

            // Start the process and capture its output
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;

            // Read the Python script's output line by line
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // Parse the output as JSON
            JSONObject json = new JSONObject(output.toString());
            json.keys().forEachRemaining(key -> plagiarismResults.put(key, json.getDouble(key)));

            // Wait for the process to finish execution
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();  // Handle exceptions (e.g., missing script, Python errors)
        }
        return plagiarismResults;  // Return the plagiarism results as a map
    }
}
