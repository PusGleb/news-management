<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>News Management</title>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="newsList.news_portal" var="news_portal" />
<fmt:message bundle="${loc}" key="newsList.en" var="en" />
<fmt:message bundle="${loc}" key="newsList.ru" var="ru" />
<fmt:message bundle="${loc}" key="newsList.filter" var="filter" />
<fmt:message bundle="${loc}" key="newsList.reset" var="reset" />
<fmt:message bundle="${loc}" key="newsList.comments" var="comments" />
<fmt:message bundle="${loc}" key="newsList.view" var="view" />
<fmt:message bundle="${loc}" key="newsList.empty" var="emptyList" />

<script src="<c:url value="/resources/js/jquery-1.6.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.8.13.custom.min.js" />"></script>
<script src="<c:url value="/resources/js/ui.dropdownchecklist.js" />"></script>

<link href="<c:url value="/resources/css/admin_news_list.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/sidebar.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/add_news.css" />" rel="stylesheet">
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

		<div class="sidebar">

			<div class="sidebar-ref"><a class="two" href="${pageContext.request.contextPath}/goToNewsList">News List</a></div>

			<c:choose>
                <c:when test="${not empty activeAddNews}">
 					<div class="active_sidebar-ref"><a class="two" >Add News</a></div>
                </c:when>
                <c:otherwise>
                    <div class="sidebar-ref"><a class="two" href="${pageContext.request.contextPath}/goToAddNews">Add News</a></div>
                </c:otherwise>
            </c:choose>

			<div class="sidebar-ref"><a class="two" href="${url}">Add/Update Authors</a></div>
			<div class="sidebar-ref"><a class="two" href="${url}">Add/Update Tags</a></div>

		</div>
		<div class="innerWrapper">

                <form  method="POST" action="${pageContext.request.contextPath}/addNews">
					<div class ="inputTitle">Title:</div>
					<input type="text" size ="100" name="login" value="" />

					<div class ="inputTitle">Date:</div>
					<input type="text" size ="30" name="login" value="" />

					<div class ="inputTitle">Brief:</div>
					<textarea name="comment" rows="5" cols="73"></textarea>

					<div class ="inputTitle">Content:</div>
					<textarea name="comment" rows="5" cols="73"></textarea>


					<div class="saveButtom">
						<input type="submit" name="send" value="save" />
					</div>
				</form>

        </div>

	</div>
	<div class="footer">Copyrihgt @Epam 2015. All rights reserved</div>
</body>

</html>