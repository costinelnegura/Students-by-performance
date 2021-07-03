<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body>

<header>
    <h1>Students by Performance</h1>
    <h6>Heap sorting time: ${duration1} nanoseconds</h6>
    <h6>Heap sorting time: ${duration2} milliseconds</h6>

</header>

<section>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <table class="center">
        <c:forEach var="student" items="${heap_sorted_list}">
            <tr>
                <th>${student}</th>
            </tr>
        </c:forEach>
    </table>

    <ul>
        <li><a href="downloadSortedList">Download list to a file</a></li>
        <li><a href="${contextPath}/">Try again</a></li>
    </ul>


</section>

<footer>
    <p>Students by Performance 2021</p>
    <p>Made by Costinel Negura</p>
</footer>


</body>
</html>
