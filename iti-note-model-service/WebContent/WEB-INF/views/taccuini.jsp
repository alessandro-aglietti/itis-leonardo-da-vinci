<ul>
	<c:forEach items="${it.taccuini}" var="t">
		<li><a href="${pageContext.request.contextPath}/taccuino/${t.id}/note">${t.titolo}</a></li>
	</c:forEach>
</ul>