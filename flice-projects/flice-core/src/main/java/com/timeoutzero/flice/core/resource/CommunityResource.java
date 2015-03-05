package com.timeoutzero.flice.core.resource;

import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import com.codahale.metrics.annotation.Timed;
import com.timeoutzero.flice.core.dao.CommunityDAO;
import com.timeoutzero.flice.core.dto.CommunityDTO;
import com.timeoutzero.flice.core.form.CommunityForm;
import com.timeoutzero.flice.core.model.Community;

@Path("/community")
@Produces(MediaType.APPLICATION_JSON)
public class CommunityResource {

	@Inject
	private CommunityDAO dao;
	
	@POST
	@Timed
	@UnitOfWork
	public Response create(@Valid CommunityForm form){
		Community community = form.toEntity();
		community = dao.create(community);
		
		CommunityDTO dto = new CommunityDTO(community);
		return Response.status(HttpStatus.CREATED_201).entity(dto).build();
	}
	
}
