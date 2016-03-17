<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript"
        src="/js/upload.js">
    validation(thisform)
</script>

<body>
  <p>Select XML file to upload. Press Add button to add more file
            inputs.</p>
  <br>
     <form action="upload.html" method="post" enctype="multipart/form-data"
    onsubmit="return validation(this)">
    Select XML File:<input type="file" name="file"/><br/>
     <input type="submit" value="Check validation" onclick="return validation(this)"/>
    </form>
     <br>
    <br>
     Message:${message}
</body>