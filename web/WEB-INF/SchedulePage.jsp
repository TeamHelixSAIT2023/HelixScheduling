<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
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

        <title>Schedule View</title>

    </head>
    <body>
        <div>
            <div class="row" style="height: 100%;">
                <div class="col-4">

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
                                <a href="/schedule" class="nav-link active">

                                    Schedule
                                </a>
                            </li>
                            <li>
                                <a href="/gannt" class="nav-link text-white">

                                    Gantt Chart
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

                </div>


                <!--Main content area-->
                <div class="col-6">
                    <div class="mainBody" style="margin-left:13%;">
                        <main>
                            <!--Tabs (List View, Calender View)-->
                            <ul class="nav nav-tabs mt-5" id="pills-tab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="pills-list-tab" data-bs-toggle="pill" data-bs-target="#pills-list" type="button" role="tab" aria-controls="pills-list" aria-selected="true">List View</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-calender-tab" data-bs-toggle="pill" data-bs-target="#pills-calender" type="button" role="tab" aria-controls="pills-calender" aria-selected="false">Calender View</button>
                                </li>

                            </ul>
                            <!--End: Tabs-->

                            <!--Table display area-->

                            <div class="tab-content" id="pills-tabContent">
                                <div class="tab-pane fade show active" id="pills-list" role="tabpanel" aria-labelledby="pills-list-tab" tabindex="0">
                                    <c:choose>
                                        <c:when test="${empty shiftList}">
                                            <p>User has no shifts</p>
                                        </c:when>
                                        <c:otherwise>
                                            <!--Table of List View-->
                                            <table class="table table-striped table-hover">
                                                <tr class="table-dark">
                                                    <th>Organization</th>
                                                    <th>Start Date</th>
                                                    <th>End Date</th>
                                                    <th>Shift Type</th>
                                                </tr>
                                                <c:forEach var="shift" items="${shiftList}">
                                                    <tr>
                                                        <td>${shift.organizationUserSchedule.schedule.organization.name}</td>
                                                        <td><fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${shift.startDate}"/></td>
                                                        <td><fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${shift.endDate}"/></td>
                                                        <td>${shift.shiftType}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </c:otherwise>
                                    </c:choose>

                                    <!--Pagination-->
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination mt-5">
                                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                                        </ul>
                                    </nav>
                                    <!--Pagination End-->
                                    <!--End: Table of List View-->
                                </div>

                                <div class="tab-pane fade" id="pills-calender" role="tabpanel" aria-labelledby="pills-calender-tab" tabindex="0">

                                    <!--Calender View-->
                                    <c:if test="${message != null}">
                                        <p>${message}</p>
                                    </c:if>
                                    <ul class="dropdown-menu">
                                        <c:forEach var="orgUser" items="${user.organizationUserList}">
                                            <li><a href="/schedule?organization=${orgUser.organization.name}">${orgUser.organization.name}</a></li>
                                            </c:forEach>
                                    </ul>
                                    <ul class="dropdown-menu">
                                        <c:forEach var="orgSchedule" items="${orgScheduleList}">
                                            <fmt:formatDate type="date" pattern="yyyy-MM-dd" var="orgScheduledate" value="${orgSchedule.startDate}"/> 
                                            <li><a href="<c:url value="/schedule?organization=${org}&startDate=${orgScheduledate}}"/>"><fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${orgSchedule.startDate}"/> - <fmt:formatDate type="date" dateStyle="short" value="${orgSchedule.endDate}"/></a></li>
                                            </c:forEach>
                                    </ul>
                                    <table class="table table-striped table-hover">
                                        <tr class="table-dark">
                                            <th></th>
                                                <c:forEach var="date" items="${dateList}">
                                                <th><fmt:formatDate type="date" pattern="E-M-dd" value="${date}"/></th>
                                                </c:forEach>
                                        </tr>
                                        <fmt:formatDate type="date" pattern="yyyy-MM-dd" var="scheduleDate" value="${schedule.startDate}"/>
                                        <c:forEach var="orgUserSchedule" items="${schedule.organizationUserScheduleList}">
                                            <tr>
                                                <td>${orgUserSchedule.organizationUser.user.firstName} ${orgUserSchedule.organizationUser.user.lastName}</td>
                                                <c:forEach var="shift" items="${orgUserSchedule.shiftList}">
                                                    <td>
                                                        <c:if test="${shift != null}">
                                                            <p><fmt:formatDate type="time" pattern="h:mm a" value="${shift.startDate}"/></p>
                                                            <p><fmt:formatDate type="time" pattern="h:mm a" value="${shift.endDate}"/></p>
                                                            <c:if test="${shift.shiftType != null}">
                                                                <p>${shift.shiftType}</p>
                                                            </c:if>
                                                            <c:if test="${orgUser.admin == true}">
                                                                <a class="btn btn-outline-primary" href="<c:url value="/schedule?organization=${orgUser.organization.name}&startDate=${scheduleDate}&action=delete&shift=${shift.shiftID}"/>">Remove Shift</a>
                                                            </c:if>
                                                        </c:if>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>


                                    </table>

                                    <!--Add button-->
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addShiftModal">
                                        Add Shift
                                    </button>

                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addScheduleModal">
                                        Add Schedule
                                    </button>

                                    <!-- Modal (ADD Shift pop up menu) -->
                                    <div class="modal fade" id="addShiftModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="addShiftModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="addShiftModalLabel">Add Shift</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form method="POST">
                                                        <div class="row mt-2">
                                                            <label for="orgMemberShift" class="col-3 col-form-label"> Create For:</label>
                                                            <div class="col-sm-8">
                                                                <select name="orgMemberShift">
                                                                    <c:choose>
                                                                        <c:when test="${orgUser.dept != null}">
                                                                            <c:forEach var="orgMember" items="${orgUser.dept.organizationUserList}">
                                                                                <option value="${orgMember.organizationUserID}">${orgMember.user.firstName} ${orgMember.user.lastName}</option>
                                                                            </c:forEach>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:forEach var="orgMember" items="${orgUser.organization.organizationUserList}">
                                                                                <option value="${orgMember.organizationUserID}">${orgMember.user.firstName} ${orgMember.user.lastName}</option>
                                                                            </c:forEach>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </select>
                                                            </div>
                                                        </div>

                                                        <fmt:formatDate type="date" pattern="yyyy-MM-dd" var="startDate" value="${schedule.startDate}"/>
                                                        <fmt:formatDate type="date" pattern="yyyy-MM-dd" var="endDate" value="${schedule.endDate}"/>
                                                        <label for="date" class="col-3 col-form-label">Date:</label>
                                                        <input class="col-sm-4 mt-2" type="date" name="date" id="shiftDate" min="${startDate}" max="${endDate}" required> 
                                                        <div>
                                                            <label for="start-time" class="col-3 col-form-label">Start Time:</label>
                                                            <input class="col-sm-4 mt-2" type="time" name="start-time" id="start-time" value="00:00" required>
                                                        </div>
                                                        <div>
                                                            <label for="end-time" class="col-3 col-form-label">End Time:</label>
                                                            <input class="col-sm-4 mt-2" type="time" name="end-time" id="end-time" value="00:00" required>
                                                        </div>
                                                        <div>
                                                            <label for="shift-type" class="col-3 col-form-label">Shift Type:</label>
                                                            <input class="col-sm-4 mt-2" type="text" name="shift-type" id="shift-type">
                                                        </div>
                                                        <div>
                                                            <input type="hidden" name="action" value="new-shift" required>
                                                            <input class="btn btn-primary mt-5" type="submit" value="Add Shift">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End: Modal (ADD Shift) -->

                                    <!-- Modal (ADD Schedule pop up menu) -->
                                    <div class="modal fade" id="addScheduleModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="addSchedulelLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="addScheduleModalLabel">Create Schedule</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form method="POST">
                                                        <div>
                                                            <label for="org" class="col-3 col-form-label"> Organization:</label>
                                                            <select class="col-sm-4 mt-2" name="orgName">
                                                                <c:forEach var="organization" items="${user.organizationUserList}">
                                                                    <option value="${organization.organization.name}"
                                                                            <c:if test="${organization == orgUser.organization}"> selected </c:if>
                                                                            required>${organization.organization.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <label for="dept" class="col-3 col-form-label">Department:</label>
                                                            <select class="col-sm-4 mt-2" name="dept">
                                                                <c:forEach var="dept" items="${orgUser.organization.departmentList}">
                                                                    <option value="${dept.title}">${dept.title}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <label for="start-date" class="col-3 col-form-label">Start Date:</label>
                                                            <input class="col-sm-4 mt-2" type="date" name="start-date" id="start-date">
                                                        </div>
                                                        <div>
                                                            <label for="copy-forward" class="col-8 col-form-label">Copy Previous Schedule Forward</label>
                                                            <input type="radio" name="copy-forward" id="copy-forward">
                                                        </div>
                                                        <div>
                                                            <input type="hidden" name="action" value="new-schedule"> 
                                                            <input class="btn btn-primary mt-5" type="submit" value="New Schedule">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End: Modal (ADD Schedule) -->

                                    <c:if test="${orgUser.admin == true}">

                                        <!--deleted "add shift" and "create schedule" form-->

                                    </c:if>
                                        
                                </div>
                        </main>
                    </div>
                </div>
            </div>
        </div>

    </div>

</body>
</html> 
