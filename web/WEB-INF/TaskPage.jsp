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

        <title>Task View</title>
    </head>
    <body>

        <div class="row" style="height: 100%;">
            <div class="col-4">

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
                                <a href="/task" class="nav-link active">

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

            </div>

            <!--Main content area-->
            <div class="col-6">

                <!-- Dropdown -->
                <div class="dropdown mt-5">
                    <button class="btn btn-sm btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Action
                    </button>
                    <ul class="dropdown-menu dropdown-menu-dark">
                        <li><a class="dropdown-item" href="#">Mark as Done</a></li>
                        <li><a class="dropdown-item" href="#">Archive</a></li>
                        <li><a class="dropdown-item" href="#">Delete</a></li>

                    </ul>
                </div>

                <!--Tabs (All Tasks, Pending, Archived-->
                <ul class="nav nav-tabs mt-5" id="pills-tab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="pills-all-tab" data-bs-toggle="pill" data-bs-target="#pills-all" type="button" role="tab" aria-controls="pills-all" aria-selected="true">All Tasks</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="pills-pending-tab" data-bs-toggle="pill" data-bs-target="#pills-pending" type="button" role="tab" aria-controls="pills-pending" aria-selected="false">Pending Tasks</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="pills-archived-tab" data-bs-toggle="pill" data-bs-target="#pills-archived" type="button" role="tab" aria-controls="pills-archived" aria-selected="false">Archived</button>
                    </li>
                </ul>
                <!--End: Tabs (All Tasks, Pending, Archived-->

                <!--Table display area-->
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade show active" id="pills-all" role="tabpanel" aria-labelledby="pills-all-tab" tabindex="0">

                        <!--Table of All Tasks-->
                        <table class="table table-striped table-hover">
                            <tr class="table-dark">
                                <th></th>
                                <th class='text-center'> My Tasks </th>
                                <th class='text-center'> Status </th>
                                <th class='text-center'> Start date </th>
                                <th class='text-center'> Due date </th>
                                <th class='text-center'> Assigned to</th>
                                <th class='text-center'> Priority  </th>
                            </tr>
                            <c:forEach var="task" items="${taskList}">
                                <tr>
                                    <td>  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> </td>
                                    <td>${task.title}</td>
                                    <td>${task.status}</td>
                                    <td><fmt:formatDate type="date" pattern="MMM. d, yyyy" value="${task.startDate}"/></td>
                                    <td><fmt:formatDate type="date" pattern="MMM. d, yyyy" value="${task.endDate}"/></td>
                                    <td>${task.organizationUser.user.firstName} ${task.organizationUser.user.lastName}</td>
                                    <td>${task.priority}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <!--End: Table of All Tasks-->
                    </div>
                    <div class="tab-pane fade" id="pills-pending" role="tabpanel" aria-labelledby="pills-pending-tab" tabindex="0">

                        <!--Table of Pending Tasks-->
                        <table class="table table-striped table-hover">
                            <tr class="table-dark">
                                <th></th>
                                <th class='text-center'> My Tasks </th>
                                <th class='text-center'> Status </th>
                                <th class='text-center'> Start date </th>
                                <th class='text-center'> Due date </th>
                                <th class='text-center'> Assigned to</th>
                                <th class='text-center'> Priority  </th>
                            </tr>
                            <c:forEach var="task" items="${currentTaskList}">
                                <tr>
                                    <td>  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> </td>
                                    <td>${task.title}</td>
                                    <td>${task.status}</td>
                                    <td><fmt:formatDate type="date" pattern="MMM. d, yyyy" value="${task.startDate}"/></td>
                                    <td><fmt:formatDate type="date" pattern="MMM. d, yyyy" value="${task.endDate}"/></td>
                                    <td>${task.organizationUser.user.firstName} ${task.organizationUser.user.lastName}</td>
                                    <td>${task.priority}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <!--End: Table of Pending Tasks-->
                    </div>

                    <div class="tab-pane fade" id="pills-archived" role="tabpanel" aria-labelledby="pills-archived-tab" tabindex="0">

                        <!--Table of Archived Tasks-->
                        <table class="table table-striped table-hover">
                            <tr class="table-dark">
                                <th></th>
                                <th class='text-center'> My Tasks </th>
                                <th class='text-center'> Status </th>
                                <th class='text-center'> Start date </th>
                                <th class='text-center'> Due date </th>
                                <th class='text-center'> Assigned to</th>
                                <th class='text-center'> Priority  </th>
                            </tr>
                            <c:forEach var="task" items="${archivedTaskList}">
                                <tr>
                                    <td>  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> </td>
                                    <td>${task.title}</td>
                                    <td>${task.status}</td>
                                    <td><fmt:formatDate type="date" pattern="MMM. d, yyyy" value="${task.startDate}"/></td>
                                    <td><fmt:formatDate type="date" pattern="MMM. d, yyyy" value="${task.endDate}"/></td>
                                    <td>${task.organizationUser.user.firstName} ${task.organizationUser.user.lastName}</td>
                                    <td>${task.priority}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <!--End: Table of Archived Tasks -->
                    </div>

                    <!--Circle ADD icon-->
                    <div class="dropdown mt-5">
                        <button class="btn btn-sm btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Create Task
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark">
                            <c:forEach var="orgUserCreateTask" items="${user.organizationUserList}">
                                <c:if test="${orgUserCreateTask.admin == true}">
                                    <li><button class="dropdown-item" type="button" data-bs-toggle="modal" data-bs-target="#${orgUserCreateTask.organization.organizationID}">${orgUserCreateTask.organization.name}</button></li>
                                    </c:if>
                                </c:forEach>

                        </ul>
                    </div>
                    <!-- Modal (ADD task pop up menu) -->
                    <c:forEach var="orgUser" items="${user.organizationUserList}">
                        <div class="modal fade" id="${orgUser.organization.organizationID}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="${orgUser.organization.organizationID}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h2 class="modal-title">Create Task For ${orgUser.organization.name}</h2>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form method="POST">
                                        <div class="modal-body">
                                            <h3 id="${orgUser.organization.name}">Task Details</h3>

                                            <div class="row mt-2">
                                                <label for="title" class="col-3 col-form-label">Title</label>
                                                <div class="col-sm-8">
                                                    <input name="title" class="form-control" type="text" placeholder="" aria-label="" required>
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="description" class="col-3 col-form-label">Description</label>
                                                <div class="col-sm-8">
                                                    <input name="description" class="form-control" type="text" placeholder="" aria-label="">
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <label for="priority" class="col-3 col-form-label">Priority</label>
                                                <div class="dropdown col-sm-8">
                                                    <select class="form-control" name="priority" required>
                                                        <c:forEach var="priorityType" items="${priorityList}">
                                                            <option value="${priorityType}">${priorityType}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="assign-to" class="col-3 col-form-label">Assign to</label>
                                                <div class="dropdown col-sm-8">
                                                    <select class="form-control" name="assign-to" required>
                                                        <c:forEach var="orgMember" items="${orgUser.organization.organizationUserList}">
                                                            <option value="${orgMember.organizationUserID}">${orgMember.user.firstName} ${orgMember.user.lastName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="start_date" class="col-3 col-form-label">Start date</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="date" name="start-date" aria-label="">
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="end-date" class="col-3 col-form-label">Due date</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="date" name="end-date" aria-label="">
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="recurring" class="col-3 col-form-label">Recurring</label>
                                                <td>  <input class="form-check-input" type="radio" name="recurring" id="flexCheckDefault"> </td>
                                                <label for="repeat" class="col-2 col-form-label">Repeat</label>

                                                <div class="dropdown col-sm-6">
                                                    <select class="form-control" name="recurring-type">
                                                        <option value="Daily" selected>Daily</option>
                                                        <option value="Weekly">Weekly</option>
                                                        <option value="Monthly">Monthly</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <label for="recurring-end" class="col-3 col-form-label"> </label>
                                                <td>  <input class="form-check-input" type="radio" name="recurring-end" id="flexCheckDefault"> </td>
                                                <label for="until-date" class="col-2 col-form-label"> Until</label>

                                                <div class="dropdown col-sm-6">
                                                    <input class="form-control" type="date" placeholder="2023-04-20" aria-label="" name="until-date">
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <label for="status" class="col-3 col-form-label"> Status</label>
                                                <div class="dropdown col-sm-8">
                                                    <select class="form-control" name="status" required>
                                                        <c:forEach var="statusOption" items="${statusList}">
                                                            <option value="${statusOption}">${statusOption}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                            <input class="btn btn-primary" type="submit" value="Submit">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- End: Modal (ADD task pop up menu) -->

                </div>
                <!--End: Table display area-->

                <!--Pagination-->
                <nav aria-label="Page navigation example">
                    <ul class="pagination mt-5">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
                <!--Pagination End-->

            </div>
            <!--End: Main content area-->

        </div>
    </body>
</html> 
