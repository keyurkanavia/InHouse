package com.oe.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oe.services.db.GetExtractedEmailDataServiceDB;

/**
 * Rest API class using jax-rs for techincalUpdates operations.
 * @author sachin.yadav
 *
 */
@Path("/getExtractedEmail")
public class GetExtractedEmailDataService {
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getTechnicalUpdate(
			) {
		System.out.println("extractedEmailData:extractedEmailData:");
		List<String> result = GetExtractedEmailDataServiceDB.getAllEmailData();
		String res=result.toString();
		System.out.println(res);
		return res;
	}
}
