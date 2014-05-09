<?xml version="1.0" encoding="utf-8"?>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pjax.js"></script>
		<script type="text/javascript">
			$(function(){
				$(document).pjax('a', '#pjax-container');
			});
		</script>
	</head>
	<body>
		<ul>
			<c:forEach items="${it.taccuini}" var="t">
				<li><a href="${pageContext.request.contextPath}/taccuino/${t.id}/note">${t.titolo}</a></li>
			</c:forEach>
		</ul>
		<div id="pjax-container">
			<%@include file="note.jsp" %>
		</div>
	</body>
</html>