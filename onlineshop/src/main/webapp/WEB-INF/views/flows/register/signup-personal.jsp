<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>


<div class="container">

	<div class="row">


		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Sign Up - Personal</h4>
				</div>

				<div class="panel-body">

					<!--  FORM ELEMENTS  -->

					<sf:form class="form-horizontal" modelAttribute="user" method="POST" id="registerForm">

						<div class="form-group">
							<label class="control-label col-md-4" for="firstName">
								First Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="firstName" id="name"
									placeholder="First Name" class="form-control" />
								<sf:errors path="firstName" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="lastName">
								Last Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="lastName" id="name"
									placeholder="last Name" class="form-control" />
								<sf:errors path="lastName" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="email">
								Email</label>
							<div class="col-md-8">
								<sf:input type="text" path="email" id="email"
									placeholder="Email" class="form-control" />
								<sf:errors path="email" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="contactNumber">
								Contact Number</label>
							<div class="col-md-8">
								<sf:input type="text" path="contactNumber" id="contactNumber"
									placeholder="Contact Number" class="form-control" />
								<sf:errors path="contactNumber" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="password">
								Password</label>
							<div class="col-md-8">
								<sf:input type="password" path="password" id="password"
									placeholder="Password" class="form-control" />
								<sf:errors path="password" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="confirmPassword">
								Confirm Password : </label>
							<div class="col-md-8">
								<sf:input type="password" path="confirmPassword" id="confirmPassword"
									placeholder="Confirm Password" class="form-control" />
								<sf:errors path="confirmPassword" cssClass="help-block" element="em" />
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="control-label col-md-4" >Select Role </label>
							<div class="col-md-8">
								<label class="radio-inline">User
									<sf:radiobutton path="role" value="USER" checked="checked"/>
								</label>
								
								<label class="radio-inline">Supplier
									<sf:radiobutton path="role" value="SUPPLIER"/>
								</label>
									
							</div>
						</div>
						

						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" class="btn btn-primary" name="_eventId_billing" >
									Next - Billing <span class="glyphicon glyphicon-chevron-right"></span>
								</button>
							</div>
						</div>

					</sf:form>
				</div>

			</div>


		</div>


	</div>


</div>

<%@include file="../shared/flows-footer.jsp"%>
