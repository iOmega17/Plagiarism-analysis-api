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
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    <c:if test="${not empty results}">
        <ul>
            <c:forEach var="entry" items="${results}">
                <li>${entry.key}: ${entry.value}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>