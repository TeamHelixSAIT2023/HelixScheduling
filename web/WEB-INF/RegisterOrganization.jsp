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
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-2 col-form-label">Organization Name:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="orgName" placeholder="Organization Name:">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-2 col-form-label">Organization Description:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="orgDesc" placeholder="Organization Description:">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-2 col-form-label">Public Organization:</label>
                        <div class="col-sm-10">
                            <input type="checkbox" class="form-control" name="public" checked="checked">
                        </div>
                    </div>
                    
                    <input class="btn btn-primary" type="submit" value="Create Organization">
                </form>
                <p>${message}</p>
            </div>
        </div>
    </body>
</html>
