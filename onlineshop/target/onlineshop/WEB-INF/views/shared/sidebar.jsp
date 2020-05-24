<p class="lead">Shop Name</p>


<div class="list-group">

	<c:forEach items="${categories}" var="category">
		<a href="${contextRoot}/show/category/${category.id}/products"
			id="a_${category.name}" class="list-group-item">${category.name}</a>
	</c:forEach>

</div>