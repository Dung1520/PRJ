<%-- 
    Document   : welcome
    Created on : Nov 11, 2025, 9:56:56 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    </head>
    <body>
            
    <!-- ===== KHÁCH CHƯA ĐĂNG NHẬP ===== -->
    <section class="home-intro-section">

        <!-- Giới thiệu -->
        <div class="intro-container">
            <!-- Video nền -->
            <video autoplay muted loop playsinline class="background-video">
                <source src="${pageContext.request.contextPath}/video/video-home.mp4" type="video/mp4">
                <!-- fallback -->
                Your browser does not support the video tag.
            </video>

            <!-- Nội dung phía trên -->
            <div class="intro-content">
                <h1 class="intro-title">Master Any Topic with yourSelf Learning</h1>
                <p class="intro-text">
                    Create custom learning paths that adapt to your pace. Transform your knowledge with intelligent, adaptive education.
                </p>
                <a href="${pageContext.request.contextPath}/session/login.jsp" class="cta-button">Start Your Journey ></a>
            </div>
        </div>

        <!-- Tại sao chọn -->
        <div class="why-section">
            <h1 class="section-title">Why Choose yourSelf Learning?</h1>
            <p class="section-subtitle">
                Discover the future of education with our cutting-edge learning platform designed to transform how you acquire and master new skills.
            </p>

            <div class="features-grid">
                <div class="feature-box">
                    <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    <h3>Adaptive Personalization</h3>
                    <p>The platform adapts to your style and pace so content lands and sticks.</p>
                </div>

                <div class="feature-box">
                    <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    <h3>Goal-Oriented Learning</h3>
                    <p>Set clear objectives and track your progress with measurable milestones and achievements.</p>
                </div>

                <div class="feature-box">
                    <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    <h3>Accelerated Progress</h3>
                    <p>Advance efficiently with optimized sequencing and spaced repetition techniques.</p>
                </div>

                <div class="feature-box">
                    <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    <h3>Community Support</h3>
                    <p>Connect with peers, mentors, and experts in your field for collaborative learning.</p>
                </div>

                <div class="feature-box">
                    <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    <h3>Progress Analytics</h3>
                    <p>Detailed insights into your learning patterns help you optimize your study sessions.</p>
                </div>

                <div class="feature-box">
                    <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    <h3>Certified Learning</h3>
                    <p>Earn verifiable certificates and badges to showcase your achievements.</p>
                </div>
            </div>
        </div>

        <!-- Cách hoạt động -->
        <div class="how-section">
            <h1 class="section-title">How It Works</h1>
            <p class="section-subtitle">Get started in minutes with our intuitive three-step process</p>

            <div class="steps-container">
                <div class="step-box">
                    <div class="step-text">
                        <h3>Choose Your Topic</h3>
                        <p>Tell us what you want to learn — from programming languages to business strategies, any subject you're passionate about.</p>
                    </div>
                    <div class="step-img">
                        <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    </div>
                </div>

                <div class="step-box">
                    <div class="step-text">
                        <h3>Set Your Goals</h3>
                        <p>Define milestones and outcomes you want to achieve, and our system will guide you step by step.</p>
                    </div>
                    <div class="step-img">
                        <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    </div>
                </div>

                <div class="step-box">
                    <div class="step-text">
                        <h3>Track and Grow</h3>
                        <p>Measure your progress, earn badges, and refine your learning plan for continuous improvement.</p>
                    </div>
                    <div class="step-img">
                        <img src="${pageContext.request.contextPath}/image/anh.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>

        <div class="end-container">
            <div class="end-content">
                <h1 class="end-title">Master Any Topic with yourSelf Learning</h1>
                <p class="end-text">
                    Create custom learning paths that adapt to your pace. Transform your knowledge with intelligent, adaptive education.
                </p>
                <a href="${pageContext.request.contextPath}/session/login.jsp" class="cta-button">Start Your Journey ></a>
            </div>
        </div>

    </section>

    
    </body>
</html>
