<jsp>
 		<nav class="navbar navbar-default">
  			<div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->
			    
			    <div class="navbar-header">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <a class="navbar-brand logo" href="home.jsp"><img class="pull-left" style="margin-top:-14px" src="http://www.objectedge.com/sites/all/themes/ob/imgs/footer-logo.png">INTRANET PORTAL</a>
			    </div><!-- /.navbar-header -->
		    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav nav-pills menu">
			 				    <li class="active"><a href="home.jsp">Home</a></li>
			 					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Project<span class="caret"></span></a>
			 					<ul class="dropdown-menu">
         						 <li><a href="createProject.jsp">Create New Project</a></li>
         						 <li><a href="allProjects.jsp">All Projects</a></li>
        						</ul>
			 					</li>
			 					<li> <a href="technicalUpdates.jsp">Technology Updates</a></li>
			 					<li> <a href="index.html">Q & A Forum</a></li>
			 					<li> <a href = "#" id ="sign" data-toggle="modal" data-target="#login-modal"> Login/Register</a></li>
					</ul>	
			    </div><!-- /.navbar-collapse -->
  			</div><!-- /.container-fluid -->
		</nav>
		
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel" id= "login-modal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Login</h4>
      </div>
      <div class="modal-body">
         <h1>Login to Your Account</h1><br>
          <form method="post">
          <input type="text" placeholder="Username" id="email_id">
          <input type="password" placeholder="Password" id="password">
          <input type="submit" class="login loginmodal-submit" value="Login" onclick="loginUser()">
          </form>
        </div>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</jsp>