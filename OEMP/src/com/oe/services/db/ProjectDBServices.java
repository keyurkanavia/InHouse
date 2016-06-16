package com.oe.services.db;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Mongo DB connecter class to query/update project related items. This class extends DBServices.
 * @author deepak.verma
 *
 */

public class ProjectDBServices extends DBServices{


	public static MongoCollection<Document> getProjectsCollection() {
	
		MongoDatabase db = getSourceDB();
		System.out.println("Connected to DB");
		MongoCollection<Document> coll = db.getCollection("projects");
		if (coll == null) {
			throw new RuntimeException("mongo db connect error");
		}
		System.out.println("Connected to projects collection");
		return coll;
	}


	public static List<String> findAllProjects() {
		MongoCollection<Document> coll = getProjectsCollection();
		List<Document> foundDocument = coll.find().into(new ArrayList<Document>());
		List<String> jsonArray = new ArrayList<String>();
		for (Document document : foundDocument) {
			jsonArray.add(document.toJson());
		}
		return jsonArray;
	}

	public static String getProjectData(String projectId) {
		System.out.println("getProjectData: project_id:"+projectId);
		Document result = null;
		String output = null;
		MongoCollection<Document> coll = getProjectsCollection();
		if (projectId != null) {
			result = coll.find(eq("proj_id", projectId)).first();
		}
		if(result == null) {
			output = "{project : Not Found}";
		} else {
			output = result.toJson();
		}
		 
		System.out.println("getProjectData:result:" + output);
		return output;
	}

	public static String updateProjDesc(String projectJson) {
		System.out.println("addEmployee: projectJson:"+projectJson);
		Document result = null;
		String output = null;
		MongoCollection<Document> coll = getProjectsCollection();
		Document current = Document.parse(projectJson);
		String proj_id = current.getString("proj_id");
		if (proj_id != null) {
			result = coll.find(eq("proj_id", proj_id)).first();
		}
		if (result != null ) {
			coll.updateOne(eq("proj_id", proj_id), new Document("$set", current));
			output = "Updated";
		}
		System.out.println("updated Project Description:output:" + output);
		return output;
	}
}
