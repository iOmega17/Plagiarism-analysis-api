import os
import json
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def read_files_from_directory(directory):
    files_content = {}
    for filename in os.listdir(directory):
        if filename.endswith('.txt'):
            with open(os.path.join(directory, filename), 'r', encoding='utf-8') as f:
                files_content[filename] = f.read()
    return files_content

def calculate_similarity(files_content):
    # Extract filenames and their content
    filenames = list(files_content.keys())
    content_list = list(files_content.values())

    # Vectorize the content using TF-IDF
    vectorizer = TfidfVectorizer().fit_transform(content_list)
    similarity_matrix = cosine_similarity(vectorizer)

    # Create a similarity result structure
    similarity_results = []
    for i in range(len(filenames)):
        for j in range(i + 1, len(filenames)):
            result = {
                'file1': filenames[i],
                'file2': filenames[j],
                'similarity': round(similarity_matrix[i][j] * 100, 2)  # Percentage format
            }
            similarity_results.append(result)

    return similarity_results

def save_results_to_json(results, output_file='output.json'):
    with open(output_file, 'w', encoding='utf-8') as f:
        json.dump(results, f, indent=4)

if __name__ == "__main__":
    input_dir = 'D:/Downloads/Projects/plagiarism-analysis/src/main/resources/Inputs'  # Directory containing the uploaded files
    files_content = read_files_from_directory(input_dir)

    if files_content:
        similarity_results = calculate_similarity(files_content)
        save_results_to_json(similarity_results)
        print("Similarity check complete. Results saved to output.json.")
    else:
        print("No text files found in the input directory.")
