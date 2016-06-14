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
			@QueryParam("emp_Id") String employeeId,
			@QueryParam("emailId") String emailId) {
		System.out.println("findEmployee:emp_Id:" + employeeId + ":emailId:" + emailId);
		String result = DBServices.findEmployee(employeeId, emailId);
		return result;
	}
	
	@Path("/addEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addEmployee(
			String employeeJson
			) {
		System.out.println("findEmployee:employeeJson:" + employeeJson);
		String result = DBServices.addEmployee(employeeJson);
		return result;
	}
}
