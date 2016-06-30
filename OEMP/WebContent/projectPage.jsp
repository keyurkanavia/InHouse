<jsp>
   <head>
	  <link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/jquery.jqtimeline.css" />
	<%@ include file="./inc/gl-html-head.jsp" %>
   </head>

   <body onload="init()">
     <jsp:include page="./inc/header.jsp" />
	  <div id="container">
	  <table id="projHeader">
		<tr id="projBannerContainer"><td id="logoContainer"><img id="logo" src="http://placehold.it/350x150"/></td>
									 <td id="banner"><img src="http://placehold.it/850x250"/></td>
		</tr>
		<!-- <tr>
			<td colspan="2">
			<hr/>
				<div id="myTimeline"></div>
			</td>
		</tr> --></table>
		
		<div id="projMid">
		<div id="mainArea">
		<div id="mainAreaItem">
		<div id="description"></div>
<a class="act">More</a><div id="editDescBar"><a href="#" ><span id="editDesc">edit</span></a></div>
</div>

<div id="mainAreaItem">
	<h1>Posts</h1>
		<div id="dashboard">	
		</div></div>


		<div id="comment_form">
			<div>
				<textarea rows="10" name="comment" id="comment" placeholder="Comment"></textarea>
			</div>
		<!--<div><input type="submit" name="submit" value="Add Comment"></div>-->
		</div>
</div>
<div id="team"><a href="#" ><span id="allTeam">see all</span></a><h1>Team members</h1>
</div>
</div>
	  </div>
	  </div>
 <jsp:include page="./inc/footer.jsp" />
		
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.jqtimeline.js"></script>
		<script type = "text/javascript"  src = "js/jquery.projectPage.js"></script>
		
		<!-- The Modal -->
<div id="allTeamModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">x</span>
  </div>

</div>

<div id="editDescModal" class="modal" >
<div class="modal-content">
<span class="close">x</span>
<form name="addEmpForm" id="addPrjForm" action="/oemp/rest/project/addDescription">
		<textarea rows="4" cols="100" id="descEditable" form="addPrjForm">Enter text here...</textarea>
		<input type="button" name="submit" value="submit" onclick="updateDescription();"/>
	</form>
	<br>
</div>
</div>

   </body>

</jsp>