<%-- 
    Document   : availability
    Created on : 6-Mar-2023, 7:30:27 PM
    Author     : Eric
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/reset.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <title>Availability</title>
    </head>
    <body>
        <div>
            <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 12%; height: 100%; position: fixed;">
                <a class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <svg class="bi pe-none me-2" width="10" height="12"><img src="/css/logo.png" style="width: 70%; height: auto;"></svg>
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
                        <a href="/gannt" class="nav-link text-white">

                            Gannt Chart
                        </a>
                    </li>
                    <li>
                        <a href="/availability" class="nav-link active">

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

        </div>
        <div class="mainBody" style="margin-left:13%;">
            <main>
                <c:choose>
                    <c:when  test="${(orgList == null) || (empty orgList)}"> 
                        <h1>This user isn't a part of any organizations</h1>
                    </c:when>
                    <c:otherwise>
                        <div >
                            <!-- currently does not work as intended, make into a drop down of links using bootstrap -->
                            <select class="btn btn-secondary dropdown-toggle" aria-haspopup="true" data-toggle="dropdown"  name="org-list-dropdown" id="org-list-dropdown">
                                <c:forEach var="org" items="${orgList}">
                                    <option value="${org.name}"><a href="<c:url value='/availability?organization=${org.name}'/>">${org.name}</a></option>
                                </c:forEach>  
                            </select>
                        </div>
                        <section>
                            <div>
                                <h2>Availability</h2>
                                <form method="POST">
                                    <c:forEach var="day" items="${orgUser.availabilityList}">
                                        <div>
                                            <p>${day.dayOfWeek}</p>
                                            <div>
                                                <fmt:formatDate type="time" var="startTime" pattern="HH:mm" value="${day.startTime}"/>
                                                <fmt:formatDate type="time" var="endTime" pattern="HH:mm" value="${day.endTime}"/>
                                                <label for="${day.dayOfWeek}-unavailable">Unavailable:</label>
                                                <input type="checkbox" id="${day.dayOfWeek}-unavailable" name="${day.dayOfWeek}-unavailable"
                                                       <c:if test="${(startTime == '00:00') && (endTime == '00:00')}">
                                                           checked
                                                       </c:if>
                                                       >
                                            </div>
                                            <div>
                                                <label for="${day.dayOfWeek}-start">Start:</label>
                                                <input type="time" id="${day.dayOfWeek}-start" name="${day.dayOfWeek}-start" value="${startTime}">
                                            </div>
                                            <div>
                                                <label for="${day.dayOfWeek}-end">End:</label>
                                                <input type="time" id="${day.dayOfWeek}-end" name="${day.dayOfWeek}-end"  value="${endTime}">
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <input class="btn btn-primary" type="hidden" name="action" value="availability">
                                    <input class="btn btn-primary" type="submit" value="Update">
                                </form>
                                <div>
                                    <c:if test="${availabilityUpdateMessage != null}">
                                        <p>${availabilityUpdateMessage}</p>
                                    </c:if>
                                </div>
                            </div>
                        </section>
                        <section>
                            <h2>Unavailable Dates</h2>
                            <c:forEach var="unavailable" items="${orgUser.unavailableList}">
                                <div>
                                    <p><fmt:formatDate type="date" dateStyle="short" value="${unavailable.date}"/></p>
                                    <p>${unavailable.reason}</p>
                                </div>
                            </c:forEach>
                            <form method="POST">
                                <div>
                                    <!-- would be nice to use bootstraps datepicker here -->
                                    <label for="date">Date:</label>
                                    <input type="date" name="date" id="date" required>
                                </div>
                                <div>
                                    <label for="reason">Reason (optional):</label>
                                    <input type="text" name="reason" id="reason">
                                </div>
                                <input class="btn btn-primary" type="hidden" name="action" value="unavailable">
                                <input class="btn btn-primary" type="submit" value="Submit">
                            </form>
                        </section>
                    </c:otherwise>
                </c:choose>

            </main>
        </div>
</html>
