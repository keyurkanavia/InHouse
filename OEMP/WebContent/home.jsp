<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewpoint" content="width=device-width, initial-scale=1.0"/>
		<%@ include file="./inc/gl-html-head.jsp" %>
	</head>
		<%@ include file="./inc/header.jsp" %>
	<body>
			<div  id="contMid" class="container">
			<div id="mainArea">
				<div id="mainAreaItem" class="profile"   align="center">
					<div class="profile_update" align="center"><h4>PROFILE UPDATES</h3><hr/></div>
					<div class="more">
 						<blockquote class="blockquote-reverse">
  				 	 		<footer class="profile_more"><a href="#">View More</a></footer></div>
  						</blockquote>
					<div class="less">
					<blockquote class='blockquote-reverse'><footer class='profile_less'><a href='#'>Less</a></footer></blockquote></div>
				</div>
				<div id="mainAreaItem" class="technical"   align="center">
					<div class="tech_updates" align="center"><h4>TECHNOLOGY UPDATES</h4><hr/><div class="load_post"></div></div>
					<div class="more">
 						<blockquote class="blockquote-reverse">
  				 	 		<footer class="tech_more"><a href="#">View More</a></footer></div>
  						</blockquote>
					<div class="less">
					<blockquote class='blockquote-reverse'><footer class='tech_less'><a href='#'>Less</a></footer></blockquote></div>
				</div>
				</div>
			</div>		
	<jsp:include page="./inc/footer.jsp" />
		<script src="./js/home.js"></script>
	</body>
</html>