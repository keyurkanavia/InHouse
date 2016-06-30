package com.oe.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oe.services.db.ProfileDBServices;
import com.oe.services.db.TechnicalUpdateDBService;

/**
 * Rest API class using jax-rs for techincalUpdates operations.
 * @author sachin.yadav
 *
 */
@Path("/technicalUpdate")
public class TechnicalUpdatesService {
	@Path("/addTechnicalUpdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addTechnicalUpdate(
			String techincalUpdateJson
			) {
		System.out.println("techincalUpdate:techincalUpdateJson:" + techincalUpdateJson);
		String result = TechnicalUpdateDBService.addtechincalUpdate(techincalUpdateJson);
		return result;
	}
	@Path("/getTechnicalUpdate")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getTechnicalUpdate(
			) {
		System.out.println("techincalUpdate:techincalUpdateJson:");
		List<String> result = TechnicalUpdateDBService.getAllTechnologyUpdateColl();
		String res=result.toString();
		System.out.println(res);
		return res;
	}
	@Path("/addComment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addComment(
			String CommentJson
			) {
		System.out.println("techincalUpdate:techincalUpdateJson:" + CommentJson);
		String result = TechnicalUpdateDBService.addtechincalComment(CommentJson);
		return result;
	}
}
