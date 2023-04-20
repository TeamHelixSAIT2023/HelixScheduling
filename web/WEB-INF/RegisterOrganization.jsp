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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <title>Register For a Organization</title>
    </head>
    <body>
        <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 12%; height: 100%; position: fixed; top: 0px;">
            <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                <svg class="bi pe-none me-2" width="10" height="12"><img src="/img/logo.png" style="width: 70%; height: auto;"></svg>
            </a>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li>
                    <a href="/home" class="nav-link text-white" aria-current="page">
                        
                        Home
                    </a>
                </li>
                <li>
                    <a href="/task" class="nav-link text-white">
                        
                        Task view
                    </a>
                </li>
                <li>
                    <a href="/schedule" class="nav-link text-white">
                       
                        Schedule
                    </a>
                </li>
                <li>
                    <a href="/availability" class="nav-link text-white">
                        
                        Availability
                    </a>
                </li>
                <li>
                    <div class="dropdown">
                        <button type="button" class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            Organization
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item" href="/joinOrganization">Join Organization</a></li>
                            <li><a class="dropdown-item" href="/registerOrganization">Create Organization</a></li>
                            <li><a class="dropdown-item" href="/organizationList">List Organizations</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
            <hr>
            <div class="dropdown">
                <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">

                    <strong>${user.firstName}</strong>
                </a>
                <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                    <li><a class="dropdown-item" href="/account">Profile</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="/login">Sign out</a></li>
                </ul>
            </div>
        </div>


        
        <div class="d-flex justify-content-center mt-5" style="position:  static">
            <div>
                <h1 class="text-center p-2" style="color:black">Create Organization</h1>
                <form method="POST" action="/registerOrganization">
                    <div>
                        <label>Organization Name:</label>
                        <div>
                            <input class="form-control" style="margin-bottom: 10px;" name="orgName" placeholder="Organization Name">
                        </div>
                    </div>
                    <div >
                        <label>Organization Description:</label>
                        <div >
                            <input class="form-control" style="margin-bottom: 10px;" type="text" name="orgDesc" placeholder="Organization Description">
                        </div>
                    </div>
                    <div>
                        <label>Public Organization:</label>
                        <div>
                            <input style="margin-bottom: 10px;" type="checkbox" name="public" value="public"/>
                        </div>
                    </div>

                    <input class="btn btn-primary" type="submit" value="Create Organization" name="submit">
                </form>

            </div>
        </div>
    </body>
</html>
