<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<div class="container">


	<c:if test="${not empty message}">	
		<div class="row">			
			<div class="col-xs-12 col-md-offset-2 col-md-8">			
				<div class="alert alert-info fade in">${message}</div>				
			</div>
		</div>
	</c:if>
	
	
	<div class="row">

		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Product Management</h4>
				</div>

				<div class="panel-body">

					<!--  FORM ELEMENTS  -->

					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">

						<div class="form-group">
							<label class="control-label col-md-4" for="name"> Enter
								Product Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="brand"> Enter
								Brand Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="description">
								Enter Product Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									placeholder="Product Description" class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">
								Enter Unit Price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice"
									placeholder="Unit Price in &#8377;" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">
								Quantity Available</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Quantity Available" class="form-control" />
							</div>
						</div>

						<!-- File Element for Image Upload -->
						<div class="form-group">
							<label class="control-label col-md-4" for="file"> Select
								a File to Upload</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">
								Category :</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />
									
									
									<c:if test="${product.id == 0 }">
										<div class="text-right">
											<br/>
											<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myCategoryModal"> Add Category </button>
										</div>
									</c:if>
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary" />

								<!-- hidden fields for Products -->
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />
								<sf:hidden path="active" />

							</div>
						</div>

					</sf:form>
				</div>

			</div>


		</div>

	</div>

	<div class="row">
	
		<div class="col-xs-12"></div>
			<h3>Available Products</h3>
			<hr/>
		<div class="col-xs-12">
		
			<div class="container-fluid">
				<div class="table-responsive">
					<!--  Products Table for Admin  -->
				<table id="adminProductsTable" class="table table-striped table-bordered">

					<thead>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</thead>

					<tfoot>
						<tr>
							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>
						</tr>
					</tfoot>

				</table>
				
				</div>			
			</div>

		</div>
	</div>


	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1" aria-labelledby="myModalLabel" > 
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			
				<!-- MODAL HEADER -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel" >Add New Category</h4>
				</div>
				
				<!-- MODAL BODY -->
				<div class="modal-body">
					
					<!-- CATEGORY FORM  -->
				
					<sf:form modelAttribute="category" action="${contextRoot}/manage/category"
					method="POST" class="form-horizontal" id="categoryForm">
					
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Category Name </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" class="form-control"/>
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Category Description </label>
							<div class="col-md-8">
								<sf:textarea rows="3"  path="description" id="category_description" class="form-control"/>
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category"  class="btn btn-primary"/>
							</div>
						</div>
					
					
					</sf:form>
			
				</div>
			
			</div>
		</div>
	</div>
	
</div>