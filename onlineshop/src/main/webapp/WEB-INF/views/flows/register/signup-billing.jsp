<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>


<div class="container">

	<div class="row">


		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Sign Up - Address</h4>
				</div>

				<div class="panel-body">

					<!--  FORM ELEMENTS  -->

					<sf:form class="form-horizontal" modelAttribute="billing"
						method="POST" id="billingForm">

						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineOne">
								Address Line One</label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineOne" id="name"
									placeholder="Address Line One" class="form-control" />
								<sf:errors path="addressLineOne" cssClass="help-block"
									element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineTwo">
								Address Line Two</label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineTwo" id="name"
									placeholder="Address Line One" class="form-control" />
								<sf:errors path="addressLineTwo" cssClass="help-block"
									element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="city"> City</label>
							<div class="col-md-8">
								<sf:input type="text" path="city" id="city" placeholder="Email"
									class="form-control" />
								<sf:errors path="city" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="state"> State</label>
							<div class="col-md-8">
								<sf:input type="text" path="state" id="state"
									placeholder="Contact Number" class="form-control" />
								<sf:errors path="state" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="country">
								Country</label>
							<div class="col-md-8">
								<sf:input type="text" path="country" id="country"
									placeholder="Contact Number" class="form-control" />
								<sf:errors path="country" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="postalCode">
								Postal Code </label>
							<div class="col-md-8">
								<sf:input type="text" path="postalCode" id="postalCode"
									placeholder="Contact Number" class="form-control" />
								<sf:errors path="postalCode" cssClass="help-block" element="em" />
							</div>
						</div>




						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">

								<button type="submit" class="btn btn-primary"
									name="_eventId_personal">
									<span class="glyphicon glyphicon-chevron-left"></span> Previous
									- Personal
								</button>

								<button type="submit" class="btn btn-primary"
									name="_eventId_confirm">
									Next - Confirm <span class="glyphicon glyphicon-chevron-right"></span>
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
