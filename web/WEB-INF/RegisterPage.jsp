<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Register</title>
        <style>
            body{
                background-color: #282c34;
                padding: 15px;
            }
            label{
                color: black;
            }
        </style>
    </head>
    <body>
        <div class="d-flex justify-content-center mt-5">
            <div>
                <h1 class="text-center p-2" style="color:black">Create Account</h1>
                <form method="POST" action="login">
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-2 col-form-label">First Name:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="fName" placeholder="First name">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-2 col-form-label">Last Name:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="lName" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-2 col-form-label">Phone Number</label>
                        <div class="col-sm-10">
                            <input type="phone" class="form-control" name="phoneNumber" placeholder="Enter Phone Number">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="email" placeholder="Enter Email">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" placeholder="Password"> 
                        </div>            
                    </div>
                    <input class="btn btn-primary" type="submit" value="Create account">
                </form>
                <p>${message}</p>
            </div>
        </div>
        
    </body>
</html>