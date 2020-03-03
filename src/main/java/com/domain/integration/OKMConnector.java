package com.domain.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.OKMWebservicesFactory;
import com.openkm.sdk4j.exception.OKMException;
import com.openkm.sdk4j.exception.UnknowException;
import com.openkm.sdk4j.exception.WebserviceException;

public class OKMConnector {

	private static Logger log = LoggerFactory.getLogger(OKMConnector.class);

	@Autowired
	private Environment env;
	
	public static OKMConnector getInstance() {
		return new OKMConnector();
	}

	public OKMWebservices getClient() throws OKMException, WebserviceException, UnknowException {
		String url = env.getProperty("openkm.rest.url");
		String user = env.getProperty("openkm.rest.user");
		String pass = env.getProperty("openkm.rest.password");
		OKMWebservices ws = OKMWebservicesFactory.getInstance(url);
		ws.login(user, pass);
		return ws;
	}

}
