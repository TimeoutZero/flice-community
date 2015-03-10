package com.timeoutzero.flice.core.resource;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.timeoutzero.flice.core.dao.CommunityDAO;
import com.timeoutzero.flice.core.dto.CommunityDTO;
import com.timeoutzero.flice.core.form.CommunityForm;
import com.timeoutzero.flice.core.model.Community;
import com.timeoutzero.flice.core.model.User;

@Path("/community")
@Produces(MediaType.APPLICATION_JSON)
public class CommunityResource {

	@Inject
	private CommunityDAO dao;
	
	@GET
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommunityDTO findById(@PathParam("id") Long id, @Auth User user){
		
		Community community = dao.loadActive(id);
		return new CommunityDTO(community);
		
	}
	
	@GET
	@Timed
	@UnitOfWork
	public List<CommunityDTO> list(@Auth User user){
		
		List<Community> list = dao.list();
		
		List<CommunityDTO> dtos = new ArrayList<CommunityDTO>();
		for(Community community : list){
			dtos.add(new CommunityDTO(community));
		}
		
		return dtos;
	}
	
	@POST
	@Timed
	@UnitOfWork
	public Response create(@Valid CommunityForm form, @Auth User user){
		
		Community community = form.toEntity();
		community.setOwner(user);
		community.setCreated(LocalDateTime.now());
		community.setActive(true);
		community = dao.save(community);
		
		CommunityDTO dto = new CommunityDTO(community);
		return Response.status(HttpStatus.CREATED_201).entity(dto).build();
	}
	
	@PUT
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommunityDTO update(@PathParam("id") Long id, @Valid CommunityForm form, @Auth User user){
		
		Community community = dao.load(id);
		community.setDescription(form.getDescription());
		community.setImage(form.getImage());
		community.setName(form.getName());
		community.setOwner(user);
		
		dao.save(community);
		
		return new CommunityDTO(community);
		
	}
	
	@DELETE
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommunityDTO delete(@PathParam("id") Long id, @Auth User user){
		
		Community community = dao.load(id);
		community.setActive(false);
		
		dao.save(community);
		
		return new CommunityDTO(community);
	}
	
}
