<%-- 
    Document   : availability
    Created on : 6-Mar-2023, 7:30:27 PM
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
        <title>Availability</title>
    </head>
    <body>
        <div>
            <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 220px; height: 100%;">
                <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <svg class="bi pe-none me-2" width="10" height="12"><img src="/css/logo.png" style="width: 150px; height: auto;"></svg>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li>
                        <a href="/home" class="nav-link text-white" aria-current="page">
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
                            Gannt Chart
                        </a>
                    </li>
                    <li>
                        <a href="/availability" class="nav-link active">
                            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
                            Availability
                        </a>
                    </li>
                    <li>
                <a href="/account" class="nav-link text-white">
                  <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"></use></svg>
                  Account
                </a>
              </li>
                </ul>
                <hr>
                <div class="dropdown">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="" class="rounded-circle me-2" width="32" height="32">
                        <strong>${user.firstName}</strong>
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
        <main>
            <c:choose>
                <c:when  test="${(orgList == null) || (empty orgList)}"> 
                    <h1>This user isn't a part of any organizations</h1>
                </c:when>
                <c:otherwise>
                    <div>
                        <select name="org-list-dropdown" id="org-list-dropdown">
                            <c:forEach var="org" items="${orgList}">
                                <option value="${org.name}"><a href="<c:url value='/availability?organization=${org.name}'/>">${org.name}</a></option>
                            </c:forEach>  
                        </select>
                    </div>
                    <div>
                        <form method="POST">
                            <c:forEach var="day" items="${availabilityList}">
                                <div>
                                    <p>${day.dayOfWeek}</p>
                                    <div>
                                        <label for="${day.dayOfWeek}-unavailable">Unavailable:</label>
                                        <input type="checkbox" id="${day.dayOfWeek}-unavailable" name="${day.dayOfWeek}-unavailable"
                                               <c:if test="${(availabilityList[0].startTime == '00:00:00') && (availabilityList[0].endTime == '00:00:00')}">
                                                   checked
                                               </c:if>
                                               >
                                    </div>
                                    <div>
                                        <label for="${day.dayOfWeek}-start">Start:</label>
                                        <input type="time" id="${day.dayOfWeek}-start" name="${day.dayOfWeek}-start" value="${availabilityList[0].startTime}">
                                    </div>
                                    <div>
                                        <label for="${day.dayOfWeek}-end">End:</label>
                                        <input type="time" id="${day.dayOfWeek}-end" name="${day.dayOfWeek}-end"  value="${availabilityList[0].endTime}">
                                    </div>
                                </div>
                            </c:forEach>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>

        </main>
</html>
