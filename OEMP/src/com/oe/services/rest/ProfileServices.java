package com.oe.services.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.oe.services.db.DBServices;

/**
 * Rest API class using jax-rs for profile operations.
 * @author keyur.kanavia
 *
 */

@Path("/profile")
public class ProfileServices {

		
	@Path("/findEmployee")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String findEmployee(
			@QueryParam("emp_id") String employeeId,
			@QueryParam("email") String emailId) {
		String result = DBServices.findEmployee(employeeId, emailId);
		return result;
	}
	
	@Path("/addEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addEmployee(
			@FormParam("employeeJson") String employeeJson,
			@FormParam("email") String emailId) {
		String result = DBServices.addEmployee(employeeJson, emailId);
		return result;
	}
}
