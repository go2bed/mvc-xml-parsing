<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<div class="news-box">
    <div class="edit-form">
        <form action="save-news.html" method="post">
            <div class="error"><html:errors/></div>
            <div class="field">
                <spring:message code="news.edit.input.title"/>
                 <input type="text" size="20" name="title" value="${news.title}">
            </div>
            <div class="field">
                <spring:message code="news.edit.input.date"/>
                 <input type="date" size="20" name="newsDate" value="${news.newsDate}">
            </div>
            <div class="field">
                <spring:message code="news.edit.input.brief"/>
                <textarea typeof="text" cols="80" rows="7" name="brief">${news.brief}</textarea>
                <input type="hidden" name="brief">
            </div>
            <div class="field">
                <spring:message code="news.edit.input.content"/>
                <textarea  typeof="text" cols="80" rows="13" name="content">${news.content}</textarea>
                <input type="hidden" name="content">
            </div>
            <div class="button-area">
                <input type="hidden" name="id" value="${news.id}">
                <button type="submit" value="save" name="action"><spring:message
                        code="news.button.save"/></button>
                <button type="button" onclick="window.location = '/news.html'"><spring:message
                        code="news.button.cancel"/></button>
            </div>
        </form>
        Message: ${message}
    </div>
</div>
