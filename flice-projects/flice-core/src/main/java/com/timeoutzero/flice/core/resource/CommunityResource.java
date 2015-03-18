package com.timeoutzero.flice.core.resource;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import com.codahale.metrics.annotation.Timed;
import com.timeoutzero.flice.core.dto.CommunityDTO;
import com.timeoutzero.flice.core.form.CommunityForm;
import com.timeoutzero.flice.core.model.Community;
import com.timeoutzero.flice.core.model.User;
import com.timeoutzero.flice.core.service.CoreService;

@Path("/community")
@Produces(MediaType.APPLICATION_JSON)
public class CommunityResource {

	@Inject
	private CoreService coreService;
	
	@GET
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommunityDTO findById(@PathParam("id") Long id, @Auth User user){
		
		Community community = coreService.getCommunityDAO().loadActive(id);
		return new CommunityDTO(community);
	}
	
	@GET
	@Timed
	@UnitOfWork
	public List<CommunityDTO> list(@Auth User user){
		
		List<Community> list =  coreService.getCommunityDAO().list();
		
		return list.stream()
				.map(CommunityDTO::new)
				.collect(Collectors.toList());
	}
	
	@POST
	@Timed
	@UnitOfWork
	public Response create(@Valid CommunityForm form, @Auth User user){
		
		Community community = form.toEntity();
		community.setOwner(user);
		community.setCreated(LocalDateTime.now());
		community.setActive(true);
		community = coreService.getCommunityDAO().save(community);
		
		return Response.status(HttpStatus.CREATED_201).entity( new CommunityDTO(community)).build();
	}
	
	@PUT
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommunityDTO update(@PathParam("id") Long id, @Valid CommunityForm form, @Auth User user){
		
		Community community = coreService.getCommunityDAO().load(id);
		community.setDescription(form.getDescription());
		community.setImage(form.getImage());
		community.setName(form.getName());
		community.setOwner(user);
		
		coreService.getCommunityDAO().save(community);
		
		return new CommunityDTO(community);
		
	}
	
	@DELETE
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommunityDTO delete(@PathParam("id") Long id, @Auth User user){
		
		Community community = coreService.getCommunityDAO().load(id);
		community.setActive(false);
		
		coreService.getCommunityDAO().save(community);
		
		return new CommunityDTO(community);
	}
	
}
