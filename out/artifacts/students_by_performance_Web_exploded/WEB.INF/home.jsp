<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body>

<header>
    <h1>Students by Performance</h1>

    <form action = "submit" >
        <input type = "file" name = "file" value="student_file" accept=".txt" />
        <br />
        <input type = "submit" value = "Submit" />
    </form>

</header>

<section>

    <table class="center">
        <tr>
            <th>First Name</th>
            <th>Last name</th>
            <th>Grade</th>
        </tr>
    </table>

</section>

<footer>
    <p>Workshop 2021</p>
    <p>Made by Costinel Negura</p>
</footer>


</body>
</html>
