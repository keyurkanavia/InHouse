<jsp>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewpoint" content="width=device-width, initial-scale=1.0"/>
		<%@ include file="./inc/gl-html-head.jsp" %>
	</head>
		<%@ include file="./inc/header.jsp" %>
		<body>
			<div class="container" style="width:800px">
				<div>
					<div style="margin-left:-70px;margin-top:40px;">
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
        <textarea class="form-control" rows="3" id="description"></textarea>
        <span class="help-block" >A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
      </div>
    </div>
    
        <div class="form-group">
      <label for="select" class="col-lg-2 control-label">Select Team Members</label>
      <div class="col-lg-5">
        <br>
       		<ul id="sourceList"class="list-group " style=' height:200px;max-height: 200px;overflow-y:scroll;' ondrop="drop(event)" ondragover="allowDrop(event)">
  				<li id="dr1" class="list-group-item" draggable="true" ondragstart="drag(event)">
   	 				<span class="badge">IND</span>Deepak Verma
  				</li>
  				<li id="dr2" class="list-group-item" draggable="true" ondragstart="drag(event)">
    				<span class="badge">BR</span>Thiago Custodio
  				</li>
  				<li id="dr3" class="list-group-item" draggable="true" ondragstart="drag(event)">
    				<span class="badge">BR</span>Gustavo Seganfredo
  				</li>
   				<li id="dr4" class="list-group-item" draggable="true" ondragstart="drag(event)">
    				<span class="badge">IND</span>Satyajit De
  				</li>
   				<li id="dr5" class="list-group-item" draggable="true" ondragstart="drag(event)">
    				<span class="badge">US</span>Bhargav Trivedi
  				</li>
			</ul>
      </div>
       <div class="col-lg-5">
        <br>
       		<ul id="targetList" class="list-group " style='height:200px;max-height: 200px;overflow-y:scroll;' ondrop="drop(event)" ondragover="allowDrop(event)"></ul>
      </div>
    </div>
    
    
    <div class="form-group">
      <div class="col-lg-10 col-lg-offset-2">
        <input type="button" class="btn btn-primary" name="submit" onclick="addProject();" value="Submit"/>
      </div>
    </div>
  </fieldset>
</form>
						</div>
					</div>
					<hr>	
			</div>	
		</div>
		
		<script type = "text/javascript"  src = "js/jquery.createProject.js"></script>
	</body>
</jsp>