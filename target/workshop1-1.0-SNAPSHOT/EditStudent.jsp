
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Student</title>
        <link rel="stylesheet" href="main.css">
        <style>
            body{
                input[type="text"]{
                    width: 15em;
                    margin-left: .5em;
                    margin-bottom: .5em;
                }
                br{
                    clear: both;
                }
                #submit{
                    margin-left: 0.5em;
                }
            </style>
        </head>

        <body>

            <h1>Edit Student</h1>

            <form action="StudentServlet" method="post">
                <input type="hidden" name="action" value="update" >
                <input type="hidden" name="idP" value="${student.id}" >
                <label>name(*):</label>
                <input type="text" name="name" value="${student.name} "
                       > <br>
                <label>Gender(*):</label>
                <input type="radio" name="gender" value="Male" 
                       ${student.gender == "Male" ? "checked" : ""}> Male
                <input type="radio" name="gender" value="Female" 
                       ${student.gender == "Female" ? "checked" : ""}> Female
                <br/>
                <label>birthDay(*):</label>
                <input type="text" name="DOB" value="${student.DOB}"
                       required> <br>
                <label>&nbsp;</label>
                <input type="submit" value="Update" id="submit"> <br>
            </form>
            <p style="color:red;
               font-weight: bold">${errStr}</p>
            </body>

        </html>

