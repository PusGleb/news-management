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

			<c:choose>
                <c:when test="${not empty activeNewsList}">
 					<div class="active_sidebar-ref"><a class="two">News List</a></div>
                </c:when>
                <c:otherwise>
                    <div class="sidebar-ref"><a class="two" href="${pageContext.request.contextPath}/goToNewsList">News List</a></div>
                </c:otherwise>
            </c:choose>

			<div class="sidebar-ref"><a class="two" href="${pageContext.request.contextPath}/goToAddNews">Add News</a></div>
			<div class="sidebar-ref"><a class="two" href="${url}">Add/Update Authors</a></div>
			<div class="sidebar-ref"><a class="two" href="${url}">Add/Update Tags</a></div>

		</div>
		<div class="innerWrapper">
			<div class="formWrapper">
				<form id="filter-form" action="${pageContext.request.contextPath}/filterNews" method="post">
					<select name="filterByAuthor">
						<option disabled selected>Select author</option>
						<c:if test="${not empty searchCriteria.author}">
							<option disabled selected>${searchCriteria.author.name}</option>
						</c:if>
						<c:forEach var="author" items="${allAuthors}">
							<option value="${author.id}">${author.name}</option>
						</c:forEach>
					</select>
					<select id="filterByTags" name="filterByTags" multiple="multiple">
						<c:if test="${empty searchCriteria.tags}">
							<option disabled selected>Select tags</option>
						</c:if>
						<c:if test="${not empty searchCriteria.tags}">
							<c:forEach var="tag" items="${searchCriteria.tags}">
								<option disabled selected>${tag.name}</option>
							</c:forEach>
						</c:if>
						<c:forEach var="tag" items="${allTags}">
							<option value="${tag.id}">${tag.name}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="command" value="filter" />
					<input type="submit" value="${filter}">
					<script type="text/javascript">
						$(document).ready(function() {
							$('#filterByTags').change(function() {
								console.log($(this).val());
							}).dropdownchecklist({
								width : 200
							})
						})
					</script>
				</form>
				<form action="${pageContext.request.contextPath}/resetFilter" method="post">
					<input type="submit" value="${reset}">
				</form>
			</div>

			<c:if test="${empty newsList}">
				<div class="newsTitle">${emptyList}</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/deleteNews" method="post">
				<c:forEach var="news" items="${newsList}">
					<div class="newsTitle">${news.title}</div>
					<div class="author">(by ${news.author.name})</div>
					<div class="data">
						<fmt:formatDate value="${news.creationData}" pattern="yyyy/MM/dd" />
					</div>
					<div class="shortText">${news.shortText}</div>

					<div class="checkbox">
						<input type="checkbox" name="newsIds" value="${news.id}">
					</div>

					<div class="view">
						<c:url value="/editNews" var="url">
							<c:param name="newsId" value="${news.id}" />
						</c:url>
						<a href="${url}">edit</a>
					</div>

					<c:set var="count" value="${news.comments}" />
					<div class="comments">${comments}(${fn:length(count)})</div>
					<c:forEach var="tag" items="${news.tags}">
						<div class="tags">${tag.name}</div>
					</c:forEach>
				</c:forEach>

				<div class="delete-buttom">
					<input type="submit" class="delete" value="DELETE">
				</div>
			</form>

			<div class="paginationWrapper">
				<div class="pagination-container">
					<ul class="pagination">
						<c:if test="${numberOfPages != 1}">
							<c:forEach begin="1" end="${numberOfPages}" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										<li><a class="active">${i}</a></li>
									</c:when>
									<c:otherwise>
										<c:url value="/newsList" var="url">
											<c:param name="page" value="${i}" />
										</c:url>
										<li><a href="${url}">${i}</a>
											</td></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">Copyrihgt @Epam 2015. All rights reserved</div>
</body>

</html>
