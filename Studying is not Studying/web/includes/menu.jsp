<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">

<nav class="menu-nav">
    <ul class="menu-list">
        <li class="menu-item"><a href="${pageContext.request.contextPath}/index.jsp">🏠</a></li>
        <li class="menu-item"><a href="${pageContext.request.contextPath}/pages/quiz.jsp">📄</a></li>
        <li class="menu-item"><a href="${pageContext.request.contextPath}/pages/flashcard.jsp">⚡</a></li>
        <li class="menu-item"><a href="${pageContext.request.contextPath}/pages/todolist.jsp">✏</a></li>

        <% if ("admin".equals(role)) { %>
            <li class="menu-item menu-item-admin"><a href="${pageContext.request.contextPath}/pages/admin.jsp">🗄</a></li>
        <% } %>
    </ul>
</nav>
