<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript"
        src="/js/upload.js">
    validation(thisform)
</script>

<body>
  <p>Select XML file to upload. Select parse method,
  which you would like to parse this file.</p>
  <br>
  <form action="upload.html" method="post" enctype="multipart/form-data"
    onsubmit="return validation(this)">
    Select XML File:<input type="file" name="file"/><br/>
  <select id="parser" name="parser_type">
     <option value="Sax">Sax parser</option>
     <option value="Stax">Stax parser</option>
     <option value="DOM">DOM parser</option>
    <input type="submit" value="Parse" onclick="return validation(this)"/>
   </select>
   </form>
     <br>
    <br>
     Message:${message}
</body>