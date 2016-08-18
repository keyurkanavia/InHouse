package com.oe.services.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongodb.client.MongoCollection;

public class GetExtractedEmailDataServiceDB extends DBServices {
public static MongoCollection<Document> extractedEmailDataColl;
	
	static{
		extractedEmailDataColl=getCollection("extractedEmail");
	}
	/*
	 * Get All Update Profile Data
	 */
	public static List<String> getAllEmailData() {
		List<Document> foundDocument = extractedEmailDataColl.find().into(new ArrayList<Document>());
		List<String> jsonArray = new ArrayList<String>();
		System.out.println(foundDocument.size());
	   
		for (Document document : foundDocument) {
			
			jsonArray.add(extractData(document.get("email_data").toString(),document.get("email").toString(),document.get("date").toString()).toJSONString());
		}
		return jsonArray;
	}

	public static JSONObject extractData(String data,String email,String date){
		JSONObject obj = new JSONObject();
		JSONObject orderPriceInfo = new JSONObject();
		JSONObject commerceItem = new JSONObject();
		JSONObject profile=new JSONObject();
		JSONObject shippingAddress=new JSONObject();
		JSONObject shippingGroup=new JSONObject();
		JSONObject itemPriceInfoData=new JSONObject();
		JSONObject itemPriceInfo=new JSONObject();
		ArrayList<String> temp = new ArrayList<String>();
		//OrderPriceinfo
		String order_id=StringUtils.substringBetween(data,"Recebemos o seu pedido *Nº ","* e agora aguardamos a ");
		String order_total_price=StringUtils.substringBetween(data,"Total:","\n\nMais informações que você precisa saber sobre entrega e troca:");
		String order_items=StringUtils.substringBetween(data,"Itens do Pedido Qtd Valor Unit Subtotal","Valor do frete:");
		
		String order_item_data[]=order_items.split("\n");
		if(order_item_data!=null){
				for(int i=0;i<order_item_data.length;i++){
					if(!order_item_data[i].equals("\n")&&!order_item_data[i].equals("[image: produto]")&&!order_item_data[i].equals("")&&!order_item_data[i].contains("Cor")&&!order_item_data[i].contains("Tamanho")&&!order_item_data[i].contains("*Enviado")&&!order_item_data[i].contains("Sabor")){
						temp.add(order_item_data[i]);
					}
				}
		}	
		
		
		HashMap<String, Object> hs=new HashMap<String, Object>();
		JSONArray jsonarry=new JSONArray();

		for(int i=0;i<temp.size();i++){
			
			 if (! (i % 2 == 0) ){
				String item_price_info[]=temp.get(i).split(" ");
				if(item_price_info!=null){
					hs.put("quantity", item_price_info[0]);
					
					 itemPriceInfoData.put("unitPrice",item_price_info[2].replace(",","."));
					
					 itemPriceInfoData.put("itemSubTotal",item_price_info[4].replace(",","."));
					 if(item_price_info[3].equals("R$")){
					 
						 itemPriceInfoData.put("currencyCode","BRL");
					 }else if(item_price_info[3].equals("$")){
					 
						 itemPriceInfoData.put("currencyCode","USD");
					 }
					 
					 hs.put("itemPriceInfo",itemPriceInfoData.clone());
					 jsonarry.add(hs.clone());
					 itemPriceInfoData.clear();
					 hs.clear();
				} 
			 }
			 else{
				 hs.put("displayName",temp.get(i));
			 }	
			 
		}
		
		
		
		
		String discount=StringUtils.substringBetween(data,"Desconto: -R$ ","Vale p").replace("\n", "").replace("\n", "").replace(",", ".").replace(" ", "").replace("R$","").replace("-","");
		String shipping=StringUtils.substringBetween(data,"\n\nValor do frete: R$ ","\n\nDesconto:").replace("\n", "").replace(",", ".").replace(" ", "").replace("R$","").replace("-","");
		String giftWrapPrice=StringUtils.substringBetween(data,"Vale p","Total:").replace("resente:", "").replace("\n", "").replace(",", ".").replace(" ", "").replace("R$","").replace("-","");
		orderPriceInfo.put("discount", discount);
		orderPriceInfo.put("shipping", shipping);
		orderPriceInfo.put("giftWrapPrice", giftWrapPrice);
		orderPriceInfo.put("orderId", order_id);
		orderPriceInfo.put("total", order_total_price.replace("R$","").replace(" ","").replace(",","."));
		if(StringUtils.substringBetween(data,"Vale p","Total:").contains("R$")){
			orderPriceInfo.put("currencyCode","BRL");
		}else if(StringUtils.substringBetween(data,"Vale p","Total:").contains("$")){
			orderPriceInfo.put("currencyCode","USD");
		}
	
		
		//ShippingGroup
		String addressExtact[]=StringUtils.substringBetween(data, "\n\n*Min", "\n\n*Forma").split("\n");
		String fullName[]=addressExtact[2].replace("*", "").split(" ");
		if(addressExtact!=null && fullName!=null){
			shippingAddress.put("address1", addressExtact[3]);
			shippingAddress.put("address2", addressExtact[4].replace("Bairro: ", ""));
			shippingAddress.put("city",addressExtact[5].replace("Cidade: ", ""));
			shippingAddress.put("country",addressExtact[7]);
			shippingAddress.put("firstName",fullName[0]);
			shippingAddress.put("lastName",fullName[1]);
			shippingAddress.put("email",email);
			shippingGroup.put("shippingAddress",shippingAddress);
			obj.put("shippingGroup", shippingGroup);
		}
		
		//profile
		String cep_id[]=StringUtils.substringBetween(data,"CEP:","*Forma").split("\n");
		if(cep_id!=null){
			profile.put("cepId",cep_id[0]);
		}
		profile.put("userName",StringUtils.substringBetween(data,"Olá","Recebemos").replace(" ", "").replace(",", "").replace(".", "").replace("\n", ""));
		profile.put("orderId",order_id);
		if(fullName!=null){
			profile.put("firstName",fullName[0]);
			profile.put("lastName",fullName[1]);
		}
		profile.put("email",email);
		
	
		obj.put("orderId", order_id);
		obj.put("stateDetail", "CONFIRMATION");
		obj.put("date", date.toString());
		obj.put("commerceItems", jsonarry);
		obj.put("profile", profile);
		obj.put("orderPriceInfo",orderPriceInfo );
		
		return obj;
	}
	
	
}
