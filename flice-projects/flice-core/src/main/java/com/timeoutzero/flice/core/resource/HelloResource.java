package com.timeoutzero.flice.core.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

	@GET
	public Map<String, String> getHello() {

		Map<String, String> map = new HashMap<>();
		map.put("hello", "world");
		
		return map;
	}
}
