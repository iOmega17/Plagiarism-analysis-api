import os
import json
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# Set the directory for input text files
INPUT_DIR = "src/main/resources/Inputs"

def load_files(input_dir):
    """Load all .txt files from the specified input directory."""
    files = [f for f in os.listdir(input_dir) if f.endswith('.txt')]
    contents = [open(os.path.join(input_dir, f), encoding='utf-8').read() for f in files]
    return files, contents

def vectorize(texts):
    """Convert text documents to TF-IDF feature vectors."""
    return TfidfVectorizer().fit_transform(texts).toarray()

def calculate_similarity(vector1, vector2):
    """Calculate cosine similarity between two vectors."""
    return round(cosine_similarity([vector1, vector2])[0][1], 2)

def check_plagiarism(files, vectors):
    """Generate plagiarism results as a JSON object."""
    results = {}
    for i in range(len(files)):
        for j in range(i + 1, len(files)):
            sim_score = calculate_similarity(vectors[i], vectors[j])
            if sim_score > 0:  # Only include non-zero similarity
                pair = sorted((os.path.splitext(files[i])[0], os.path.splitext(files[j])[0]))
                results[f"{pair[0]} similar to {pair[1]}"] = sim_score
    return json.dumps(results, indent=4)

if __name__ == "__main__":
    try:
        # Load files and compute vectors
        student_files, student_notes = load_files(INPUT_DIR)
        vectors = vectorize(student_notes)

        # Check plagiarism and print the result
        plagiarism_report = check_plagiarism(student_files, vectors)
        print(plagiarism_report)
    except Exception as e:
        print(f"Error: {e}")
