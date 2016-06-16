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
		System.out.println("findEmployee:projectJson:" + projectJson);
		result = ProjectDBServices.updateProjDesc(projectJson);
		return result;
	}
	
}
