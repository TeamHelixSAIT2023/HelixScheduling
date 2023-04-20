<%-- 
    Document   : OrganizationList
    Created on : 21-Mar-2023, 12:02:43 PM
    Author     : Eric
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>

        <title>Organization List</title>
    </head>
    <body>
        <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 12%; height: 100%; position: fixed;">
            <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                <svg class="bi pe-none me-2" width="10" height="12"><img src="/img/logo.png" style="width: 70%; height: auto;"></svg>
            </a>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li>
                    <a href="/home" class="nav-link text-white" aria-current="page">Home</a>
                </li>
                <li>
                    <a href="/task" class="nav-link text-white">Task view</a>
                </li>
                <li>
                    <a href="/schedule" class="nav-link text-white">Schedule</a>
                </li>
                <li>
                    <a href="/availability" class="nav-link text-white">Availability</a>
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

        <div class="d-flex justify-content-center">
            <div class="mt-5">
                <h1 class="mb-5">Organization List</h1>
                <c:if test="${empty user.organizationUserList}">
                    <h2>You are not a part of any organizations</h2>
                     <div class="col-6">
                   
                        <div class="h-20 mt-4 d-flex flex-column">
                            <a class="text-decoration-none text-dark" href="/joinOrganization">
                                <div class="mt-5 p-3 border border-primary border-2 rounded shadow">
                                    <h2>Join Organization</h2>
                            </a>
                                  
                        </div>
                                </div>
                        </div>
                        
                                 <div class="col-6">
                   
                        <div class="h-20 mt-4 d-flex flex-column">
                                  <a class="text-decoration-none text-dark" href="/registerOrganization">
                                <div class="mt-5 p-3 border border-primary border-2 rounded shadow">
                                    <h2>Create a Organization</h2>
                                    </a>
                                </div>
                                    
                        </div>
                    </div>
                                 
                        
                   
                    
                </c:if>
                <c:forEach var="orgUser" items="${user.organizationUserList}">
                    <a class="link-primary text-decoration-none" href="<c:url value='/organization?organization=${orgUser.organization.name}'/>">
                        <div class="border border-primary rounded m-2 p-3 bg-light">
                            <h2>${orgUser.organization.name}</h2>
                            <p>${orgUser.organization.description}</p>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
