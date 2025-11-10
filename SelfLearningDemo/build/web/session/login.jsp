<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Đăng nhập - yourSelf Learning</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    </head>
    <body>
        <div class="login-container">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="Logo">
                <h2>yourSelf Learning</h2>
            </div>
                
            <% String error = (String) request.getAttribute("error");
           if (error != null) { %>
            <div class="error-message">
                <%= error %>
            </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/xulylogin" method="post" class="login-form">
                <label for="email">Email của bạn</label>
                <input type="email" id="email" name="email" placeholder="e.g. elon@tesla.com" required>

                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" placeholder="e.g. iloveyourself123" required>

                <button type="submit" class="btn-login">Đăng nhập</button>

                <div class="links">
                    <a href="register.jsp">Chưa có tài khoản?</a>
                    <a href="forgot.jsp">Quên mật khẩu?</a>
                </div>
            </form>

        </div>
    </body> 
</html>
