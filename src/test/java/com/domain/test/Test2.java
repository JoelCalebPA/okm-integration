package com.domain.test;

import java.util.List;
import java.util.Map;

import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.OKMWebservicesFactory;
import com.openkm.sdk4j.bean.form.FormElement;
import com.openkm.sdk4j.bean.form.Option;
import com.openkm.sdk4j.bean.form.Select;
import com.openkm.sdk4j.bean.workflow.TaskInstance;

public class Test2 {

	public static void main(String[] args) {
		String host = "http://192.168.1.10:8080/openkm";
		String user = "okmAdmin";
		String password = "admin";
		OKMWebservices ws = OKMWebservicesFactory.getInstance(host);

		try {
			ws.login(user, password);
			Map<String, List<FormElement>> forms = ws.getProcessDefinitionForms(34);
			
			System.out.println("**************************************");
			for (String key : forms.keySet()) {
				System.out.println("Key:" + key);
				for (FormElement fe : forms.get(key)) {
					System.out.println("Fe:" + fe);
				}
				System.out.println("**************************************");
			}
			
			System.out.println("|||||||||||||||||||||||||||||||||||||||");
			for (TaskInstance ti : ws.findUserTaskInstances()) {
				System.out.println(ti.getName());
                System.out.println("|||||||||||||||||||||||||||||||||||||||");
            }

			
//            System.out.println(forms.get("reviewer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
