<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <title>account</title>
    </head>
    <body>
        <div>
            <div class="row" style="height: 100%;">
                <div class="col-4">
                    <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 12%; height: 100%; position: fixed;">
                        <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                            <svg class="bi pe-none me-2" width="10" height="12"><img src="/img/logo.png" style="width: 70%; height: auto;"></svg>
                        </a>
                        <hr>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li>
                                <a href="/home" class="nav-link text-white" aria-current="page">Home</a></li>
                            <li>
                                <a href="/task" class="nav-link text-white">Task view</a>
                            </li>
                            <li>
                                <a href="/schedule" class="nav-link text-white">Schedule</a>
                            </li>
                            <li>
                                <a href="/availability" class="nav-link text-white"> Availability</a>
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
                </div>
                <div class="col-3">
                    <div class="mt-5">
                        <div class="mb-5">
                            <h2 class="mb-2">Account Details</h2>
                            <form method="POST">
                                <div class="mb-3 d-flex justify-content-between">
                                    <label for="email" class="form-label">Email</label>
                                    <input class="form-control w-50" type="email" name="email" value="${user.email}" requiired>
                                </div>
                                <div class="mb-3 d-flex justify-content-between">
                                    <label for="phone" class="form-label">Phone Number</label>
                                    <input class="form-control w-50" type="phone" name="phone" value="${user.phone}" required>
                                </div>
                                <div class="mb-3 d-flex justify-content-between form-check form-switch">
                                    <label for="public" class="form-check-label">Public</label>
                                    <input class="form-check-input" type="checkbox" name="public" 
                                           <c:if test="${user.public1 == true}">checked
                                           </c:if>
                                </div>
                                <div class="mb-3 d-flex justify-content-between form-check form-switch">
                                    <label for="active" class="form-check-label">Active</label>
                                    <input class="form-check-input" type="checkbox" name="active" 
                                           <c:if test="${user.active == true}">checked
                                           </c:if>>
                                </div>
                                <input type="hidden" name="action" value="info">
                                <input class="btn btn-primary" type="submit" value="Submit Changes">
                            </form>
                            <c:if test="${infoMessage != null}">
                                <p data-mbd-animation="fade-in-out">${infoMessage}</p>
                            </c:if>
                        </div>
                        <div>
                            <h2 class="mb-2">Change Password</h2>
                            <form method="POST">
                                <c:if test="${passwordMessage != null}">
                                    <p>${passwordMessage}</p>
                                </c:if>
                                <div class="mb-3 d-flex justify-content-between">
                                    <label for="old-password" class="form-label">Old Password</label>
                                    <input type="password" class="form-control w-50" name="old-password" placeholder="Old Password" required> 
                                </div>
                                <div class="mb-3 d-flex justify-content-between">
                                    <label for="new-password-first" class="form-label">New Password</label>
                                    <input type="password" class="form-control w-50" name="new-password-first" placeholder="New Password" required> 
                                </div>
                                <div class="mb-3 d-flex justify-content-between">
                                    <label for="input-password-second" class="form-label">Re enter New Password</label>                                
                                    <input type="password" class="form-control w-50" name="new-password-second" placeholder="Re Enter Password" required> 
                                </div>
                                <input type="hidden" name="action" value="password">
                                <input class="btn btn-primary" type="submit" value="Update password">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html> 