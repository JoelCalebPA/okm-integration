package com.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.domain.cache.WSCacheDAO;
import com.domain.service.WorkflowService;
import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.bean.form.FormElement;
import com.openkm.sdk4j.bean.workflow.TaskInstance;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.AuthenticationException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.ParseException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;
import com.openkm.sdk4j.exception.WorkflowException;

@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {

	private static final Logger log = LoggerFactory.getLogger(WorkflowServiceImpl.class);

	@Autowired
	private WSCacheDAO wsDao;

	@Override
	public List<TaskInstance> getUserTasks() throws AuthenticationException, UnknowException, WebserviceException,
			AccessDeniedException, RepositoryException, DatabaseException, WorkflowException {
		log.debug("getUserTasks()");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		List<TaskInstance> userTasks = ws.findUserTaskInstances();
		return userTasks;
	}

	@Override
	public List<FormElement> getTaskForm(long pdId, String task)
			throws AuthenticationException, UnknowException, WebserviceException, ParseException, AccessDeniedException,
			RepositoryException, DatabaseException, WorkflowException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		Map<String, List<FormElement>> forms = ws.getProcessDefinitionForms(pdId);
		List<FormElement> fes = forms.get(task);
		return fes;
	}

	@Override
	public TaskInstance getTaskInstance(long tiId) throws AuthenticationException, UnknowException, WebserviceException,
			AccessDeniedException, RepositoryException, DatabaseException, WorkflowException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		TaskInstance instance = ws.getTaskInstance(tiId);
		return instance;
	}

}
