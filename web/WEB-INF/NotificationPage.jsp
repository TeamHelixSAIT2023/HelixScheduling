<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

        <title>Notification</title>

    </head>
    <body>
        <div class="row" style="height: 100%;">
            <div class="col-4">

                <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 220px; height: 100%;">
                    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <svg class="bi pe-none me-2" width="10" height="12"><img src="/css/logo.png" style="width: 150px; height: auto;"></svg>
                    </a>
                    <hr>
                    <ul class="nav nav-pills flex-column mb-auto">
                        <li>
                            <a href="/home" class="nav-link active" aria-current="page">
                                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="/task" class="nav-link text-white">
                                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
                                Task view
                            </a>
                        </li>
                        <li>
                            <a href="/schedule" class="nav-link text-white">
                                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
                                Schedule
                            </a>
                        </li>
                        <li>
                            <a href="/gannt" class="nav-link text-white">
                                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
                                Gantt Chart
                            </a>
                        </li>
                        <li>
                            <a href="/availability" class="nav-link text-white">
                                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
                                Availability
                            </a>
                        </li>
                    </ul>
                    <hr>
                    <div class="dropdown">
                        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/mdo.png" alt="" class="rounded-circle me-2" width="32" height="32">
                            <strong>User</strong>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item" href="#">New project...</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Sign out</a></li>
                        </ul>
                    </div>
                </div>
            </div>


            <!--Main content area-->
            <div class="col-6 mt-5">
                   <jsp:include page="notification.html" />
            </div>
            <!--End: Main content area--> 

        </div>

    </body>
</html> 
