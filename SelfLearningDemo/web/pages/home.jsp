<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

<div class="home-main-content">
    <% if (role == null) { %>
    <!-- ===== KHÁCH CHƯA ĐĂNG NHẬP ===== -->
    <section class="home-intro-section">
        <h1 class="home-intro-title">Giới thiệu tính năng</h1>
        <div class="home-feature-container">
            <div class="home-feature-box">
                <div class="home-feature-front">Quiz</div>
                <div class="home-feature-back">Các bài kiểm tra</div>
            </div>

            <div class="home-feature-box">
                <div class="home-feature-front">Flashcard</div>
                <div class="home-feature-back">Ghi nhớ nhanh</div>
            </div>

            <div class="home-feature-box">
                <div class="home-feature-front">TodoList</div>
                <div class="home-feature-back">Việc cần làm</div>
            </div>
        </div>
    </section>

    <% } else { %>
    <!-- ===== QUIZ SECTION ===== -->
    <div class="feature-section">
        <h2 class="feature-title">Quiz</h2>
        <div class="feature-slider">
            <button class="slide-btn" onclick="slidePrev('quiz-list')">⟵</button>
            <div class="feature-list" id="quiz-list">
                <% for (int i = 1; i <= 10; i++) { %>
                    <div class="feature-card">Quiz <%= i %></div>
                <% } %>
            </div>
            <button class="slide-btn" onclick="slideNext('quiz-list')">⟶</button>
        </div>
    </div>

    <!-- ===== FLASHCARD SECTION ===== -->
    <div class="feature-section">
        <h2 class="feature-title">Flashcard</h2>
        <div class="feature-slider">
            <button class="slide-btn" onclick="slidePrev('flashcard-list')">⟵</button>
            <div class="feature-list" id="flashcard-list">
                <% for (int i = 1; i <= 10; i++) { %>
                    <div class="feature-card">Flashcard <%= i %></div>
                <% } %>
            </div>
            <button class="slide-btn" onclick="slideNext('flashcard-list')">⟶</button>
        </div>
    </div>

    <!-- ===== TODOLIST SECTION ===== -->
    <div class="feature-section">
        <h2 class="feature-title">TodoList</h2>
        <div class="feature-slider">
            <button class="slide-btn" onclick="slidePrev('todo-list')">⟵</button>
            <div class="feature-list" id="todo-list">
                <% for (int i = 1; i <= 10; i++) { %>
                    <div class="feature-card">Công việc <%= i %></div>
                <% } %>
            </div>
            <button class="slide-btn" onclick="slideNext('todo-list')">⟶</button>
        </div>
    </div>
</section>

<script>
    // Hàm trượt sang phải
    function slideNext(id) {
        const list = document.getElementById(id);
        const cardWidth = list.children[0].offsetWidth + 20; // card + gap
        list.scrollBy({ left: cardWidth * 5, behavior: 'smooth' });
    }

    // Hàm trượt sang trái
    function slidePrev(id) {
        const list = document.getElementById(id);
        const cardWidth = list.children[0].offsetWidth + 20;
        list.scrollBy({ left: -cardWidth * 5, behavior: 'smooth' });
    }
</script>
<% } %>
</div>
