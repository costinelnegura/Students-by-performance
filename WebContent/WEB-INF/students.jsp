<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <h1>Students by Performance</h1>
</head>
<body>

<header>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <table class="center">
        <c:forEach var="student" items="${student_list}">
            <tr>
                <th>${student}</th>
            </tr>
        </c:forEach>
    </table>
</header>

<section>

    <ul>
        <li><a href="${contextPath}/bubble_sort">Bubble Sort</a></li>
        <li><a href="${contextPath}/merge_sort">Merge Sort</a></li>
        <li><a href="${contextPath}/heap_sort">Heap Sort</a></li>
    </ul>

</section>

<footer>
    <p>Students by Performance 2021</p>
    <p>Made by Costinel Negura</p>
</footer>

</body>
</html>
