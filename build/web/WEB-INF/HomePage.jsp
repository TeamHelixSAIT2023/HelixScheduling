<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
            body {
            background-color: white;
            }
            * {
               margin: 0;
               padding: 0;
               box-sizing: border-box;
               }
            .header {
                overflow-y:hidden;
                height: 50px;
                width: 100%;
                box-shadow: 0px 5px 5px lightblue;
                position: relative;
                align-items:center;
            }

            .header .searchHeader{
               background-color: inherit;
            }
            teamLogo {
                text-align: center;
                padding: 15px;
                color: white;
                background-color: blue;
                width: 180px;
                height: 50px;
                position: fixed;
             }

             .sideBar {
             background-color: lightgrey;
             display: flex;
             width: 180px;
             height: 100%;
             position: fixed;
             overflow-x: hidden;
             align-items:center;
             justify-content:center;
             }

             button{
             background-color: inherit;
             padding: 10px;
             display: inline-block;
             }

             button:hover{
             background:white;

             }

            .homeButton{
                top: 80px;
                border:none;
                position: fixed;
                cursor: pointer;
            }


        </style>
    </head>
    <body>
    <teamLogo>Team Helix</teamLogo>
    <div class="header">
        <input class="searchHeader" type="text" placeholder="Search name, task, date, etc">
    </div>

    <div class="sideBar">
        <button class="homeButton">Home</button>


    </div>

    </body>
</html>