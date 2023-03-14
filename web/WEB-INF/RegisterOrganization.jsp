<%-- 
    Document   : RegisterOrganizationpage
    Created on : 11-Mar-2023, 2:17:27 PM
    Author     : Sukhpal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Register For a Organization</title>
    </head>
    <body>
                <div class="d-flex justify-content-center mt-5">
            <div>
                <h1 class="text-center p-2" style="color:black">Create Organization</h1>
                <form method="POST" action="register">
                    <div>
                        <label>Organization Name:</label>
                        <div>
                            <input name="orgName" placeholder="Organization Name:">
                        </div>
                    </div>
                    <div >
                        <label>Organization Description:</label>
                        <div >
                            <input type="text" name="orgDesc" placeholder="Organization Description:">
                        </div>
                    </div>
                    <div>
                        <label>Public Organization:</label>
                        <div>
                            <input type="checkbox" name="pub" value="pub"/>
                        </div>
                    </div>
                    
                    <input class="btn btn-primary" type="submit" value="Create Organization">
                </form>
                
            </div>
        </div>
    </body>
</html>
