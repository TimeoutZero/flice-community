package com.timeoutzero.flice.core.service;

import lombok.Getter;

import com.google.inject.Inject;
import com.timeoutzero.flice.core.dao.CommentDAO;
import com.timeoutzero.flice.core.dao.CommunityDAO;
import com.timeoutzero.flice.core.dao.TopicDAO;
import com.timeoutzero.flice.core.dao.UserCommunityDAO;
import com.timeoutzero.flice.core.dao.UserDAO;
import com.timeoutzero.flice.core.dao.UserTopicDAO;

@Getter
public class CoreService {

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserTopicDAO userTopicDAO;
	
	@Inject
	private UserCommunityDAO userCommunityDAO;
	
	@Inject
	private TopicDAO topicDAO;
	
	@Inject
	private CommentDAO commentDAO;
	
	@Inject
	private CommunityDAO communityDAO;
}
