<jsp>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewpoint" content="width=device-width, initial-scale=1.0"/>
		<title>Getting Started with BOOTSTRAP</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="./css/main.css">
	</head>
		<jsp:include page="./inc/header.jsp" />
	<body>
		<div class="container border_profile">
		<p align="right">
                <input type="button" value=" Edit " class="edit"/>
                </p>
               <form name="save"  id="update" action="/rest/updateProfile">
                		<p align="right">
                <input type="button" value=" Save " class="save"onclick="ajaxUpdateProfile();"/>
                </p>
                </form>
			<div style="margin-left:50px">
		<input type="hidden" name="_id" class="_id"/>
				<h4 style="font-family:courier"><b>Name:</b><b class="name"></b></h4>
				<h4 style="font-family:courier"><b>Designation:</b><b class="desg"></b></h4>
	            <img src="https://www.renault.co.uk/etc/designs/renault_v2/5.2.0-112/common-assets/img/avatar/avatar.png" alt= "place holder" width="154" height="200" align="left" class="profile_img"/>
			</div>
			<div style="margin-left:250px">
					  <legend>Contact Information</legend>
	  				  <table style="margin-top:-130px;">
		  				  <tr><td>First name:</td><td class="fname"></td></tr><br/>
						  <tr><td> Middle name:</td><td class="mname"></td></tr><br/>
						   <tr><td>Last name:</td><td class="lname"></td></tr><br/>
						  <tr><td> Email: </td><td class="email"></td></tr><br/>
						  <tr><td> Phone[Cell]:</td><td class="cellphone"></td></tr><br/>
						  <tr><td> Skype Id: </td><td class="skype_id"></td></tr><br/>
					  </table>  
					  							  		  					
			  		  <legend>General Information</legend>
			  		   <table  style="margin-top:-130px;">
						  <tr><td>Gender:</td><td class="gender"></td></tr><br/>
						  <tr><td>Date of birth: </td><td class="dob"></td></tr><br/>
					      <tr><td>Country: </td><td class="country"></td></tr><br/>
						  <tr><td>City: </td><td class="city"></td></tr><br/>
					      <tr><td>Hobbies / Interests: </td><td class="interest"></td></tr><br/>
						  <tr><td>Languages Known:</td><td class="language"></td></tr><br/>
					   </table>  
					   <legend>Educational Qualifications</legend>
					   <table  style="margin-top:-70px;">
					    	<tr><td>Highest Degree: </td><td class="high_degree"></td></tr><br/>
							<tr><td>College / University: </td><td class="College"></td></tr><br/>
							<tr><td>Other Courses or Certifications: </td><td class="Course"></td></tr><br/>
					   	</table>
					   	<legend>Experience Details</legend>
					   	<table  style="margin-top:-130px;">
							<tr><td>Date of joining:</td><td class="doj"></td></tr><br/>
							<tr><td>Skills acquired:</td><td class=skills></td></tr><br/>
	 						<tr><td>Current project: </td><td class="curr_project"></td></tr><br/>
		  					<tr><td>Roles & responsibilities: </td><td class="curr_role"></td></tr><br/>
				  			<tr><td>Previous project: </td><td class="pre_project"></td></tr><br/>
					  		<tr><td>Roles & responsibilities: </td><td class="pre_role"></td></tr><br/>
					    </table>					   	
					   	 	 
  			
			</div>
			
		</div>
		

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="./js/profile.js"></script>
	</body>
</html>	
</jsp>
