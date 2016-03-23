<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <meta charset="utf-8" content="application/http">
    <title>News Management</title>
</head>
<body>
<div class="wrapper">
    <div class="header">
        <tiles:insertAttribute name="header"/>
    </div>
    <div class="sidebar">
        <tiles:insertAttribute name="sidebar"/>
    </div>
    <div class="content">
        <div class="path">
            <tiles:insertAttribute name="content"/>
        </div>
    </div>
    <div class="footer">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
</body>
</html>
