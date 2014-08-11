package com.nate.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nate.contracts.Offering;
import com.nate.contracts.Person;
import com.nate.dao.ItemDAO;
import com.nate.dao.UserDAO;
import com.nate.dao.model.Items;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;


@Component
@Path("/payment")
@Api(value="/payment",description="Actions related to Items")
public class PaymentService {

	
	private static Logger log = LoggerFactory.getLogger(PaymentService.class);
	@Autowired private ItemDAO itemdao;
	
 
	
	@POST
	@Path("/addItem")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value="Add an item to server")
	public Response addItem(final Offering item)
	{
		try{
			Items newitem = new Items();
			
			newitem.setCost(item.getCost());
			newitem.setName(item.getName());
			newitem.setPrice(item.getPrice());
			newitem.setProfit((float) (item.getPrice() - item.getCost()));
			newitem.setUserID(1);
			
			this.itemdao.AddItem(newitem);
			
			return Response.status(200).build();
		}catch(Exception e){
			log.error("Error calling AddItem with item: "+item.toString()+" Stack trace: \n"+e.getMessage());
			return Response.status(500).build();
		}
		
	}

	
	@GET
	@Path("/offering")
	@Produces("application/json")
	@ApiOperation(value="Get All the offering per userID")
	@ApiResponse(code = 200, message="OK")
	public Response getOffering(){
		
		try{
			List<Items> offering = itemdao.getAllItemsFomUser(1);
			return Response.status(200).entity(offering).build();
		}catch(Exception e)
		{
			log.error("Cannot get AllItems...\n"+e.getMessage());
			return Response.status(500).build();
		}
	}
	
	@POST
	@Path("/editItem")
	@Consumes("application/json")
	@Produces("application/json")
	public Response ChangeItem(final Offering item)
	{
		
		try{
			Items newItem = new Items();
			newItem.setCost(item.getCost());
			newItem.setPrice(item.getPrice());
			newItem.setName(item.getName());
			
			//calculate new profit
			newItem.setProfit( (float) (item.getPrice() - item.getCost()) );
			
			newItem.setUserID(1);
			
			//call dao using userID and itemID
			return Response.status(200).build();
		}catch(Exception e)
		{
			log.error("Error when Editing item: "+item.toString()+" Error was \n"+e.getMessage());
			return Response.status(500).build();
		}
	}
}
