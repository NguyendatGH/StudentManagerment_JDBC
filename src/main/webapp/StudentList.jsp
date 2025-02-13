

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>

        <style>

            table{

                border: 1px solid black;
                border-collapse: collapse;
            }

            th,td{

                border: 1px solid black;
                padding: 0.5em;
            }
            form{
                margin-bottom: 1em;
            }

            input[type="text"]{
                width: 20em;
                height: 1.5em;
            }


        </style>

    </head>

    <body>

        <h1>Student List</h1>

        <table>

            <tr>

                <th>id</th>

                <th>name</th>

                <th>gender</th>

                <th>Birthday</th>
                
                <th>Action</th>

            </tr>
            <c:forEach var="student" items="${students}">

                <tr>
                    <td>${student.id}</td>

                    <td>${student.name}</td>

                    <td>${student.gender}</td>

                    <td>${student.DOB}</td>

                    <td><a href="StudentServlet?action=delete&id=${student.id}">Delete</a></td>

                    <td><a href="StudentServlet?action=edit&id=${student.id}">Update</a></td>
                </tr>

            </c:forEach>

        </table>
        <h2><a href="CreateStudent.jsp">Add new student</a></h2>

    </body>

</html>
