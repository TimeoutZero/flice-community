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
import com.timeoutzero.flice.core.dto.TopicDTO;
import com.timeoutzero.flice.core.form.TopicForm;
import com.timeoutzero.flice.core.model.Topic;
import com.timeoutzero.flice.core.model.User;
import com.timeoutzero.flice.core.service.CoreService;

@Path("/topic")
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {

	@Inject
	private CoreService coreService;
	
	@GET
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public TopicDTO findById(@PathParam("id") Long id, @Auth User user){
		
		Topic topic = coreService.getTopicDAO().loadActive(id);
		return new TopicDTO(topic);
	}
	
	@GET
	@Timed
	@UnitOfWork
	public List<TopicDTO> list(@Auth User user){
		
		List<Topic> list = coreService.getTopicDAO().list();
		
		List<TopicDTO> dtos = new ArrayList<TopicDTO>();
		for(Topic topic : list){
			dtos.add(new TopicDTO(topic));
		}
		
		return dtos;
	}
	
	@POST
	@Timed
	@UnitOfWork
	public Response create(@Valid TopicForm form, @Auth User user){
		
		Topic topic = form.toEntity();
		
		topic.setCreated(LocalDateTime.now());
		topic.setActive(true);
		topic.setCommunity(coreService.getCommunityDAO().loadActive(form.getCommunityId()));
		topic.setOwner(user);
		
		topic = coreService.getTopicDAO().save(topic);
		
		return Response.status(HttpStatus.CREATED_201).entity(new TopicDTO(topic)).build();
	}
	
	@PUT
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public TopicDTO update(@PathParam("id") Long id, @Valid TopicForm form, @Auth User user){
		
		Topic topic = coreService.getTopicDAO().load(id);
		topic.setCommunity(coreService.getCommunityDAO().loadActive(form.getCommunityId()));
		topic.setName(form.getName());
		topic.setOwner(user);
		
		topic = coreService.getTopicDAO().save(topic);
		
		return new TopicDTO(topic);
	}
	
	@DELETE
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public TopicDTO delete(@PathParam("id") Long id, @Auth User user){
		
		Topic topic = coreService.getTopicDAO().load(id);
		topic.setActive(false);
		topic = coreService.getTopicDAO().save(topic);
		
		return new TopicDTO(topic);
	}
	
}
