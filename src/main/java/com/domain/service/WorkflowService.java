package com.domain.service;

import java.util.List;

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

public interface WorkflowService {

	List<TaskInstance> getUserTasks() throws AuthenticationException, UnknowException, WebserviceException,
			AccessDeniedException, RepositoryException, DatabaseException, WorkflowException;

	List<FormElement> getTaskForm(long pdId, String task)
			throws AuthenticationException, UnknowException, WebserviceException, ParseException, AccessDeniedException,
			RepositoryException, DatabaseException, WorkflowException;

	TaskInstance getTaskInstance(long tiId) throws AuthenticationException, UnknowException, WebserviceException,
			AccessDeniedException, RepositoryException, DatabaseException, WorkflowException;

}
