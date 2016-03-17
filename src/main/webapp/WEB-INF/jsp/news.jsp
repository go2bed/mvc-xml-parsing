<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>News</title>
</head>
<body>
We have all news for today at here!!
<c:forEach var="news" items="${newsList}">
<table  border = 1>
  <tr><td>${news.title}<td><td>${news.newsDate}</td>
  <td>${news.brief}</td><td>${news.content}</td>
  </tr>
  </table>

</c:forEach>
<br>
  <a href="xml-validate.html"><input
          type="button" value="XML work page" /></a>
</body>
</html>
