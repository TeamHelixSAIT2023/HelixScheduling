<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Login</title>
    </head>
    <body class="bg-dark p-3">
        <div class="d-flex justify-content-center mt-5">
            <div class="p-5 border border-light border-3 rounded">
                <h1 class="text-center p-2 text-white" style="color:black">Login</h1>
                <form method="POST">
                    <div class="row mb-3">
                        <label for="emaiil" class="col-md col-form-label text-white">Email</label>
                        <div>
                            <input type="email" class="form-control" name="email" placeholder="Enter Email">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="password" class="col-md col-form-label text-white">Password</label>
                        <div>
                            <input type="password" class="form-control" name="password" placeholder="Password"> 
                        </div>            
                    </div>
                    <input class="btn btn-primary mb-2" type="submit" value="Log in">
                </form>
                <a href="/register">
                    <button class="btn btn-primary">Register</button>
                </a>
                <p style="color:white">${message}</p>
            </div>
        </div>
    </body>
</html>
