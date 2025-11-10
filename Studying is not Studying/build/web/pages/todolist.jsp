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

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const addTaskBtn = document.getElementById("add-task-btn");
            const taskInput = document.querySelector(".add-task input");
            const taskList = document.querySelector(".tasks");

            const createListBtn = document.getElementById("create-list-btn");
            const createNewListItem = document.getElementById("create-new-list");
            const newListModal = document.getElementById("new-list-modal");
            const closeModalBtn = document.getElementById("close-modal");
            const newListNameInput = document.getElementById("new-list-name");
            const sidebar = document.querySelector(".task-lists");

            // ========== THÊM TASK ==========
            addTaskBtn.addEventListener("click", addTask);
            taskInput.addEventListener("keypress", (e) => {
                if (e.key === "Enter")
                    addTask();
            });

            function addTask() {
                const taskText = taskInput.value.trim();
                if (taskText === "")
                    return;

                const li = document.createElement("li");
                li.classList.add("task");
                li.innerHTML = `
            <input type="checkbox">
            <p>${taskText}</p>
            <i class="material-icons details">notes</i>
            <i class="material-icons date">event</i>
            <i class="material-icons delete-task">delete</i>
        `;

                taskList.appendChild(li);
                taskInput.value = "";
            }

            // ========== XÓA TASK ==========
            taskList.addEventListener("click", (e) => {
                if (e.target.classList.contains("delete-task")) {
                    e.target.closest(".task").remove();
                }
            });

            // ========== ĐÁNH DẤU HOÀN THÀNH ==========
            taskList.addEventListener("change", (e) => {
                if (e.target.type === "checkbox") {
                    const text = e.target.nextElementSibling;
                    text.classList.toggle("completed", e.target.checked);
                }
            });

            // ========== HIỂN THỊ MODAL ==========
            createNewListItem.addEventListener("click", () => {
                newListModal.style.display = "flex";
                newListNameInput.focus();
            });

            closeModalBtn.addEventListener("click", () => {
                newListModal.style.display = "none";
            });

            window.addEventListener("click", (e) => {
                if (e.target === newListModal) {
                    newListModal.style.display = "none";
                }
            });

            // ========== TẠO DANH SÁCH MỚI ==========
            createListBtn.addEventListener("click", createNewList);

            function createNewList() {
                const listName = newListNameInput.value.trim();
                if (listName === "")
                    return;

                const li = document.createElement("li");
                li.dataset.listId = listName.toLowerCase().replace(/\s+/g, "-");
                li.innerHTML = `
            <i class="material-icons">list</i>
            <span>${listName}</span>
            <i class="material-icons delete-list">delete</i>
        `;

                sidebar.insertBefore(li, createNewListItem);
                newListModal.style.display = "none";
                newListNameInput.value = "";
            }

            // ========== XÓA DANH SÁCH ==========
            sidebar.addEventListener("click", (e) => {
                if (e.target.classList.contains("delete-list")) {
                    e.target.closest("li").remove();
                }

                if (e.target.closest("li") && !e.target.classList.contains("delete-list")) {
                    document.querySelectorAll(".task-lists li").forEach(li => li.classList.remove("active"));
                    e.target.closest("li").classList.add("active");

                    const selectedList = e.target.closest("li").querySelector("span").textContent;
                    document.querySelector(".header h1").textContent = selectedList;
                }
            });
        });

    </script>
</html>
