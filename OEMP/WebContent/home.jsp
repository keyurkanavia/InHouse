<jsp>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewpoint" content="width=device-width, initial-scale=1.0"/>
		<title>Getting Started with BOOTSTRAP</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" href="./css/main.css">
	</head>
		<%@ include file="./inc/header.jsp" %>
	<body>
			<div class="container" style="width:800px">
				<div class="profile border"   align="center">
					<div class="profile_update" align="center"><h4>PROFILE UPDATES</h3><hr/></div>
					<div class="more">
 						<blockquote class="blockquote-reverse">
  				 	 		<footer class="profile_more"><a href="#">View More</a></footer></div>
  						</blockquote>
					<div class="less">
					<blockquote class='blockquote-reverse'><footer class='profile_less'><a href='#'>Less</a></footer></blockquote></div>
				</div>
			</div>		
	<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="./js/home.js"></script>
	</body>
</html>
</jsp>