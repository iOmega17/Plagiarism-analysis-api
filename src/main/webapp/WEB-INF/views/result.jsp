
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plagiarism Check Results</title>
</head>
<body>
    <h1>Plagiarism Check Results</h1>
    <!-- Display the jsonResponse of the plagiarism check -->
    <c:if test="${not empty jsonResponse}">
        <p>${jsonResponse}</p>
    </c:if>
    <!-- display json response of the plagiarism check as a table-->

</body>
</html>