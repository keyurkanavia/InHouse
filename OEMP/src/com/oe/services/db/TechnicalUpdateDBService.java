package com.oe.services.db;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class TechnicalUpdateDBService extends DBServices {
	public static MongoCollection<Document> technicalUpdateColl;
	
	static{
		technicalUpdateColl=getCollection("technology");
	}
	
	public static String addtechincalUpdate(String techincalUpdateJson) {
		String output = null;
		Document current = Document.parse(techincalUpdateJson);
		List<Document> Comments=new ArrayList<Document>();
		BasicDBObject document= new BasicDBObject();
		document.put("_id","TU"+ProfileDBServices.getNextId("techid","profile_update"));
		document.put("technologyUpdate",current.getString("technicalUpdate"));
		document.put("date",ProfileDBServices.getCurrentDate());
		document.put("comments",Comments);
		Document updatedJson = Document.parse(document.toString());
		technicalUpdateColl.insertOne(updatedJson);
		output = "Updated";
		return output;
	}
	public static String addtechincalComment(String CommentJson) {
		System.out.println("update post : commentJSON:"+CommentJson);
		Document result = null;
		String output = null;
		Document current = Document.parse(CommentJson);
		String tech_id = current.getString("id");
		String name = current.getString("name");
		String comment = current.getString("addComment");
		String queryParam = "{\"name\":\""+name+"\",\"addComment\":\""+comment+"\",\"date\":\""+ProfileDBServices.getCurrentDate()+"\"}";
		current = Document.parse(queryParam);
		if (tech_id != null) {
			result = technicalUpdateColl.find(eq("_id", tech_id)).first();
			System.out.println("Result"+result);
		}
		if (result != null ) {
			List<Document> comments = (List<Document>) result.get("comments");
			Document newComment = current;
			comments.add(newComment);
			BasicDBObject updateQuery = new BasicDBObject("_id", tech_id);
			Document updateDocument = new Document("$set", result);
			technicalUpdateColl.updateOne(updateQuery, updateDocument);
			output = "Updated";
		}	
		System.out.println("Added Commment:" + output);
		return output;
	}
	/*
	 * Get All Update Profile Data
	 */
	public static List<String> getAllTechnologyUpdateColl() {
		List<Document> foundDocument = technicalUpdateColl.find().into(new ArrayList<Document>());
		List<String> jsonArray = new ArrayList<String>();
		System.out.println(foundDocument.size());
		
		for (Document document : foundDocument) {
			jsonArray.add(document.toJson());
		}
		return jsonArray;
	}
	
}
