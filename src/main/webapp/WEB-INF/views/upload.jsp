<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload File</title>
</head>
<body>
    <h1>Upload File</h1>
    <form method="post" action="/upload" enctype="multipart/form-data">
        <input type="file" name="file" />
        <button type="submit">Upload</button>
    </form>
    <c:if test="${not empty message}">
        <p>${message}
            <a href="/check">Check uploaded files</a>
        </p>

    </c:if>

</body>
</html>