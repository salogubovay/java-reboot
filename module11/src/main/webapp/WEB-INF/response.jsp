<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
    <head>
       <title>Add user</title>
       <link rel="stylesheet" href="css/style.css"/>
    </head>
    
    <body>
        <h1>${headerText}</h1>
        <p>${bodyText}</p>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="user" value="${user}"/>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.age}</td>
                    </tr>
            </tbody>
        </table>
    </body>
</html>
