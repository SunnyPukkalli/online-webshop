$(function (){
	// solving the active menu problem
	switch(menu){
	
	case 'About Us' :
		$('#about').addClass('active');
		break;
	case 'Contact Us' :
		$('#contact').addClass('active');
		break;
	case 'All Products' :
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products' :
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart' :
		$('#userCart').addClass('active');
		break;
	default:
		if(menu == "Home") break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}
	
	// to tackle CSRF Token
	
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length>0 && header.length>0){	
		// set token header to AJAX request
		$(document).ajaxSend(function(e , xhr , options){
			xhr.setRequestHeader(header,token);			
		});		
	}
	
	
	// code for jQuery Data Table	
	// get the table
	var $table =  $('#productListTable');
	
	
	//execute the below code only where we have this table
	if($table.length){
		//console.log('inside a table');
		
		var jsonUrl = '';
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot + '/json/data/all/products' ;
		}else{
			jsonUrl = window.contextRoot + '/json/data/category/'+window.categoryId+'/products' ;
		}
		
		
		
		$table.DataTable({
			
			lengthMenu : [ [2,3,5,-1] , ['2 Records','3 Records','5 Records','All'] ], 
			//	1st array -- how many records to display 2nd Array -- String to display
			//	data : products
			
			ajax : {
				url : jsonUrl,
				dataSrc: ''
			},
			columns:[
						{
							data : 'code',
							bSortable : false,
							mRender : function (data, type, row){
								
								return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="datatableCSS"/>';
							}
						},
						{
							data : 'name'
						},
						{
							data : 'brand'
						},
						{
							data : 'unitPrice',
							mRender : function(data,type,row){
								return '&#8377; '+data;
							}
						},
						{
							data : 'quantity',
							mRender : function(data, type, row){
								
								if(data <1 ){
									return '<span style="color : red"> Out Of Stock </span>';
								}
								return data;
							}
						},
						{
							data : 'id',
							bSortable : false,
							mRender : function(data, type, row){
								
								var str ='';
								str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"> <span class="glyphicon glyphicon-eye-open" ></span> </a> &#160;';
								
								
								if( userRole == 'ADMIN'){
									str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"> <span class="glyphicon glyphicon-pencil" ></span>  </a>';
								}else{
									if(row.quantity <1 ){
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"> <span class="glyphicon glyphicon-shopping-cart" ></span>  </a>';
									}else{
											str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"> <span class="glyphicon glyphicon-shopping-cart" ></span>  </a>';
									}
								}
								return str ;
							}
						}
				
					]
			
			
			
		});
	}
	
	// dismissing the alert after 3 seconds
	var $alert = $('.alert');
	
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000);
	}
	
	//--------------------------------------------
	

	
	
	//-----------------------------------------
	// DATA TABLE FOR ADMIN
	
	// get the table
	
	var $adminProductsTable =  $('#adminProductsTable');
	
	
	//execute the below code only where we have this table
	if($adminProductsTable.length){
		//console.log('inside a table');
		
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products' ;
		
		
		$adminProductsTable.DataTable({

			// 1st array -- how many records to display 2nd Array -- String to display
			lengthMenu : [ [10,20,50,-1] , ['10 Records','20 Records','50 Records','All'] ], 
			pageLength : 30,
			
			//	data : products
			
			ajax : {
				url : jsonUrl,
				dataSrc: ''
			},
			columns:[
						{
							data : 'id'
						},
						{
							data : 'code',
							bSortable : false,
							mRender : function (data, type, row){
								
								return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"/>';
							}
						},
						{
							data : 'name'
						},
						{
							data : 'brand'
						},
						{
							data : 'quantity',
							mRender : function(data, type, row){
								
								if(data <1 ){
									return '<span style="color : red"> Out Of Stock </span>';
								}
								return data;
							}
						},
						{
							data : 'unitPrice',
							mRender : function(data,type,row){
								return '&#8377; '+data;
							}
						},
						{
							data : 'active',
							bSortable : false,
							mRender : function(data, type, row){
								
								var str ='';
								str += 	'<label class="switch">';
								if(data){
									str += 	'<input type="checkbox" checked="checked" value="'+row.id+'"/>';
								}
								else{
									str += 	'<input type="checkbox" value="'+row.id+'"/>';
								}
								str += 	'<div class="slider"></div></label>';
								
								return str;
							}
							
						},
						{
							data : 'id',
							bSortable : false,
							mRender : function(data, type,row){
								var str ='';
								str+= '<a href="'+window.contextRoot+'/manage/'+row.id+'/product" class="btn btn-warning" >';
								str+= '<span class="glyphicon glyphicon-pencil"></span></a>';

								return str;
							}
						}
				
					],
				// For Toggle to work ,after dataTable is loaded 
				initComplete : function() {
					var api = this.api();

					api.$('.switch input[type="checkbox"]').on('change',function(){
						
						var checkbox = $(this);
						var checked = checkbox.prop('checked');
						var dMsg = (checked)? 'You want to activate the product?':'You want to deactivate the product?';
						var value = checkbox.prop('value');
						
						bootbox.confirm({
							size : 'medium',
							title: 'Product Activation & Deactivation',
							message:dMsg,
							callback: function(confirmed){
								if(confirmed){
									
									console.log(value);
									
									var activationUrl = window.contextRoot+'/manage/product/' +value + '/activation';
									$.post(activationUrl, function(data){
										
										bootbox.alert({
											size : 'medium',
											title: 'Information',
											message:data
										});
										
									});
									
									
								}else{
									checkbox.prop('checked',!checked);
								}
							}
							
						});
						
					});
					
				}
			
			
			
		});
	}
	//-----------------------------------------
	
	
	// Validation Code for Category Form 
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		
		$categoryForm.validate({
			
			rules : {
				
				name : {
					required : true,
					minlength : 2
				},
				description : {
					required : true
				}
		
			},
			
			messages:  {
				
				name : {
					required: 'Please enter a Category Name',
					minlength: ' Minimum Length of Category Name must be more than 2 characters'
				},
				description : {
					reuired : 'Please enter a Category Description '
				}
			},
			errorElement : 'em',
			errorPlacement : function(error , element ){
				// add the class of help block
				error.addClass('help-block');
				// add the error element after input element
				error.insertAfter(element);
			}
			
		});
		
		
	}
	// -------------------------------------------------------------------------
	
	
	
	
	
	// Validation Code for Login  Form 
	var $loginForm = $('#loginForm');
	
	if($loginForm.length){
		
		$loginForm.validate({
			
			rules : {
				
				username : {
					required : true,
					email : true
				},
				password : {
					required : true
				}
		
			},
			
			messages:  {
				
				username : {
					required: 'Please enter a valid User Name',
					email: ' Please enter a valid Email '
				},
				password : {
					required : 'Please enter a valid password'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error , element ){
				// add the class of help block
				error.addClass('help-block');
				// add the error element after input element
				error.insertAfter(element);
			}
			
		});
		
	}
	
	//--------------------------------------------
	// handling click event of refresh cart button
	
	$('button[name="refreshCart"]').click(function(){
		
		// fetch the cart line id
		var cartLineId =  $(this).attr('value');
		var countElement = $('#count_' +cartLineId);
		
		var originalCount = countElement.attr('value');
		
		var currentCount = countElement.val();
		
		// work only when the count has changed
		
		if(currentCount !== originalCount){
			
			if(currentCount < 1 || currentCount > 3){
				// reverting back to original count
				countElement.val(originalCount);
				bootbox.alert({
					size : 'medium',
					title: 'Error',
					message : 'Product count should be minimum 1 and maximum 3'	
				});
			}else{
				
				var updateUrl = window.contextRoot +'/cart/'+ cartLineId +'/update?count='+currentCount ;
				console.log(updateUrl);
				// forward it to controller
				window.location.href = updateUrl;
			}
			
		}
		
	});
	
	//--------------------------------------------


});