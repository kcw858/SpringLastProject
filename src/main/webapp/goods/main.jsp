<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<style type="text/css">
		.row{
			margin:0px auto;
			width: 960px;
		}
		p{
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
	</style>
	<body>
		<div class="container">
			<div class="row">
				<c:forEach var="vo" items="${list}">
				<div class="col-sm-3">
					<div class="thumbnail">
						<a href="../goods/detail_before.do?no=${vo.no}">
						<img src="${vo.goods_poster}" style="width: 250px;height: 130px;object-fit:fill">
						<p>${vo.goods_name }</p>
						</a>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="row text-center" style="margin-top: 10px">
				<ul class="pagination">
					<c:if test="${startPage > 1}">
						<li><a href="../goods/main.do?page=${startPage-1}">&laquo;</a></li>
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<li class="${curpage == i ? 'active':''}"><a href="../goods/main.do?page=${i}">${i}</a></li>
					</c:forEach>
					
					<c:if test="${endPage < totalpage}">
						<li><a href="../goods/main.do?page=${endPage+1}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
			<div class="row" style="margin-top: 10px">
				<h3>최근 방문 상품</h3>
				<hr>
				<c:if test="${size < 1}">
					<h3>방문한 상품페이지가 없습니다</h3>
				</c:if>
				<c:if test="${size > 0}">
					<c:forEach var="gvo" items="${gList}">
						<a href="../goods/detail.do?no=${gvo.no}">
						<div style="width: 100px;height: 100px; margin-left: 3px;display: inline;">
							<img src="${gvo.goods_poster}" style="width: 100px;height: 100px;">
						</div>
						</a>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</body>
</html>