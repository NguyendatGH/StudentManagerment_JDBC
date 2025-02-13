<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create new Student</title>
        <meta http-equiv="Content-Type" content="text/html;
              charset=UTF-8">
        <link rel="stylesheet" href="main.css">
        <style>
            body{
                font-family: Arial, Helvetica, sans-serif;
                font-size: 11pt;
                margin-left: 2em;
                margin-right: 2em;
            }
            h1{
                color: teal;
            }
            label{
                float: left;
                width: 7em;
                margin-bottom: .5em;
            }
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
        <h1>Create New Student</h1>
        <form action="StudentServlet" method="post">
            <input type="hidden" name="action" value="addnew">
            <label>id(*):</label>
            <input type="text" name="id" > <br/>
            <label>name(*):</label>
            <input type="text" name="name" > <br/>
            <label>Gender(*):</label>
            <input type="radio" name="gender" value="Male"> Male
            <input type="radio" name="gender" value="Female"> Female
            <br/>
            <label>birthday(yyyy-MM-dd)(*):</label>
            <input type="text" name="DOB" > <br/>
            <label>&nbsp;</label>
            <input type="submit" value="Add Student" id="submit"> <br/>
        </form>
        <p style="color:red; font-weight: bold">${errString}</p>
    </body>
</html>
