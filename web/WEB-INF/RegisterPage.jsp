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
            <div class="p-4 border border-secondary rounded">
                <h1 class="text-center p-2 text-white" style="color:black">Create Account</h1>
                <form method="POST" action="register">
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-3 col-form-label text-white">First Name</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="fName" placeholder="First name">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-3 col-form-label text-white">Last Name</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="lName" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-3 col-form-label text-white">Phone Number</label>
                        <div class="col-sm-9">
                            <input type="phone" class="form-control" name="phoneNumber" placeholder="Enter Phone Number">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="staticEmail" class="col-sm-3 col-form-label text-white">Email</label>
                        <div class="col-sm-9">
                            <input type="email" class="form-control" name="email" placeholder="Enter Email">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputPassword" class="col-sm-3 col-form-label text-white">Password</label>
                        <div class="col-sm-9">
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