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
import com.timeoutzero.flice.core.dao.UserDAO;
import com.timeoutzero.flice.core.dto.UserDTO;
import com.timeoutzero.flice.core.form.UserForm;
import com.timeoutzero.flice.core.model.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	private UserDAO dao;

	@POST
	@Timed
	@UnitOfWork
	public Response create(@Valid UserForm form) {

		User user = form.toEntity();
		user = dao.create(user);

		UserDTO dto = new UserDTO(user); 
		
		return Response.status(HttpStatus.CREATED_201).entity(dto).build();
	}
}
