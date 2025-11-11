<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">

<div class="home-main-content">
    <!-- ===== QUIZ SECTION ===== -->
    <section class="home-loggedin-section">
        <!-- Quiz Section -->
        <div class="feature-section quiz-section">
            <div class="section-header">
                <div class="feature-icon">📝</div>
                <h2 class="feature-title">Quiz Collection</h2>
            </div>
            <div class="feature-slider">
                <button class="slide-btn prev-btn" onclick="slidePrev('quiz-list')">‹</button>
                <div class="feature-list" id="quiz-list">
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">1</div>
                        <div class="card-title">Quiz 1</div>
                        <div class="card-description">Kiến thức cơ bản - Bắt đầu hành trình học tập</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">2</div>
                        <div class="card-title">Quiz 2</div>
                        <div class="card-description">Ôn tập nâng cao - Thử thách bản thân</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">3</div>
                        <div class="card-title">Quiz 3</div>
                        <div class="card-description">Thử thách khó - Vượt qua giới hạn</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">4</div>
                        <div class="card-title">Quiz 4</div>
                        <div class="card-description">Luyện tập tổng hợp - Củng cố kiến thức</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">5</div>
                        <div class="card-title">Quiz 5</div>
                        <div class="card-description">Đánh giá năng lực - Kiểm tra trình độ</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">6</div>
                        <div class="card-title">Quiz 6</div>
                        <div class="card-description">Kiểm tra định kỳ - Theo dõi tiến độ</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">7</div>
                        <div class="card-title">Quiz 7</div>
                        <div class="card-description">Ôn tập chuyên sâu - Nắm vững lý thuyết</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">8</div>
                        <div class="card-title">Quiz 8</div>
                        <div class="card-description">Thử thách cuối kỳ - Sprint về đích</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">9</div>
                        <div class="card-title">Quiz 9</div>
                        <div class="card-description">Đề thi mẫu - Luyện tập thực chiến</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">10</div>
                        <div class="card-title">Quiz 10</div>
                        <div class="card-description">Kiểm tra tổng kết - Hoàn thành xuất sắc</div>
                    </div>
                </div>
                <button class="slide-btn next-btn" onclick="slideNext('quiz-list')">›</button>
            </div>
        </div>

        <!-- Flashcard Section -->
        <div class="feature-section flashcard-section">
            <div class="section-header">
                <div class="feature-icon">🎴</div>
                <h2 class="feature-title">Flashcard Library</h2>
            </div>
            <div class="feature-slider">
                <button class="slide-btn prev-btn" onclick="slidePrev('flashcard-list')">‹</button>
                <div class="feature-list" id="flashcard-list">
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">1</div>
                        <div class="card-title">Flashcard 1</div>
                        <div class="card-description">Từ vựng cơ bản - Nền tảng vững chắc</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">2</div>
                        <div class="card-title">Flashcard 2</div>
                        <div class="card-description">Ngữ pháp quan trọng - Học hiệu quả</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">3</div>
                        <div class="card-title">Flashcard 3</div>
                        <div class="card-description">Khái niệm chính - Ghi nhớ lâu dài</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">4</div>
                        <div class="card-title">Flashcard 4</div>
                        <div class="card-description">Công thức toán học - Áp dụng thực tế</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">5</div>
                        <div class="card-title">Flashcard 5</div>
                        <div class="card-description">Định lý cơ bản - Tư duy logic</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">6</div>
                        <div class="card-title">Flashcard 6</div>
                        <div class="card-description">Ghi nhớ nhanh - Kỹ thuật học tập</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">7</div>
                        <div class="card-title">Flashcard 7</div>
                        <div class="card-description">Kiến thức nâng cao - Chuyên sâu hơn</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">8</div>
                        <div class="card-title">Flashcard 8</div>
                        <div class="card-description">Ôn tập tổng hợp - Hệ thống hóa</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">9</div>
                        <div class="card-title">Flashcard 9</div>
                        <div class="card-description">Mẹo học tập - Tối ưu thời gian</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">10</div>
                        <div class="card-title">Flashcard 10</div>
                        <div class="card-description">Tổng kết kiến thức - Master toàn bộ</div>
                    </div>
                </div>
                <button class="slide-btn next-btn" onclick="slideNext('flashcard-list')">›</button>
            </div>
        </div>

        <!-- TodoList Section -->
        <div class="feature-section todo-section">
            <div class="section-header">
                <div class="feature-icon">✓</div>
                <h2 class="feature-title">TodoList Manager</h2>
            </div>
            <div class="feature-slider">
                <button class="slide-btn prev-btn" onclick="slidePrev('todo-list')">‹</button>
                <div class="feature-list" id="todo-list">
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">1</div>
                        <div class="card-title">Công việc 1</div>
                        <div class="card-description">Hoàn thành bài tập - Deadline hôm nay</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">2</div>
                        <div class="card-title">Công việc 2</div>
                        <div class="card-description">Ôn tập bài cũ - Xem lại kiến thức</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">3</div>
                        <div class="card-title">Công việc 3</div>
                        <div class="card-description">Chuẩn bị bài mới - Đọc trước tài liệu</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">4</div>
                        <div class="card-title">Công việc 4</div>
                        <div class="card-description">Làm dự án nhóm - Họp team online</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">5</div>
                        <div class="card-title">Công việc 5</div>
                        <div class="card-description">Đọc tài liệu tham khảo - Mở rộng hiểu biết</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">6</div>
                        <div class="card-title">Công việc 6</div>
                        <div class="card-description">Viết báo cáo - Tổng hợp nghiên cứu</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">7</div>
                        <div class="card-title">Công việc 7</div>
                        <div class="card-description">Thuyết trình nhóm - Chuẩn bị slides</div>
                    </div>
                    <div class="feature-card">
                        <div class="shimmer"></div>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <span class="particle"></span>
                        <div class="card-number">8</div>
                        <div class="card-title">Công việc 8</div>
                        <div class="card-description">Kiểm tra giữa kỳ - Ôn tập kỹ lưỡng</div>
                    </div>
                    <div class="feature-card">
                    </div>
                </div>
                <button class="slide-btn next-btn" onclick="slideNext('todo-list')">›</button>
            </div>
        </div>
    </section>

    <script>
        // Hàm trượt sang phải
        function slideNext(id) {
            const list = document.getElementById(id);
            const cardWidth = list.children[0].offsetWidth + 20; // card + gap
            list.scrollBy({left: cardWidth * 5, behavior: 'smooth'});
        }

        // Hàm trượt sang trái
        function slidePrev(id) {
            const list = document.getElementById(id);
            const cardWidth = list.children[0].offsetWidth + 20;
            list.scrollBy({left: -cardWidth * 5, behavior: 'smooth'});
        }
    </script>
</div>
