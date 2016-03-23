<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<div class="logo">
    <a><spring:message code="main.page.logo"/></a>
</div>
<br/>
<br/>

<div align="right" class="lang-panel">
    <span>
    <a href="?mylocale=en"><spring:message code="main.page.en.language"/></a>
    <a href="?mylocale=ru"><spring:message code="main.page.ru.language"/></a>
    </span>
</div>