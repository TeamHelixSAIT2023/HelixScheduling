<%-- 
    Document   : OrganizationPage
    Created on : 21-Mar-2023, 12:55:24 PM
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

        <title>Organization Page</title>
    </head>

    <body>
        <div class="d-flex" style="height: 100%;">
            <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark fixed-top" style="width: 20%; height: 100vh;">
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
                            <button type="button" class="btn btn-dark dropdown-toggle active" data-bs-toggle="dropdown" aria-expanded="false">
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


            <div class="d-flex justify-content-center" style="width: 60%; margin-left: 30%">
                <div class="w-100 h-100 pt-3 mainBody">
                    <h1 class="mb-3">${org.name}</h1>
                    <c:if test="${orgUser.admin}">
                        <div class="mb-3">

                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit-Organization">
                                Edit Organization
                            </button>

                        </div>

                        <div id="edit-Organization" class="modal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit ${org.name}</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form method="POST">
                                        <div class="modal-body">
                                            <div>
                                                <label for="name">Name:</label>
                                                <input type="text" name="name" id="name" value="${org.name}" 
                                                       <c:if test="${not orgUser.admin}">readonly
                                                       </c:if> required> 
                                            </div>
                                            <div>
                                                <label for="description">Description:</label>
                                                <input type="text" name="description" id="description" value="${org.description}" 
                                                       <c:if test="${not orgUser.admin}">readonly
                                                       </c:if> required> 
                                            </div>
                                            <div>
                                                <label for="public">Public:</label>
                                                <input type="checkbox" name="public" id="public" 
                                                       <c:if test="${org.public1}">checked="checked"
                                                       </c:if> 
                                                       <c:if test="${not orgUser.admin}">readonly
                                                       </c:if>>
                                            </div>
                                            <div>
                                                <label for="man-approved-avail-change">Manager Approved Availability Change:</label>
                                                <input type="checkbox" name="man-approved-avail-change" id="man-approved-avail-change" 
                                                       <c:if test="${org.managerApprovedAvailabilityChange}">checked="checked"
                                                       </c:if> 
                                                       <c:if test="${not orgUser.admin}">readonly
                                                       </c:if>>
                                            </div>
                                            <div>
                                                <label for="man-approved-shift-swap">Manager Approved Shift Swap:</label>
                                                <input type="checkbox" name="man-approved-shift-swap" id="man-approved-shift-swap" 
                                                       <c:if test="${org.managerApprovedShiftSwap}">checked="checked"
                                                       </c:if> <c:if test="${not orgUser.admin}">readonly
                                                       </c:if>>
                                            </div>
                                            <div>
                                                <label for="man-approved-time-off">Manager Approved Time Off:</label>
                                                <input type="checkbox" name="man-approved-time-off" id="man-approved-time-off" 
                                                       <c:if test="${org.managerApprovedTimeOff}">checked="checked"
                                                       </c:if> 
                                                       <c:if test="${not orgUser.admin}">readonly
                                                       </c:if>>
                                            </div>
                                            <input type="hidden" name="action" value="info">

                                        </div>

                                        <div class="modal-footer">
                                            <input type="submit" class="btn btn-primary" value="Edit">
                                        </div>
                                    </form>


                                </div>

                            </div>
                        </div>
                    </c:if>


                    <div>
                        <h2>Members</h2>
                        <p>${orgEditMessage}</p>
                        <p>${orgUserMessage}</p>
                        <c:if test="${orgUser.admin}">

                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-user">
                                Add User
                            </button>



                            <div id="add-user" class="modal">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Add User</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form method="POST">
                                            <div class="modal-body">

                                                <p>${orgUserMessage}</p>
                                                <div class="mb-2">
                                                    <label class="col-3 col-form-label" for="email">Email:</label>
                                                    <input class="col-sm-4 form-control" type="email" name="email" id="email" required>
                                                </div>
                                                <div class="mb-2">
                                                    <label class="form-label" for="dept">Department:</label>
                                                    <select class="form-control" name="dept" id="dept">
                                                        <c:forEach var="dept" items="${org.departmentList}">
                                                            <option value="${dept.deptID}">${dept.title}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="mb-2">
                                                    <label for="manager">Managed By:</label>
                                                    <select class="form-control" name="manager" id="manager">
                                                        <c:forEach var="orgMember" items="${org.organizationUserList}">
                                                            <option value="${orgMember.organizationUserID}">${orgMember.user.firstName} ${orgMember.user.lastName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="mb-2">
                                                    <label for="hourly">Hourly:</label>
                                                    <input type="number" name="hourly" id="hourly" min="0" step="0.01" value="0.00">
                                                </div>
                                                <div class="mb-2 form-check form-switch">
                                                    <label class="form-check-label" for="admin">Administrator:</label>
                                                    <input class="form-check-input" type="checkbox" name="admin" id="admin" value="admin">
                                                </div>
                                                <input type="hidden" name="action" value="new-user">
                                            </div>
                                            <div class="modal-footer">
                                                <input class="btn btn-primary" type="submit" value="Add New User">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="mt-3 mb-5 d-flex flex-wrap justify-content-start">
                        <c:forEach var="orgMember" items="${org.organizationUserList}">
                            <div class="me-2 mb-2 p-3 w-25 border border-primary border-1 rounded shadow">
                                <div>
                                    <p>${orgMember.user.firstName} ${orgMember.user.lastName}</p>
                                    <p>${orgMember.dept.title}</p>
                                    <p>${orgMember.user.phone}</p>
                                    <p>${orgMember.user.email}</p>

                                    <c:if test="${orgUser.admin}">
                                        <p>$${orgMember.hourly}</p>
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit-user-${orgMember.organizationUserID}">
                                            Edit User
                                        </button>
                                    </c:if>
                                </div>
                                <div id="edit-user-${orgMember.organizationUserID}" class="modal">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Member Details of ${orgMember.user.firstName} ${orgMember.user.lastName}</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form method="POST">
                                                <div class="modal-body">

                                                    <p>${orgUserEditMessage}</p>
                                                    <div class="mb-2">

                                                        <input type="hidden" name="editEmail" id="editEmail" value="${orgMember.user.email}">
                                                        <input type="hidden" name="userID" id="userID" value="${orgMember.organizationUserID}" required>
                                                    </div>
                                                    <div class="mb-2">
                                                        <label for="newdept">Department:</label>
                                                        <select class="form-control" name="newdept" id="newdept">
                                                            <c:forEach var="dept" items="${org.departmentList}">
                                                                <option value="${dept.deptID}">${dept.title}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="mb-2">
                                                        <label for="newmanager">Managed By:</label>
                                                        <select class="form-control" name="newmanager" id="newmanager">
                                                            <option value="" selected>--None--</option>
                                                            <c:forEach var="orgMember" items="${org.organizationUserList}">
                                                                <option value="${orgMember.organizationUserID}">${orgMember.user.firstName} ${orgMember.user.lastName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="mb-2">
                                                        <label for="newhourly">Hourly:</label>
                                                        <input type="number" name="newhourly" id="newhourly" min="0" step="0.01" value="0.00">
                                                    </div>
                                                    <div class="mb-2 form-check form-switch">
                                                        <label class="form-check-label" for="newadmin">Administrator:</label>
                                                        <input class="form-check-input" type="checkbox" name="newadmin" value="newadmin">
                                                    </div>
                                                    <input type="hidden" name="action" value="edit-user">

                                                </div>
                                                <div class="modal-footer">
                                                    <input class="btn btn-primary" type="submit" value="Edit User">
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="mb-5">

                        <h2 class="mb-3">Schedules</h2>
                        <div class="d-flex flex-wrap justify-content-start">
                            <c:forEach var="schedule" items="${org.scheduleList}">
                                <div class="me-2 mb-2 p-2 border border-primary rounded shadow">
                                    <fmt:formatDate type="date" pattern="yyyy-MM-dd" var="startDate" value="${schedule.startDate}"/>
                                    <a class="text-decoration-none" href="<c:url value="schedule?organization=${schedule.organization.name}&startDate=${startDate}"/>">
                                        <h3>${schedule.dept.title}</h3>
                                        <p><fmt:formatDate type="date" value="${schedule.startDate}"/> - <fmt:formatDate type="date" value="${schedule.endDate}"/></p>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div>
                        <h2>Departments</h2>

                        <p>${deptMessage}</p>
                        <div class="mb-3 mb-2 d-flex flex-wrap justify-content-start">
                            <c:forEach var="dept" items="${org.departmentList}">
                                <div class="w-25 me-2 p-2 border border-primary rounded shadow">
                                    <div>
                                        <h4>${dept.title}</h4>
                                        <p>${dept.deptNo}</p>
                                        <p>${dept.description}</p>
                                        <c:if test="${orgUser.admin}">
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit-dept-${dept.deptNo}">
                                                Edit ${dept.title}
                                            </button>
                                        </c:if>
                                    </div>


                                    <div id="edit-dept-${dept.deptNo}" class="modal">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit ${dept.title}</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <form method="POST">
                                                    <div class="modal-body">

                                                        <div>

                                                            <label for="edit-dept-title">Title:</label><br>
                                                            <textarea class="form-control" id="edit-dept-title" name="edit-dept-title" cols="50" rows="0" maxlength="50" required>${dept.title}</textarea>

                                                        </div>
                                                        <div>

                                                            <label for="edit-dept-description">Description:</label><br>
                                                            <textarea class="form-control" id="edit-dept-description" name="edit-dept-description" rows="5" cols="50" maxlength="100" required>${dept.description}</textarea>
                                                            <input type="hidden" name="edit-dept-no" value="${dept.deptNo}"> 
                                                            <input type="hidden" name="edit-dept-id" value="${dept.deptID}">
                                                            <input type="hidden" name="action" value="edit-dept">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <input class="btn btn-primary" type="submit" value="Edit ${dept.title}">
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div>
                            <c:if test="${orgUser.admin}">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-dept-modal">
                                    Add Department
                                </button>
                                <div id="add-dept-modal" class="modal">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Add Department</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <form method="POST">
                                                <div class="modal-body">
                                                    <label for="dept-title">Title:</label><br>
                                                    <textarea class="form-control" id="dept-title" name="dept-title" cols="50" rows="1" maxlength="50"></textarea>

                                                    <label for="edit-dept-description">Description:</label><br>
                                                    <textarea class="form-control" id="dept-description" name="dept-description" rows="5" cols="50" maxlength="100"></textarea>

                                                    <div>
                                                        <label for="dept-no">Number:</label>
                                                        <input type="number" name="dept-no" min="0" max="99" step="1"> 
                                                    </div>
                                                    <input type="hidden" name="action" value="new-dept"><br>

                                                </div>
                                                <div class="modal-footer">
                                                    <input class="btn btn-primary" type="submit" value="Add New Department">
                                                </div>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>







