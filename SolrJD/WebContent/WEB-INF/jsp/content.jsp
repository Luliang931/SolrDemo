<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="search.do" method="post">
			搜索：<input type="text" name="search" id="search">
			<input type="submit" value="搜索">
		</form>
		
		
		搜索的结果：
		<div>
			<table  border="1px" width="100%" bgcolor="#E9FBEB">
					<tr>
						<td>pid</td>
						<td>name</td>
						<td>catalog_name</td>
						<td>price</td>
						<td>description</td>
						<td>picture</td>
					</tr>
			
				<c:forEach items="${product}" var="p">
					<tr>
					
						<td>${p.pid }</td>
						<td>${p.name }</td>
						<td>${p.catalog_name }</td>
						<td>${p.price }</td>
						<td>${p.description }</td>
						<td>${p.picture }</td>
					</tr>
				</c:forEach>
			
			</table>

		</div>
		
		
</body>
</html>