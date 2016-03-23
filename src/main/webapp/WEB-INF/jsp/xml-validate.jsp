<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="news-box">
    <p>Select XML file to upload. Select parse method,
        which you would like to parse this file.</p>
    <br>
    <form:errors path="*" cssClass="error.block" element="div"/><br>

    <form action="upload.html" method="post" enctype="multipart/form-data">
        Select XML File: <input type="file" name="file"/><br><br>
        <select id="parser" name="parser_type">
            <option value="Sax">Sax parser</option>
            <option value="Stax">Stax parser</option>
            <option value="DOM">DOM parser</option>
            <input type="submit" value="Parse"/>
        </select>
    </form>
    <br>
    Message:${message}<br>
    ${message2}<br>
    <form:errors path="file" cssClass="error"/><br>
    <a href="news.html"><input
            type="button" value="Go back to the news"/></a>

</div>

