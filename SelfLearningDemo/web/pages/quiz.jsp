<%-- 
    Document   : quiz
    Created on : Nov 1, 2025, 9:39:03 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/quiz.css">
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
        <div class="quiz-main-content">
            <div class="quiz-header">
        <h2>Quiz của bạn</h2>
    </div>

    <!-- Thanh danh mục -->
    <section class="quiz-category-list">
        <ul>
            <li class="active">Tất cả</li>
            <li>Toán</li>
            <li>Lý</li>
            <li>Hóa</li>
            <li>Sinh</li>
            <li>Sử</li>
            <li>Địa</li>
            <li>Văn</li>
            <li>Anh</li>
            <li>N1</li>
            <li>N2</li>
            <li>N3</li>
            <li>N4</li>
            <li>N5</li>
            <li>HSK1</li>
            <li>HSK2</li>
            <li>HSK3</li>
            <li>HSK4</li>
            <li>HSK5</li>
            <li>IELTS</li>
            <li>SAT</li>
        </ul>
    </section>
    
    <div class="quiz-search">
            <button class="search-btn">
                <i class="fa fa-search"></i> Tìm kiếm
            </button>
            <input type="text" placeholder="Tìm đề theo tên, môn học...">
        </div>
    <% if ("admin".equals(role)) { %>
    <!-- Nút tạo quiz -->
            <div class="create-quiz-container">
                <button id="openModalBtn" class="create-quiz-btn">
                    <i class="fa fa-plus"></i> Tạo flashcard mới
                </button>
            </div>
    <% } %>

    <!-- Danh sách Quiz -->
        <section class="quiz-list" id="quizList">
            <% 
                String[] cats = {"Toán", "Lý", "Hóa", "Sinh", "Anh", "Văn", "IELTS", "N1"};
                for (int i = 1; i <= 50; i++) { 
            %>
                <div class="quiz-card" data-index="<%= i %>">
                    <h3 class="quiz-title">Quiz <%= i %></h3>
                    <p class="quiz-desc">Luyện tập hiệu quả với các câu hỏi chọn lọc.</p>
                    <p class="quiz-meta">Thời gian: <%= 10 + (i % 3) * 5 %> phút · Số câu: <%= 10 + (i % 5) %> câu</p>
                    <p class="quiz-category">Môn: <%= cats[i % cats.length] %></p>
                    <button class="quiz-btn">Chi tiết</button>
                </div>
            <% } %>
        </section>

        <!-- Thanh phân trang -->
        <div class="pagination">
            <button id="prevPage" class="page-btn">&lt;</button>
            <div id="pageNumbers"></div>
            <button id="nextPage" class="page-btn">&gt;</button>
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
    document.querySelectorAll('.quiz-category-list li').forEach(item => {
        item.addEventListener('click', () => {
            document.querySelectorAll('.quiz-category-list li').forEach(li => li.classList.remove('active'));
            item.classList.add('active');
        });
    });
</script>

<script>
// === CATEGORY HIGHLIGHT ===
document.querySelectorAll('.quiz-category-list li').forEach(item => {
    item.addEventListener('click', () => {
        document.querySelectorAll('.quiz-category-list li').forEach(li => li.classList.remove('active'));
        item.classList.add('active');
    });
});

// === PAGINATION LOGIC ===
const quizzes = document.querySelectorAll('.quiz-card');
const perPage = 16;
let currentPage = 1;
const totalPages = Math.ceil(quizzes.length / perPage);
const pageNumbersDiv = document.getElementById("pageNumbers");

function showPage(page) {
    quizzes.forEach((quiz, index) => {
        quiz.style.display = (index >= (page - 1) * perPage && index < page * perPage) ? "block" : "none";
    });
    updatePageNumbers();
}

function updatePageNumbers() {
    pageNumbersDiv.innerHTML = "";
    let start = Math.max(1, currentPage - 1);
    let end = Math.min(totalPages, start + 2);

    for (let i = start; i <= end; i++) {
        const btn = document.createElement("button");
        btn.textContent = i;
        btn.classList.add("page-number");
        if (i === currentPage) btn.classList.add("active");
        btn.addEventListener("click", () => {
            currentPage = i;
            showPage(currentPage);
        });
        pageNumbersDiv.appendChild(btn);
    }
}

document.getElementById("prevPage").addEventListener("click", () => {
    if (currentPage > 1) {
        currentPage--;
        showPage(currentPage);
    }
});

document.getElementById("nextPage").addEventListener("click", () => {
    if (currentPage < totalPages) {
        currentPage++;
        showPage(currentPage);
    }
});

showPage(currentPage);

// ======= MODAL POPUP =======
const modal = document.getElementById("flashcardModal");
const openBtn = document.getElementById("openModalBtn");
const closeBtn = document.getElementById("closeModalBtn");

openBtn.addEventListener("click", () => modal.style.display = "flex");
closeBtn.addEventListener("click", () => modal.style.display = "none");
window.addEventListener("click", e => { if (e.target === modal) modal.style.display = "none"; });

</script>

</html>
