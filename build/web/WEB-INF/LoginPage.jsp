<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <title>Login</title>
    </head>
    <body>
            <h1>Login</h1>
            <form method="POST" action="login">
                <label>Email Address:</label>
                <input type="email" name="email" placeholder="Enter Email"><br>
                <label>Password:</label>
                <input type="password" name="password" placeholder="Password"><br>
                <input type="submit" value="Log in">
                <inp
            </form>
                <p>${error}</p>
        
    </body>
</html>
