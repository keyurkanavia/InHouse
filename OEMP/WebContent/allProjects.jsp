<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewpoint" content="width=device-width, initial-scale=1.0"/>
		<%@ include file="./inc/gl-html-head.jsp" %>
	</head>
		<%@ include file="./inc/header.jsp" %>
		<body>
		<div  id="contMid">
		<div id="mainArea">
				<div id="mainAreaItem">
			<div style="width:800px">
				<div class="profile"   >
					<div style="margin-left:-70px;margin-top:40px;">
						<div class="action-line to-open">
							<span>All Projects</span>
							<ul id="projectList">
							</ul>
						</div>
					</div>
					<hr>	
			</div>	
		</div>
		</div>
		</div>
		</div>
		<jsp:include page="./inc/footer.jsp" />
		<script type = "text/javascript"  src = "./js/jquery.allProjects.js"></script>
	</body>
</html>