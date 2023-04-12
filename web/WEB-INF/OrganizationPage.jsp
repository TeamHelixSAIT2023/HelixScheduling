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
        <title>Organization Page</title>
    </head>

    <body>
        <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 220px; height: 100%;">
            <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                <svg class="bi pe-none me-2" width="10" height="12"><img src="/css/logo.png" style="width: 150px; height: auto;"></svg>
            </a>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li>
                    <a href="/home" class="nav-link active" aria-current="page">
                        <svg class="bi pe-none me-2" width="16" height="16" ><use xlink:href="#home"></use></svg>
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
                    <a href="/availability" class="nav-link text-white">
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
        <div>
            <h1>${org.name}</h1>
            <div>
                <form method="POST">
                    <div>
                        <label for="name">Name:</label>
                        <input type="text" name="name" id="name" value="${org.name}" <c:if test="${not orgUser.admin}">readonly</c:if>> 
                        </div>
                        <div>
                            <label for="description">Description:</label>
                            <input type="text" name="description" id="description" value="${org.description}" <c:if test="${not orgUser.admin}">readonly</c:if>> 
                        </div>
                        <div>
                            <label for="public">Public:</label>
                            <input type="checkbox" name="public" id="public" <c:if test="${org.public1}">checked="checked"</c:if> <c:if test="${not orgUser.admin}">readonly</c:if>>
                        </div>
                        <div>
                            <label for="man-approved-avail-change">Manager Approved Availability Change:</label>
                                <input type="checkbox" name="man-approved-avail-change" id="man-approved-avail-change" <c:if test="${org.managerApprovedAvailabilityChange}">checked="checked"</c:if> <c:if test="${not orgUser.admin}">readonly</c:if>>
                        </div>
                        <div>
                            <label for="man-approved-shift-swap">Manager Approved Shift Swap:</label>
                                <input type="checkbox" name="man-approved-shift-swap" id="man-approved-shift-swap" <c:if test="${org.managerApprovedShiftSwap}">checked="checked"</c:if> <c:if test="${not orgUser.admin}">readonly</c:if>>
                        </div>
                        <div>
                            <label for="man-approved-time-off">Manager Approved Time Off:</label>
                                <input type="checkbox" name="man-approved-time-off" id="man-approved-time-off" <c:if test="${org.managerApprovedTimeOff}">checked="checked"</c:if> <c:if test="${not orgUser.admin}">readonly</c:if>>
                        </div>
                    <c:if test="${orgUser.admin}">
                        <input type="hidden" name="action" value="info">
                        <input type="submit" value="Edit">
                    </c:if>
                </form>
            </div>
            <div>
                <h2>Members</h2>
                <div>
                    <c:forEach var="orgMember" items="${org.organizationUserList}">
                        <div>
                            <p>${orgMember.user.firstName} ${orgMember.user.lastName}</p>
                            <p>${orgMember.dept.title}</p>
                            <p>${orgMember.user.phone}</p>
                            <c:if test="${orgUser.admin}">
                                <button type="button" class="btn btn-primary" onclick="showEditModal()">Edit User</button>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
                <div id="edit-user-modal" class="modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Task Details</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <label for="department">Department:</label>
                                    <input type="text" id="department" name="department" required><br><br>
                                    <label for="hourly-rate">Hourly Rate:</label>
                                    <input type="number" id="hourly-rate" name="hourly-rate" required><br><br>
                                    <label for="managed-by">Managed By:</label>
                                    <input type="text" id="managed-by" name="managed-by"><br><br>
                                    <label for="admin-status">Admin Status:</label>
                                    <input type="checkbox" id="admin-status" name="admin-status"><br><br>
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${orgUser.admin}">
                    <h2>Add new member</h2>
                    <form method="POST">
                        <p>${orgUserMessage}</p>
                        <div>
                            <label for="email">Email:</label>
                            <input type="email" name="email" id="email" required>
                        </div>
                        <div>
                            <label for="dept">Department:</label>
                            <select name="dept" id="dept">
                                <c:forEach var="dept" items="${org.departmentList}">
                                    <option value="${dept.deptID}">${dept.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="manager">Managed By:</label>
                            <select name="manager" id="manager">
                                <c:forEach var="orgMember" items="${org.organizationUserList}">
                                    <option value="${orgMember.organizationUserID}">${orgMember.user.firstName} ${orgMember.user.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="hourly">Hourly:</label>
                            <input type="number" name="hourly" id="hourly" min="0" step="0.01" value="0.00">
                        </div>
                        <div>
                            <label for="admin">Administrator:</label>
                            <input type="radio" name="admin" id="admin" value="admin">
                        </div>
                        <input type="hidden" name="action" value="new-user">
                        <input type="submit" value="Add New User">
                    </form>
                </c:if>
            </div>
            <div>
                <h2>Schedules</h2>
                <c:forEach var="schedule" items="${org.scheduleList}">
                    <div>
                        <a href="schedule?id=${schedule.scheduleID}">
                            <h3>${schedule.dept.title}</h3>
                            <p><fmt:formatDate type="date" value="${schedule.startDate}"/> - <fmt:formatDate type="date" value="${schedule.endDate}"/></p>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div>
                <h2>Departments</h2>
                <div>
                    <c:forEach var="dept" items="${org.departmentList}">
                        <div>
                            <h4>${dept.title}</h4>
                            <p>${dept.deptNo}</p>
                            <p>${dept.description}</p>
                        </div>
                    </c:forEach>
                    <c:if test="${orgUser.admin}">
                        <p>${deptMessage}</p>
                        <form method="POST">
                            <div>
                                <label for="dept-title">Title:</label>
                                <input type="text" name="dept-title" id="dept-title" maxlength="50">
                            </div>
                            <div>
                                <label for="dept-description">Description</label>
                                <input type="text" name="dept-description" id="dept-description" maxlength="100">
                            </div>
                            <div>
                                <label for="dept-no">Number:</label>
                                <input type="number" name="dept-no" min="0" max="99" step="1"> 
                            </div>
                            <input type="hidden" name="action" value="new-dept">
                            <input type="submit" value="Add New Department">
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
    <script>
        const editButton = document.querySelector('#edit-button');

// add an event listener to the edit button that listens for a click event
        editButton.addEventListener('click', function () {
            // get a reference to the modal element
            const modal = document.querySelector('#edit-modal');

            // show the modal by setting its display property to "block"
            modal.style.display = 'block';
        });
    </script>
</html>
