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
        <div class="row" style="height: 100%;">
            <div class="col-4">

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
                            <a href="/schedule" class="nav-link active">
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

                        <!--Table of List View-->
                        <table class="table table-striped table-hover">
                            <tr class="table-dark">
                                <th></th>
                                <th> Start date </th>
                                <th> Due date </th>
                                <th class='text-center'> My Tasks </th>
                                <th class='text-center'> Priority  </th>
                            </tr>
                            <tr><td></td><td class="h6 text-primary">January</td></tr>
                            <tr>
                                <td>  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> </td>
                                <td>2023-01-25 </td>
                                <td>2023-01-30</td>
                                <td>Task 1</td>
                                <td class='text-center'>High</td>     
                            </tr>
                            <tr><td></td><td class="h6 text-primary">February</td></tr>
                            <tr>
                                <td>  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> </td>
                                <td>2023-02-15 </td>
                                <td>2023-02-20</td>
                                <td>Task 2</td>
                                <td class='text-center'>High</td>     
                            </tr>
                        </table>
                        <!--Circle ADD icon-->
                        <a href="#"><i class="bi bi-plus-circle" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="font-size: 3rem; color: #0275d8;"></i></a>

                        <!-- Modal (ADD task pop up menu) -->
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Task Details</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form>
                                            <div class="row mt-2">
                                                <label for="title" class="col-3 col-form-label"> Title</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="text" placeholder="" aria-label="">
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="description" class="col-3 col-form-label"> Description</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="text" placeholder="" aria-label="">
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="organization" class="col-3 col-form-label"> Organization</label>
                                                <div class="dropdown col-sm-8">
                                                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Select
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><button class="dropdown-item" type="button">Company A</button></li>
                                                        <li><button class="dropdown-item" type="button">Company B</button></li>
                                                        <li><button class="dropdown-item" type="button">Company C</button></li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="priority" class="col-3 col-form-label"> Priority</label>
                                                <div class="dropdown col-sm-8">
                                                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Select
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><button class="dropdown-item" type="button">Hight</button></li>
                                                        <li><button class="dropdown-item" type="button">Normal</button></li>
                                                        <li><button class="dropdown-item" type="button">Low</button></li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="assign_to" class="col-3 col-form-label"> Assign to</label>
                                                <div class="dropdown col-sm-8">
                                                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Select
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><button class="dropdown-item" type="button">Staff A</button></li>
                                                        <li><button class="dropdown-item" type="button">Staff B</button></li>
                                                        <li><button class="dropdown-item" type="button">Staff C</button></li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="start_date" class="col-3 col-form-label"> Start date</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="text" placeholder="2023-04-20" aria-label="">
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="due_date" class="col-3 col-form-label"> Due date</label>
                                                <div class="col-sm-8">
                                                    <input class="form-control" type="text" placeholder="2023-04-20" aria-label="">
                                                </div>
                                            </div>

                                            <div class="row mt-2">
                                                <label for="recurring" class="col-3 col-form-label"> Recurring</label>
                                                <td>  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> </td>
                                                <label for="repeat" class="col-2 col-form-label"> Repeat</label>

                                                <div class="dropdown col-sm-6">
                                                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Daily
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><button class="dropdown-item" type="button">Daily</button></li>
                                                        <li><button class="dropdown-item" type="button">Weekly</button></li>
                                                        <li><button class="dropdown-item" type="button">Monthly</button></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <label for="recurring" class="col-3 col-form-label"> </label>
                                                <td>  <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> </td>
                                                <label for="until" class="col-2 col-form-label"> Until</label>

                                                <div class="dropdown col-sm-6">
                                                    <input class="form-control" type="text" placeholder="2023-04-20" aria-label="">
                                                </div>
                                            </div>
                                            <div class="row mt-2">
                                                <label for="status" class="col-3 col-form-label"> Status</label>
                                                <div class="dropdown col-sm-8">
                                                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Select
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><button class="dropdown-item" type="button">Open</button></li>
                                                        <li><button class="dropdown-item" type="button">Completed</button></li>
                                                    </ul>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End: Modal (ADD task pop up menu) -->


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
                        <!--End: Table of List View-->
                    </div>

                    <div class="tab-pane fade" id="pills-calender" role="tabpanel" aria-labelledby="pills-calender-tab" tabindex="0">
                        <!--Calender View-->


                    </div>

                </div>
                <!--End: Table display area-->

            </div>
            <!--End: Main content area--> 

        </div>



    </body>
</html> 
