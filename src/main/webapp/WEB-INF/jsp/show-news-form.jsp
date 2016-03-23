<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
<script>
    function calculate() {
        var arr = $.map($('input:checkbox:checked'), function (e, i) {
            return +e.value;
        });
        console.log(arr);
        $('input[name="checkbox1"]').val(arr);
     }
    $('div').delegate('input:checkbox','click', calculate);

</script>

<div class="news-box">
    <%--@elvariable id="newsList" type="java.util.List"--%>
    <c:forEach var="news" items="${newsList}">
        <div class="clear-area">
            <div class="news-text">
                <h3><spring:message code="news.edit.input.title"/>
                        ${news.title}</h3><br>
                    ${news.brief}
            </div>
            <div class="news-date">${news.newsDate}
            </div>
        </div>
        <div class="actions-in-news-block">
            <form action="view-news.html" method="get">
                <input type="hidden" name="id" value="${news.id}"/>
                <button type="submit" class="link">
                    <spring:message code="news.link.view"/></button>
            </form>
            <form action="edit-news.html" method="post">
                <input type="hidden" name="id" value="${news.id}"/>
                <button type="submit" class="link">
                    <spring:message code="news.link.edit"/></button>
            </form>
            <input type="checkbox" name="checkbox" value="${news.id}" title="checkbox" id="checkbox"/>
        </div>
    </c:forEach>
    <div class="button-area">
        <form action="delete-list.html" method="post" onsubmit="calculate() ">
            <input type="hidden" value="" name="checkbox1" id="checkbox1"/>
            <button type="submit" value="/delete-list">
                <spring:message code="news.button.delete"/></button>
        </form>
    </div>
    <br>
    <br>
</div>
