<jsp>
	<head>
		<%@ include file="./inc/gl-html-head.jsp" %>
	</head>
		<%@ include file="./inc/header.jsp" %>
		<body>
			<div id="contMid" >
			<div id="mainArea">
			<div id="mainAreaItem" >
			<div class="container" style="width:40em">
				<div>
					<div>
						<div class="action-line to-open">
	<form class="form-horizontal" name="addProjForm" id="addProjForm" action="/oemp/rest/project/createNewProj">
  <fieldset>
    <legend>Create New Project</legend>
    <div class="form-group">
      <label for="inputEmail" class="col-lg-2 control-label">Project Name</label>
      <div class="col-lg-10">
        <input type="text" class="form-control" id="name" placeholder="Project Name">
      </div>
    </div>
   
    <div class="form-group">
      <label for="textArea" class="col-lg-2 control-label">Description</label>
      <div class="col-lg-10">
        <textarea class="form-control" rows="3" id="description" placeholder="Description"></textarea>
        <span class="help-block" >A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
      </div>
    </div>
    
        <div class="form-group">
      <label for="select" class="col-lg-2 control-label">Select Team Members</label>
      <div class="col-lg-5">
        <br>
       		<ul id="sourceList"class="list-group " style=' height:200px;max-height: 200px;overflow-y:scroll;' ondrop="drop(event)" ondragover="allowDrop(event)">
			</ul>
      </div>
       <div class="col-lg-5">
        <br>
       		<ul id="targetList" class="list-group " style='height:200px;max-height: 200px;overflow-y:scroll;' ondrop="drop(event)" ondragover="allowDrop(event)"></ul>
      </div>
      <span class="help-block" >Drag and drop the members in the right area to select the team members.</span>
    </div>
    
    
    <div class="form-group">
      <div class="col-lg-10 col-lg-offset-2">
        <input type="button" class="btn btn-primary" name="submit" onclick="addProject();" value="Submit"/>
      </div>
    </div>
        <div class="form-group">
      <div class="col-lg-10 col-lg-offset-2">
        <div class="alert alert-success" role="alert" hidden>...</div>
		<div class="alert alert-info" role="alert" hidden>...</div>
		<div id="warning" class="alert alert-warning" role="alert" hidden></div>
		<div id="danger" class="alert alert-danger" role="alert" hidden>...</div>
      </div>
    </div>
  </fieldset>
</form>
						</div>
					</div>
					<hr>	
			</div>	
		</div>
		</div>
		</div>
		</div>
		<jsp:include page="./inc/footer.jsp" />
		<script type = "text/javascript"  src = "js/jquery.createProject.js"></script>
	</body>
</jsp>