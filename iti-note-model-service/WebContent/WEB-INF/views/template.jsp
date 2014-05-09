<?xml version="1.0" encoding="utf-8"?>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- start i18n setup -->
<fmt:setBundle basename="i18n.i18n" var="i18n"/>
<!-- end i18n setup -->

<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="template.title" bundle="${i18n}" /></title>
	</head>
	<body>
		<h1>${it.t.title}</h1>
		<h3><fmt:message key="template.msg" bundle="${i18n}" /></h3>
		<p>${it.t.subTitle}</p>
		<h4><fmt:formatDate value="${it.t.now}" type="date" dateStyle="long" /></h4>
		<hr />
		<a href="${it.path}/template/vm">Hello Velocity!</a>
		<br />
		<br />
		<a href="${it.path}/template/mu">Hello Mustache!</a>
	</body>
</html>