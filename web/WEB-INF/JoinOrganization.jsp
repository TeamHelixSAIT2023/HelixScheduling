<%-- 
    Document   : JoinAOrganization
    Created on : 27-Mar-2023, 9:12:20 AM
    Author     : Sukhpal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Join a Organization</title>
    </head>
    <body>
        <form id="joinForm" action="joinOrganization" method="POST">
  <label for="orgName">Enter organization name:</label>
  <input type="text" id="orgName" name="orgName">
  <button type="submit">Join organization</button>
</form>
        

    </body>
</html>
