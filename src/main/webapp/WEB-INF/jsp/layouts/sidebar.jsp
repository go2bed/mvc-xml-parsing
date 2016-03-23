<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="menu">
    <h4 class="sidebar-header-layer" align="center"><spring:message code="side.bar.logo"/></h4>
    <ul class="actions">
        <li>
            <form action="news.html" method="get">
                <button type="submit" class="link">
                    <spring:message code="side.bar.action.list"/>
                </button>
            </form>
        </li>
        <li>
            <form action="edit-news.html" method="post">
                <button type="submit" class="link">
                    <spring:message code="side.bar.action.add"/></button>
            </form>
        <li><a href="xml-work.html"><input
                type="button" value="XML work page"/></a></li>
    </ul>
</div>
