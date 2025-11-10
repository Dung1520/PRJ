<%-- 
    Document   : admin
    Created on : Nov 1, 2025, 9:40:30 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h2>Nội dung trang Admin</h2>
        <h3>Welcome: <%= name %></h3>
        <div>
            <%@include file="../includes/footer.jsp" %>
        </div>
         <%
             }
         %>
    </body>
</html>
