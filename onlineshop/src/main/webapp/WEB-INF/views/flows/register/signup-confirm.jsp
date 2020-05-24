<%@include file="../shared/flows-header.jsp"%>


<div class="container">

	<div class="row">
		<!-- Column to display personal details -->
		<div class="col-sm-6">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Personal Details</h4>
				</div>

				<div class="panel-body">
					<!-- Code to display Panel  Body -->
					<div class="text-center">
						<h4>${registerModel.user.firstName} ${registerModel.user.lastName}</h4>
						<h5>Email : ${registerModel.user.email}</h5>
						<h5>Contact Number : ${registerModel.user.contactNumber}</h5>
						<h5>Role : ${registerModel.user.role}</h5>
					
					</div>

				</div>

				<div class="panel-footer">
					<!-- Anchor to move to edit Personal Details -->
					<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary"> Edit </a>
				</div>

			</div>
		</div>


		<!-- Column to display billing  Address -->
		<div class="col-sm-6">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Billing Address</h4>
				</div>

				<div class="panel-body">
					<!-- Code to display Panel  Body -->
					<div class="text-center">
						<h4>Address : ${registerModel.billing.addressLineOne} ${registerModel.billing.addressLineTwo}</h4>
						<h5>City : ${registerModel.billing.city}</h5>
						<h5>State : ${registerModel.billing.state}</h5>
						<h5>Country : ${registerModel.billing.country}</h5>
					
					</div>

				</div>

				<div class="panel-footer">
					<!-- Anchor to move to edit Address Details -->
					<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary"> Edit </a>
				</div>

			</div>
		</div>

	</div>
	
	<!--  To provide the confirm button after displaying the details -->
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4">
			<div class="text-center">
				<!-- Anchor to move to success Page -->	
				<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-primary"> Confirm </a>
			</div>
		
		</div>
	
	</div>

</div>

<%@include file="../shared/flows-footer.jsp"%>
