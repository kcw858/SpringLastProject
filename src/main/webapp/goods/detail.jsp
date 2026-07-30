<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
		.row{
			margin:0px auto;
			width: 960px;
		}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<table class="table">
					<tr>
						<td width="30%" class="text-center" rowspan="6">
							<img src="${vo.goods_poster}" style="width: 290px;height: 250px;">
						</td>
						<td colspan="2">
							<h3>${vo.goods_name}</h3>
						</td>
					</tr>
					<tr>
						<td width="15%" style="color: gray">처음 가격</td>
						<td width="55%">${vo.goods_first_price}</td>
					</tr>
					<tr>
						<td width="15%" style="color: gray">할인율</td>
						<td width="55%">${vo.goods_discount}%</td>
					</tr>
					<tr>
						<td width="15%" style="color: gray">판매 가격</td>
						<td width="55%">${vo.goods_price}</td>
					</tr>
					<tr>
						<td width="15%" style="color: gray">배송</td>
						<td width="55%">${vo.goods_delivery}</td>
					</tr>
					<tr>
						<td width="15%" style="color: gray">상품 설명</td>
						<td width="55%">${vo.goods_sub}</td>
					</tr>
					<tr>
						<td colspan="3" class="text-right">
							<a href="../goods/main.do" class="btn btn-sm btn-danger">목록</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>