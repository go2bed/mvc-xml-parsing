<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${not empty news}">
    <jsp:useBean id="news" scope="request" type="com.epam.chadov.task3.xml.model.News"/>
</c:if>

<div class="news-box">
    <div class="edit-form">
        <div class="field">
            <label><spring:message code="news.edit.input.title"/> </label>
            <c:out value="${news.title}"/>
        </div>
        <div class="field">
            <label><spring:message code="news.edit.input.date"/> </label>
            <c:out value="${news.newsDate}"/>
        </div>
        <div class="field">
            <label><spring:message code="news.edit.input.brief"/></label>
            <c:out value="${news.brief}"/>
        </div>

        <div class="field">
            <label><spring:message code="news.edit.input.content"/></label>
            <c:out value="${news.content}"/>
        </div>
        <div class="button-area">
            <form action="edit-news.html" method="post">
                <input type="hidden" name="id" value="${news.id}"/>
                <button type="submit" class="link">
                    <spring:message code="news.button.edit"/></button>
            </form>
            <form action="delete-list.html" method="post">
                <input type="hidden" name="id" value="${news.id}"/>
                <button type="submit" class="link">
                    <spring:message code="news.button.delete"/></button>
            </form>
        </div>

    </div>
</div>