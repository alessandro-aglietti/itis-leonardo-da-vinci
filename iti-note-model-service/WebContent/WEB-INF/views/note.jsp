<c:if test="${empty it.note}">
	<b>Questo taccuino non ha note</b>
</c:if>

<c:forEach items="${it.note}" var="n">
	<p><b>${n.id}:</b> ${n.testo}</p>
</c:forEach>