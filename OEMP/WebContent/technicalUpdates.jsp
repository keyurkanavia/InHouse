<jsp>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewpoint" content="width=device-width, initial-scale=1.0"/>
		<jsp:include page="./inc/gl-html-head.jsp" />
	</head>
		<jsp:include page="./inc/header.jsp" />
		<body>
			<div class="container" style="width:800px">
				<div class="profile border"   >
					<div style="margin-left:-70px;margin-top:40px;">
						<textarea rows="4" cols="80" class="post" id="post" placeholder="Share Some Technical Doc/videos/ppt...."></textarea></br></br>
						<input type="button" name="button" id="btn_post" value="POST"  style="margin-left:400px">
					</div>
					<hr>
					<div class="load_post">
					
					</div>	
					
				<div>
				</div>	
			</div>
		</div>
			<jsp:include page="./inc/footer.jsp" />			
	<!-- jQuery library -->

			<script src="./js/technicalUpdates.js"></script>
	</body>
</html>
</jsp>