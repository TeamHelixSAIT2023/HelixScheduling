<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <title>Home</title>
    </head>
    <body>
        <div class="d-flex" style="height: 100%;">
            <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 20%; height: 100vh;">
                <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <svg class="bi pe-none me-2" width="10" height="12"><img src="/img/logo.png" style="width: 70%; height: auto;"></svg>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li>
                        <a href="/home" class="nav-link active" aria-current="page">Home</a></li>
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
                            <ul class="dropdown-menu dropdown-menu-dark dropup text-small shadow">
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
            <div class="w-100 mx-auto d-flex justify-content-center">
                <div class="w-75 h-75">
                    <div class="h-20 mt-4 d-flex flex-column">
                        <a class="text-decoration-none text-dark" href="/schedule">
                            <div class="mt-5 p-3 border border-primary border-2 rounded shadow">
                                <h2>Shifts</h2>
                                <c:choose>
                                    <c:when test="${empty shiftList}">
                                        <p>No upcoming shifts</p>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-hover">
                                            <c:forEach var="shift" items="${shiftList}">
                                                <a href="/schedule?organization${shift.organizationUserSchedule.schedule.organization.name}"
                                                   <tr class="p-1">
                                                        <td>${shift.organizationUserSchedule.schedule.organization.name}</td>
                                                        <td><fmt:formatDate type="both" pattern="MMM. dd, yyyy h:ss a" value="${shift.startDate}"/></td>
                                                        <td>
                                                            <c:if test="${shift.endDate != null}">
                                                                <fmt:formatDate type="both" pattern="MMM. dd, yyyy h:ss a" value="${shift.endDate}"/>
                                                            </c:if>
                                                        </td>
                                                        <td>${shift.shiftType}</td>
                                                    </tr>
                                                </c:forEach>
                                        </table>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </a>
                        <a class="text-decoration-none text-dark" href="/task">
                            <div class="mt-5 p-3 border border-primary border-2 rounded shadow">
                                <h2>Tasks</h2>
                                <c:choose>
                                    <c:when test="${empty taskList}">
                                        <p>No upcoming tasks</p>
                                    </c:when>
                                    <c:otherwise>
                                        <table class="table table-striped table-hover">
                                            <c:forEach var="task" items="${taskList}">
                                                <tr class="p-1">
                                                    <td>${task.title}</td>
                                                    <td><fmt:formatDate type="date" pattern="MMM. dd, yyyy" value="${task.startDate}"/></td>
                                                    <td>
                                                        <c:if test="${task.endDate != null}">
                                                            <fmt:formatDate type="date" pattern="MMM. dd, yyyy" value="${task.endDate}"/>
                                                        </c:if>
                                                    </td>
                                                    <td>${task.priority}</td>
                                                    <td>${task.status}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html> 
