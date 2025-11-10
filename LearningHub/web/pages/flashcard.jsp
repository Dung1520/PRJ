<%-- 
    Document   : flashcard
    Created on : Nov 1, 2025, 9:39:15 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/flashcard.css">
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
        <div class="flashcard-main">
            <div class="flashcard-header">
                <div class="flashcard-icon">🎴</div>
                <h1>Flashcard</h1>
            </div>

            <!-- Thanh tìm kiếm -->
            <div class="flashcard-search">
                <button class="search-btn"><i class="fa fa-search"></i> Tìm kiếm</button>
                <input type="text" placeholder="Tìm thẻ theo tên, môn học...">
            </div>

            <!-- Thanh danh mục -->
            <section class="flashcard-category">
                <ul>
                    <li class="active">Tất cả</li>
                    <li>Tiếng Anh</li>
                    <li>Nhật</li>
                    <li>Hán ngữ</li>
                    <li>Toán</li>
                    <li>Lý</li>
                    <li>Hóa</li>
                    <li>Sinh</li>
                </ul>
            </section>

            <!-- Nút tạo flashcard -->
            <div class="create-flashcard-container">
                <button id="openModalBtn" class="create-flashcard-btn">
                    <i class="fa fa-plus"></i> Tạo flashcard mới
                </button>
            </div>

            <!-- Danh sách Flashcard -->
            <section class="flashcard-list" id="flashcardList">
                <% 
                    String[] subjects = {"Anh", "Nhật", "Trung", "Toán", "Lý", "Hóa"};
                    for (int i = 1; i <= 50; i++) { 
                %>
                <div class="flashcard-card">
                    <h3>Flashcard <%= i %></h3>
                    <p>Ôn tập hiệu quả với nội dung chọn lọc.</p>
                    <p class="flashcard-meta">Môn: <%= subjects[i % subjects.length] %></p>
                    <button class="detail-btn">Chi tiết</button>
                </div>
                <% } %>
            </section>

            <!-- Phân trang -->
            <div class="pagination">
                <button id="prevPage" class="page-btn">&lt;</button>
                <div id="pageNumbers"></div>
                <button id="nextPage" class="page-btn">&gt;</button>
            </div>

        </div>
        <!-- Modal tạo flashcard -->
        <div id="flashcardModal" class="modal">
            <div class="modal-content">
                <h3>Tạo Flashcard mới</h3>
                <input type="text" id="flashcardName" placeholder="Tên flashcard...">
                <textarea id="flashcardContent" placeholder="Nội dung..."></textarea>
                <div class="modal-actions">
                    <button id="saveFlashcard">Lưu</button>
                    <button id="closeModalBtn">Hủy</button>
                </div>
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
    // ======= CATEGORY HIGHLIGHT =======
document.querySelectorAll('.flashcard-category li').forEach(item => {
    item.addEventListener('click', () => {
        document.querySelectorAll('.flashcard-category li').forEach(li => li.classList.remove('active'));
        item.classList.add('active');
    });
});

// ======= PAGINATION =======
const cards = document.querySelectorAll('.flashcard-card');
const perPage = 16;
let currentPage = 1;
const totalPages = Math.ceil(cards.length / perPage);
const pageNumbersDiv = document.getElementById("pageNumbers");

function showPage(page) {
    cards.forEach((card, index) => {
        card.style.display = (index >= (page - 1) * perPage && index < page * perPage) ? "block" : "none";
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
