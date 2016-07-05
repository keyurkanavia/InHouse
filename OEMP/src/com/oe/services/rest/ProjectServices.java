package com.oe.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.oe.services.db.ProjectDBServices;

/**
 * Rest API class using jax-rs for project operations.
 * @author deepak.verma
 *
 */

@Path("/project")
public class ProjectServices {
	
	/**
	 * The method in turn calls ProjectDBServices.getProjectData for fetching the project info JSON.
	 * @param projectId
	 * @return
	 */
	@Path("/getProjectData")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getProjectData(
			@QueryParam("proj_Id") String projectId) {
		System.out.println("findProject:proj_Id:" + projectId);
		String result= ProjectDBServices.getProjectData(projectId);
		return result;
	}
	
	/**
	 * The method in turn calls ProjectDBServices.getPosts for fetching the Posts info JSON.
	 * @param projectId
	 * @return
	 */
	@Path("/getPosts")
	@GET	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getPosts(
			@QueryParam("proj_Id") String projectId) {
		System.out.println("find Posts for Project : proj_Id:" + projectId);
		String result= ProjectDBServices.getPosts(projectId);
		return result;
	}
	
	/**
	 * The method in turn calls ProjectDBServices.updateProjDesc to update the project description in mongoDB.
	 * @param projectId
	 * @return
	 */
	@Path("/updateProjDesc")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProjDesc(
			String projectJson
			) {
		String result = null;
		System.out.println("updateProjectDesc:projectJson:" + projectJson);
		result = ProjectDBServices.updateProjDesc(projectJson);
		return result;
	}
	
	/**
	 * This method inserts the new post added by the user. Thisis done my calling ProjectDBServices.postInsert method.
	 * @param projectId
	 * @return
	 */
	@Path("/postInsert")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String postInsert(
			String projectJson
			) {
		String result = null;
		System.out.println("insert Project post:projectJson:" + projectJson);
		result = ProjectDBServices.postInsert(projectJson);
		return result;
	}
	
	/**
	 * The method in turn calls ProjectDBServices.getAllProjectList for fetching the list of project names JSON.
	 * @return
	 */
	@Path("/getProjectList")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getProjectList(){
		System.out.println("Get all Projects list");
		String result= ProjectDBServices.getAllProjectList();
		return result;
	}
	
	/**
	 * The method in turn calls ProjectDBServices.createNewProj to create new project.
	 * @return
	 */
	@Path("/createNewProj")
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewProj(String projectJson){
		System.out.println("Create new Project");
		String result= ProjectDBServices.createNewProj(projectJson);
		return result;
	}
	
	
	/**
	 * The method in turn calls ProjectDBServices.deleteProj to delete the project from projects collection in mongoDB.
	 * @param projectId
	 * @return
	 */
	@Path("/deleteProj")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProj(
			@QueryParam("proj_Id") String projectId) {
		String result = null;
		System.out.println("Delete project :" + projectId);
		result = ProjectDBServices.deleteProj(projectId);
		return result;
	}
}
