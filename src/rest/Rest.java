package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import json.Address;

@Path("/rest")
public class Rest {
	@GET
	@Path("/test/")
	@Produces(MediaType.APPLICATION_JSON)
	public Address convertCtoF(){
		Address adr = new Address();
		adr.setAge(32);
		adr.setName("Fidde");
		adr.setSurename("Lass");
		return adr;
	}

}
