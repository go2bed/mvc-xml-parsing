<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>xml-validate-success</title>
</head>
<body>
 <p>Selected XML file was success validated.At now tou can use
    parser for your File.</p>
    <p>Message:${message}</p>
       <br>
  <contentType="hidden">${message2}</contentType>
    <br>


<select id="parser" name="parser">
   <option value="Sax">Sax parser</option>
   <option value="Stax">Stax parser</option>
   <option value="DOM">DOM parser</option>
</select>
   <form action="parse.html" method="post" enctype="multipart/form-data">
   <input type="hidden" name="file" value=${message2}/>
   <input type="submit" value="Parse"/>
   </form>
</body>
</html>
