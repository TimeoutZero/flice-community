package com.timeoutzero.flice.core.resource;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.mindrot.jbcrypt.BCrypt;

import com.codahale.metrics.annotation.Timed;
import com.timeoutzero.flice.core.api.UserDTO;
import com.timeoutzero.flice.core.dao.CommunityDAO;
import com.timeoutzero.flice.core.dao.TopicDAO;
import com.timeoutzero.flice.core.dao.UserCommunityDAO;
import com.timeoutzero.flice.core.dao.UserDAO;
import com.timeoutzero.flice.core.dao.UserTopicDAO;
import com.timeoutzero.flice.core.form.UserForm;
import com.timeoutzero.flice.core.model.Community;
import com.timeoutzero.flice.core.model.Topic;
import com.timeoutzero.flice.core.model.User;
import com.timeoutzero.flice.core.model.UserCommunity;
import com.timeoutzero.flice.core.model.UserTopic;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	private UserDAO dao;
	
	@Inject
	private TopicDAO topicDAO;
	
	@Inject CommunityDAO communityDAO;
	
	@Inject
	private UserTopicDAO userTopicDAO;
	
	@Inject
	private UserCommunityDAO userCommunityDAO;
	
	@GET
	@Path("/me")
	@UnitOfWork
	public Map<String, String> me(@Auth User user) {
	
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("email", user.getEmail());
		
		return hashMap;
	}

	@POST
	@Timed
	@UnitOfWork
	public Response create(@Valid UserForm form) {

		User user = form.toEntity();
		user = dao.create(user);

		UserDTO dto = new UserDTO(user);

		return Response.status(HttpStatus.CREATED_201).entity(dto).build();
	}

	@POST
	@Timed
	@UnitOfWork
	@Path("/verify")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response verify(@FormParam("email") String email, @FormParam("password") String password) {

		User user = dao.findByEmail(email);

		if (user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		boolean isNotSamePass  = !BCrypt.checkpw(password, user.getPassword());
		
		if (isNotSamePass) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}

		return Response.status(Response.Status.OK).build();
	}
	
	@PUT
	@Timed
	@UnitOfWork
	@Path("/topic")
	public Response followTopic(@Auth User user, @FormParam("topicId") Long topicId, @FormParam("favorite") Boolean favorite){
		Topic topic = topicDAO.loadActive(topicId);
		UserTopic userTopic = userTopicDAO.findByUserAndTopic(user, topic);
		
		if(userTopic == null){
			userTopic = new UserTopic();
		}
		
		userTopic.setFavorite(favorite);;
		userTopic.setUser(user);
		userTopic.setTopic(topic);
		
		userTopicDAO.save(userTopic);
		
		return Response.status(Response.Status.OK).build();
	}

	@PUT
	@Timed
	@UnitOfWork
	@Path("/community")
	public Response followCommunity(@Auth User user, @FormParam("communityId") Long communityId, @FormParam("favorite") Boolean favorite){
		Community community = communityDAO.loadActive(communityId);
		UserCommunity userCommunity = userCommunityDAO.findByUserAndCommunity(user, community);
		
		if(userCommunity == null){
			userCommunity = new UserCommunity();
		}
		
		userCommunity.setFavorite(favorite);;
		userCommunity.setUser(user);
		userCommunity.setCommunity(community);
		
		userCommunityDAO.save(userCommunity);
		
		return Response.status(Response.Status.OK).build();
	}

}
