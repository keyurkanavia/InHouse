package com.oe.services.db;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;

/**
 * Mongo DB connecter class to query/update project related items. This class extends DBServices.
 * @author deepak.verma
 *
 */

public class ProjectDBServices extends DBServices{
	
	public static MongoCollection<Document> projectColl;
	
	static{
		projectColl = getCollection("projects");
	}
	
	public static List<String> findAllProjects() {
		List<Document> foundDocument = projectColl.find().into(new ArrayList<Document>());
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
		if (projectId != null) {
			result = projectColl.find(eq("proj_id", projectId)).first();
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
		System.out.println("update Project : projectJson:"+projectJson);
		Document result = null;
		String output = null;
		Document current = Document.parse(projectJson);
		String proj_id = current.getString("proj_id");
		if (proj_id != null) {
			result = projectColl.find(eq("proj_id", proj_id)).first();
		}
		if (result != null ) {
			projectColl.updateOne(eq("proj_id", proj_id), new Document("$set", current));
			output = "Updated";
		}
		System.out.println("updated Project Description:output:" + output);
		return output;
	}
	
	public static String postInsertOldImpl(String projectJson) {
		System.out.println("update post : projectJson:"+projectJson);
		Document result = null;
		String output = null;
		Document current = Document.parse(projectJson);
		String proj_id = current.getString("proj_id");
		List<Document> post = (List<Document>)current.get("posts");
		String text = post.get(0).getString("text");
		String user_id = post.get(0).getString("user");
		String queryParam = "{\"user\":\""+user_id+"\",\"text\":\""+text+"\"}";
		current = Document.parse(queryParam);
		if (proj_id != null) {
			result = projectColl.find(eq("proj_id", proj_id)).first();
		}
		if (result != null ) {
			List<Document> posts = (List<Document>) result.get("posts");
			Document newPost = current;
			posts.add(newPost);
			System.out.println("posts = "+posts);
			BasicDBObject updateQuery = new BasicDBObject("proj_id", proj_id);
			Document updateDocument = new Document("$set", result);
			projectColl.updateOne(updateQuery, updateDocument);
			output = "Updated";
		}
		System.out.println("updated Project Post:output:" + output);
		
		//insertPostToPostsColl(projectJson);
		
		
		return output;
	}


	public static String postInsert(String projectJson) {
		System.out.println("insert new post to posts collection : projectJson:"+projectJson);
		String output = null;
		Document current = Document.parse(projectJson);
		String proj_id = current.getString("source");
		MongoCollection<Document>postsColl = getCollection("posts");
		
		postsColl.insertOne(current);
		output = "Created";
		
		System.out.println("Created new Post for project "+ proj_id +":output:" + output);
		return output;
	}

	public static String getAllProjectList() {
		System.out.println("get all Project List");
		Document result = null;
		String output = null;
		FindIterable<Document> iterable = projectColl.find();
		final List<Document> projectNamesList = new ArrayList<Document>();
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		        projectNamesList.add(new Document("name",document.get("proj_id")));
		    }
		});
		result = new Document("names",projectNamesList);
		if(result == null) {
			output = "{project : Not Found}";
		} else {
			output = result.toJson();
		}
		 
		System.out.println("getProjectData:result:" + output);
		return output;
	}

	public static String createNewProj(String projectJson) {
		System.out.println("New Project : projectJson:"+projectJson);
		Document result = null;
		String output = null;
		Document current = Document.parse(projectJson);
		String name = current.getString("name");
		if (name != null) {
			result = projectColl.find(eq("proj_id", name)).first();
		}
		if (result != null ) {
			projectColl.updateOne(eq("proj_id", name), new Document("$set", current));
			output = "Updated";
		} else {			
			projectColl.insertOne(current);
			output = "Created";
		}
		System.out.println("new Project:output:" + output);
		return output;
	}

	public static String getPosts(final String projectId) {
		System.out.println("get Posts: project_id:"+projectId);
		MongoCollection<Document>postsColl = getCollection("posts");
		List<Document> foundDocument = null;
		
		Document result = null;
		String output = null;
		FindIterable<Document> iterable = postsColl.find();
		final List<Document> pojectList = new ArrayList<Document>();
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		        if(document.get("source").equals(projectId)){
		        	pojectList.add(new Document("user",document.get("user")).append("text", document.get("text")));
		        }
		    }
		});
		result = new Document("posts",pojectList);
		
		
		if(result == null) {
			output = "{project : Not Found}";
		} else {
			output = result.toJson();
		}
		 
		System.out.println("getProjectData:result:" + output);
		return output;
	}

}
