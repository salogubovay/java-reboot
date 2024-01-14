<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Add User</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
    </head>
    <body>  
        <form method="POST" action="">
		    <span>Name</span><input name="name">
		    <span>Age</span><input name="age">
		    <input type="submit" name="submit" value="Save">
		</form>
    </body>
</html>