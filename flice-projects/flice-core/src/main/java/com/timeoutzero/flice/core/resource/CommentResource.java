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
import com.timeoutzero.flice.core.dao.CommentDAO;
import com.timeoutzero.flice.core.dao.TopicDAO;
import com.timeoutzero.flice.core.dto.CommentDTO;
import com.timeoutzero.flice.core.form.CommentForm;
import com.timeoutzero.flice.core.model.Comment;
import com.timeoutzero.flice.core.model.User;

@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	@Inject
	private CommentDAO dao;
	
	@Inject
	private TopicDAO topicDAO;
	
	@GET
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommentDTO findById(@PathParam("id") Long id, @Auth User user){
		
		Comment comment = dao.loadActive(id);
		return new CommentDTO(comment);
	}
	
	@GET
	@Timed
	@UnitOfWork
	public List<CommentDTO> list(@Auth User user){
		
		List<Comment> list = dao.list();
		
		List<CommentDTO> dtos = new ArrayList<CommentDTO>();
		for(Comment comment : list){
			dtos.add(new CommentDTO(comment));
		}
		
		return dtos;
	}
	
	@POST
	@Timed
	@UnitOfWork
	public Response create(@Valid CommentForm form, @Auth User user){
		
		Comment comment = form.toEntity();
		comment.setActive(true);
		comment.setCreated(LocalDateTime.now());
		comment.setOwner(user);
		comment.setTopic(topicDAO.loadActive(form.getTopicId()));
		
		comment = dao.save(comment);
		
		CommentDTO dto = new CommentDTO(comment);
		
		return Response.status(HttpStatus.CREATED_201).entity(dto).build();
	}
	
	@PUT
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommentDTO update(@PathParam("id") Long id, @Valid CommentForm form, @Auth User user){
		
		Comment comment = dao.load(id);
		comment.setContent(form.getContent());
		comment.setOwner(user);
		comment.setTopic(topicDAO.loadActive(form.getTopicId()));
		
		comment = dao.save(comment);
		
		return new CommentDTO(comment);
	}
	
	@DELETE
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public CommentDTO delete(@PathParam("id") Long id, @Auth User user){
		
		Comment comment = dao.load(id);
		comment.setActive(false);
		comment = dao.save(comment);
		return new CommentDTO(comment);
	}
	
}
