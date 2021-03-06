<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="news" scope="request" class="com.epam.entity.News" />
<html>
<head>
<title>News Management</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/newsList.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/newsView.css">
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="newsList.news_portal" var="news_portal" />
<fmt:message bundle="${loc}" key="newsList.en" var="en" />
<fmt:message bundle="${loc}" key="newsList.ru" var="ru" />
<fmt:message bundle="${loc}" key="newsView.back" var="back" />
<fmt:message bundle="${loc}" key="newsView.previous" var="previous" />
<fmt:message bundle="${loc}" key="newsView.next" var="next" />
<fmt:message bundle="${loc}" key="newsView.post_comment" var="post_comment" />
</head>
<body>
	<div class="headerWrapper">
		<div class="title">
			<h1>${news_portal}</h1>
		</div>
		<div class="localeWrapper">
			<div class="locale">
				<c:url value="/Controller" var="url">
					<c:param name="locale" value="ru" />
					<c:param name="command" value="locale" />
				</c:url>
				<a href="${url}">${ru}</a>
			</div>
			<div class="locale">
				<c:url value="/Controller" var="url">
					<c:param name="locale" value="en" />
					<c:param name="command" value="locale" />
				</c:url>
				<a href="${url}">${en}</a>
			</div>
		</div>
	</div>
	<div class="bodyWrapper">
		<div class="innerWrapper">
			<c:url value="/Controller" var="url">
				<c:param name="page" value="${currentPage}" />
				<c:param name="command" value="newsList" />
			</c:url>
			<div class="back">
				<a href="${url}">${back}</a>
			</div>

			<div class="viewNewTitle">${news.title}</div>
			<div class="author">(by ${news.author.name})</div>
			<div class="data">
				<fmt:formatDate value="${news.creationData}" pattern="yyyy/MM/dd" />
			</div>
			<div class="fullText">${news.fullText}</div>
			<c:forEach var="comment" items="${news.comments}">
				<div class="commentData">
					<fmt:formatDate value="${comment.creationDate}" pattern="yyyy/MM/dd" />
				</div>
				<div class="commentText">${comment.commentText}</div>
			</c:forEach>
			<div class="commentForm">
				<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/Controller">
					<input type="hidden" name="command" value="addComment" />
					<input type="hidden" name="newsId" value="${news.id}" />
					<textarea name="comment" rows="5" cols="73"></textarea>
					<div class="submitWrapper">
						<input type="submit" name="send" value="${post_comment}" />
					</div>
				</form>
			</div>
			<c:if test="${currentIndex > 0}">
				<c:url value="/Controller" var="url">
					<c:param name="currentIndex" value="${currentIndex - 1}" />
					<c:param name="command" value="goToNextOrPreviousNews" />
				</c:url>
				<div class="previous">
					<a href="${url}">${previous}</a>
				</div>
			</c:if>

			<c:if test="${currentIndex < sizeOfAllNews}">
				<c:url value="/Controller" var="url">
					<c:param name="currentIndex" value="${currentIndex + 1}" />
					<c:param name="command" value="goToNextOrPreviousNews" />
				</c:url>
				<div class="next">
					<a href="${url}">${next}</a>
				</div>
			</c:if>
		</div>
	</div>
	<div class="footer">Copyrihgt @Epam 2015. All rights reserved</div>
</body>
</html>
