# **Plagiarism Detection Web Application 🚀**

This project is a web application built using **Spring Boot** and **Python libraries** to detect plagiarism between uploaded files. The solution integrates Java-based backend technologies with Python to perform plagiarism detection using text similarity techniques.

---

## **Features**  
- 📁 Upload multiple text files for comparison.  
- 📝 Detect similarity using Python’s **Scikit-learn** and JSON-based output handling.  
- 🔍 View similarity results directly via the web interface.  
- ⚙️ Backend built using **Spring Boot**, **JPA**, **Hibernate**, and **JSP**.  
- 🐍 Python script execution from the Java service layer.  

---

## **Technologies Used**  
- **Java Frameworks:**  
  - Spring Boot  
  - JPA and Hibernate (for ORM and data management)  
  - Maven (for dependency management)  
  - JSP (for frontend views)  

- **Python Libraries:**  
  - Scikit-learn (for text similarity detection)  
  - JSON handling (for results output)  

---

## **Project Structure**  

```bash
|-- src
|   |-- main
|   |   |-- java
|   |   |   |-- com.example.plagiarismdetector
|   |   |       |-- controller
|   |   |       |-- service
|   |   |-- resources
|   |       |-- templates (JSP files)
|-- python
|   |-- plagiarism.py (Python script)
|   |-- output.json (Generated result)
```

---

## **Setup Instructions**  

1. **Clone the Repository**  
   ```bash
   git clone https://github.com/your-username/plagiarism-detector.git
   cd plagiarism-detector
   ```

2. **Install Dependencies**  
   - **Java:** Ensure Java 17+ and Maven are installed.  
     ```bash
     mvn clean install
     ```  
   - **Python:** Install required libraries.  
     ```bash
     pip install scikit-learn
     ```

3. **Run the Application**  
   - Start the Spring Boot application:  
     ```bash
     mvn spring-boot:run
     ```
   - Access the application at: [http://localhost:8080](http://localhost:8080)  

4. **Upload Files and Check Similarity**  
   - Upload files via `/upload`.  
   - View similarity results at `/check`.

---

## **Usage Flow**  

1. **Upload Panel (`/upload`)**  
   - Upload the text files to be compared.  
   - The files are added to the **input directory** for analysis.

2. **Python Script Execution**  
   - The service layer triggers the Python script to compare the uploaded files.
   - Results are stored in `output.json`.

3. **View Results (`/check`)**  
   - Visit `/check` to see the similarity scores in JSON format.

---

## **Sample API Endpoints**  

- **Upload Files**  
  ```
  POST /upload
  ```

- **Check Similarity**  
  ```
  GET /check
  ```

---

## **Contributing**  
Contributions are welcome! Please fork the repository and submit a pull request.

---

## **License**  
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## **Contact**  
Feel free to reach out if you have any questions or suggestions.  

---
