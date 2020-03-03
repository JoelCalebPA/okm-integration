package com.domain.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.domain.cache.WSCacheDAO;
import com.domain.constants.MimeTypeConstants;
import com.domain.service.CatalogService;
import com.domain.util.PathUtils;
import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.bean.Configuration;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.AccessDeniedException;
import com.openkm.sdk4j.exception.AuthenticationException;
import com.openkm.sdk4j.exception.AutomationException;
import com.openkm.sdk4j.exception.ConversionException;
import com.openkm.sdk4j.exception.DatabaseException;
import com.openkm.sdk4j.exception.ExtensionException;
import com.openkm.sdk4j.exception.LockException;
import com.openkm.sdk4j.exception.NoSuchGroupException;
import com.openkm.sdk4j.exception.NoSuchPropertyException;
import com.openkm.sdk4j.exception.ParseException;
import com.openkm.sdk4j.exception.PathNotFoundException;
import com.openkm.sdk4j.exception.PluginNotFoundException;
import com.openkm.sdk4j.exception.RepositoryException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.ValidationFormException;
import com.openkm.sdk4j.exception.WebserviceException;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

	private static final Logger log = LoggerFactory.getLogger(CatalogServiceImpl.class);

	private static final String TYPE_METADATA_PROPERTY = "okp:type.type";

	private static final String TYPE_METADATA_GROUP = "okg:type";
	
	private static final String CP_PENDING_FOLDER = "cp.pending.folder";

	@Autowired
	private WSCacheDAO wsDao;

	@Override
	public List<Document> getChildren() throws RepositoryException, AccessDeniedException, PathNotFoundException,
			DatabaseException, UnknowException, WebserviceException, IOException, ParseException, NoSuchPropertyException,
			NoSuchGroupException, LockException, ExtensionException, AutomationException, AuthenticationException {
		log.debug("getChildren()");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		Configuration pendingPath = getProperty(CP_PENDING_FOLDER);
		
		String folderUuid = ws.getNodeUuid(pendingPath.getValue());
		List<Document> documents = ws.getDocumentChildren(folderUuid);

		List<Document> documentsWithType = new ArrayList<>();
		for (Document doc : documents) {
			if (ws.hasGroup(doc.getUuid(), TYPE_METADATA_GROUP)) {
				documentsWithType.add(doc);
			}
		}
		return documentsWithType;
	}

	@Override
	public void setPropertiesToNode(String uuid, String groupName, Map<String, String> properties)
			throws AccessDeniedException, IOException, ParseException, NoSuchPropertyException, NoSuchGroupException,
			LockException, PathNotFoundException, RepositoryException, DatabaseException, ExtensionException,
			AutomationException, UnknowException, WebserviceException, AuthenticationException, PluginNotFoundException, ValidationFormException {
		log.debug("setPropertiesToNode({}, {}, {})", uuid, groupName, properties);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		
		// Only add group if it has not
		if (!ws.hasGroup(uuid, groupName)) {
			ws.addGroup(uuid, groupName, properties);
		} else {
			ws.setPropertyGroupProperties(uuid, groupName, properties);
		}
	}

	@Override
	public Document getDocumentById(String docId) throws RepositoryException, AccessDeniedException, PathNotFoundException,
			DatabaseException, UnknowException, WebserviceException, AuthenticationException {
		log.debug("getDocumentById({})", docId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		return ws.getDocumentProperties(docId);
	}

	@Override
	public void assignType(String uuid, String type) throws AccessDeniedException, IOException, ParseException,
			NoSuchPropertyException, NoSuchGroupException, LockException, PathNotFoundException, RepositoryException,
			DatabaseException, ExtensionException, AutomationException, UnknowException, WebserviceException, PluginNotFoundException, ValidationFormException, AuthenticationException {
		log.debug("assignType({}, {})", uuid, type);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		
		Map<String, String> properties = new HashMap<>();
		properties.put(TYPE_METADATA_PROPERTY, type);
		// Only add group if it has not
		if (!ws.hasGroup(uuid, TYPE_METADATA_GROUP)) {
			ws.addGroup(uuid, TYPE_METADATA_GROUP, properties);
		} else {
			ws.setPropertyGroupProperties(uuid, TYPE_METADATA_GROUP, properties);
		}
	}

	private Configuration getProperty(String property) throws DatabaseException, UnknowException, WebserviceException, AuthenticationException {
		log.debug("getProperty({})", property);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		return ws.getConfiguration(property);
	}

	@Override
	public void removeType(String uuid) throws AccessDeniedException, NoSuchGroupException, LockException,
			PathNotFoundException, RepositoryException, DatabaseException, ExtensionException, AutomationException,
			UnknowException, WebserviceException, IOException, ParseException, NoSuchPropertyException, AuthenticationException {
		log.debug("removeType({})", uuid);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		ws.removeGroup(uuid, TYPE_METADATA_GROUP);
		
		Map<String, String> properties = new HashMap<>();
		ws.addGroup(uuid, TYPE_METADATA_GROUP, properties);
	}

	@Override
	public InputStream getContent(String node) throws RepositoryException, IOException, PathNotFoundException,
			AccessDeniedException, DatabaseException, UnknowException, WebserviceException, AuthenticationException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		return ws.getContent(node);
	}

	@Override
	public InputStream getContentInPdfFormat(String node) throws RepositoryException, PathNotFoundException, DatabaseException,
			UnknowException, WebserviceException, AccessDeniedException, IOException, NotImplementedException,
			ConversionException, AuthenticationException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		Document doc = getProperties(node);

		if (MimeTypeConstants.validImageMagick.contains(doc.getMimeType())) {
			return ws.imageConvert(getContent(node), PathUtils.getName(doc.getPath()), "", MimeTypeConstants.MIME_PDF);
		} else if (MimeTypeConstants.validOpenOffice.contains(doc.getMimeType())) {
			return ws.doc2pdf(getContent(node), PathUtils.getName(doc.getPath()));
		}

		return ws.getContent(node);
	}

	@Override
	public Document getProperties(String node) throws RepositoryException, PathNotFoundException, DatabaseException,
			UnknowException, WebserviceException, AccessDeniedException, AuthenticationException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OKMWebservices ws = wsDao.getOKMWebservices(auth.getName());
		return ws.getDocumentProperties(node);
	}
}
