<%-- 
    Document   : todolist
    Created on : Nov 1, 2025, 9:39:29 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/todolist.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if(session.getAttribute("Logined")==null)
            {
        %>
        <h3>Bạn chưa đăng nhập</h3>
        <h3><a href="${pageContext.request.contextPath}/session/login.jsp">MỜI ĐĂNG NHẬP</a></h3>
        <%
            }
            else
            {
               String name = (String)session.getAttribute("name");
        %>
        <div>
            <%@include file="../includes/header.jsp" %>
        </div>
        <div>
            <%@include file="../includes/menu.jsp" %>
        </div>
        <div class="todolist-main-content">
            <div class="container">
                <div class="sidebar">
                    <div class="sidebar-header">
                        <i class="material-icons">menu</i>
                        <h2>Tasks</h2>
                    </div>
                    <ul class="task-lists">
                        <li class="active" data-list-id="my-tasks">
                            <i class="material-icons">list</i>
                            <span>My tasks</span>
                            <i class="material-icons delete-list" style="display:none;">delete</i>
                        </li>
                        <li data-list-id="important">
                            <i class="material-icons">star</i>
                            <span>Important</span>
                            <i class="material-icons delete-list">delete</i>
                        </li>
                        <li id="create-new-list">
                            <i class="material-icons">add</i>
                            <span>Create new list</span>
                        </li>
                    </ul>
                </div>
                <div class="main-content">
                    <div class="header">
                        <h1>My tasks</h1>
                        <div class="header-actions">
                            <i class="material-icons">sort</i>
                            <i class="material-icons">more_vert</i>
                        </div>
                    </div>
                    <ul class="tasks">
                        <li class="task">
                            <input type="checkbox" id="task1">
                            <p>Buy groceries</p>
                            <i class="material-icons details">notes</i>
                            <i class="material-icons date">event</i>
                            <i class="material-icons delete-task">delete</i>
                        </li>
                        <li class="task">
                            <input type="checkbox" id="task2">
                            <p>Call the doctor</p>
                            <i class="material-icons details">notes</i>
                            <i class="material-icons date">event</i>
                            <i class="material-icons delete-task">delete</i>
                        </li>
                        <!-- Add more tasks as needed -->
                    </ul>
                    <div class="add-task">
                        <i class="material-icons">add</i>
                        <input type="text" placeholder="Add task">
                        <button id="add-task-btn">Add</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="new-list-modal" class="modal">
            <div class="modal-content">
                <span id="close-modal" class="close">&times;</span>
                <h2>Create New List</h2>
                <input type="text" id="new-list-name" placeholder="Enter list name">
                <button id="create-list-btn">Create</button>
            </div>
        </div>
        <div>
            <%@include file="../includes/footer.jsp" %>
        </div>
        <%
            }
        %>

    </body>
</html>
