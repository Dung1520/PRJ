<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">

<header class="header-main">
    <div class="header-left">
        <h1 class="header-logo">Studying is not Studying</h1>
    </div>

    <div class="header-center" id="deadlineNotice">
        <% 
            java.util.Date today = new java.util.Date();
            java.util.Date deadlineDate = (java.util.Date) request.getAttribute("deadlineDate");
            if (deadlineDate != null) {
                long diff = (deadlineDate.getTime() - today.getTime()) / (1000 * 60 * 60 * 24);
                if (diff > 0) {
        %>
                    <p class="header-deadline-alert">Còn <%=diff%> ngày nữa là đến kỳ thi FE!</p>
        <%      } 
            } 
        %>
        <p class="header-deadline-alert">Còn 1 ngày nữa là đến kỳ thi FE!</p>
    </div>

    <div class="header-user">
        <%
            String role = (String) session.getAttribute("role");
            if (role == null) {
        %>
            <a href="${pageContext.request.contextPath}/session/login.jsp" class="btn-signin">Đăng nhập</a>
            <a href="${pageContext.request.contextPath}/session/register.jsp" class="btn-signup">Đăng ký</a>
        <%
            } else {
        %>
            <a href="${pageContext.request.contextPath}/profile.jsp" class="user-name">Tên người dùng</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn-logout">Đăng xuất</a>
        <%
            }
        %>
    </div>
</header>
