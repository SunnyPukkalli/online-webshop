<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container">

	<!-- Breadcrumb -->
	<div class="row">

		<div class="col-xs-12">

			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${contextRoot}/show/all/products">Products</a></li>
				<li class="breadcrumb-item" class="active">${product.name}</li>
			</ol>

		</div>

	</div>

	<!-- Display product -->
	<div class="row">

		<!-- Product Image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img src="${images}/${product.code}.jpg" class="img img-responsive" />
			</div>

		</div>

		<!-- Product Description -->
		<div class="col-xs-12 col-sm-8">

			<h3>${product.name}</h3>
			<hr />

			<p>${product.description}</p>
			<hr />

			<h4>
				Price : &#8377; <strong>${product.unitPrice} /-</strong>
			</h4>
			<hr />



			<c:choose>

				<c:when test="${product.quantity <1}">
					<h6>
						Qty. Availble : <span style="color: red"> Out Of Stock </span>
					</h6>
				</c:when>
				<c:otherwise>
					<h6>Qty. Availble : ${product.quantity}</h6>
				</c:otherwise>


			</c:choose>


			<sec:authorize access="hasAuthority('USER')">
				<c:choose>

					<c:when test="${product.quantity <1}">
						<a href="javascript:void(0)" class="btn btn-success disabled"><strike>
								<span class="glyphicon glyphicon-shopping-cart"></span> Add to
								Cart
						</strike> </a>
					</c:when>
					<c:otherwise>
						<a href="${contextRoot}/cart/add/${product.id}/product"
							class="btn btn-success"> <span
							class="glyphicon glyphicon-shopping-cart"></span> Add to Cart
						</a>
					</c:otherwise>


				</c:choose>
			</sec:authorize>

			<sec:authorize access="hasAuthority('ADMIN')">
				<a href="${contextRoot}/manage/${product.id}/product"
					class="btn btn-warning"> <span
					class="glyphicon glyphicon-pencil"></span> Edit
				</a>
			</sec:authorize>

			<a href="${contextRoot}/show/all/products" class="btn btn-success">
				Back </a>

		</div>
	</div>

</div>