<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<title>Hello Spring Blog</title>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

</head>
<body>
    <!-- Navigation -->
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('https://github.com/BlackrockDigital/startbootstrap-clean-blog/blob/gh-pages/img/home-bg.jpg?raw=true')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1>
                        	<c:out value="${post.title}" escapeXml="true"></c:out>
                        </h1>
                        <h2 class="subheading">
                        	<c:out value="${post.subtitle}" escapeXml="true"></c:out>
                        </h2>
                        <span class="meta">Posted by <a href="#">${post.name }</a> on ${post.regDate}</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    ${post.content}
                </div>
            </div>
            <div class="row">
            	<div class="pull-right">
					<a href="/post/${post.id }/edit">
            			<button type="button" class="btn btn-warning">Edit</button>
            		</a>
            		<a href="/post/${post.id }/delete">
            			<button type="button" class="btn btn-danger">Delete</button>
            		</a>
            	</div>
            </div>
        </div>
    </article>

    <hr>

    <!-- Footer -->
  <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
