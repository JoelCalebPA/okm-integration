package com.domain.test;

import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;

import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.OKMWebservicesFactory;
import com.openkm.sdk4j.bean.form.Button;
import com.openkm.sdk4j.bean.form.FormElement;
import com.openkm.sdk4j.bean.form.Option;
import com.openkm.sdk4j.bean.form.Select;
import com.openkm.sdk4j.bean.form.TextArea;
import com.openkm.sdk4j.bean.workflow.ProcessDefinition;
import com.openkm.sdk4j.bean.workflow.TaskInstance;

public class Test2 {

	public static void main(String[] args) {
		String host = "http://localhost:9090/openkm";
		String user = "okmAdmin";
		String password = "admin";
		OKMWebservices ws = OKMWebservicesFactory.getInstance(host);
		DozerBeanMapper mapper = new DozerBeanMapper();
		try {
			ws.login(user, password);
			
			long pdId = 0;
			
			for (ProcessDefinition pd : ws.findAllProcessDefinitions()) {
				pdId = pd.getId();
            }
			
			Map<String, List<FormElement>> forms = ws.getProcessDefinitionForms(pdId);
			
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
				System.out.println(ti.getName() + " --> " + ti.getProcessInstance().getVariables());
				System.out.println("formElements:");
				for (FormElement fe : ws.getProcessDefinitionForms(ti.getProcessDefinitionId()).get(ti.getName())) {
					if (fe instanceof Select) {
						System.out.println("Fe:" + ((Select)fe).getValue());
					} else if (fe instanceof Button) {
						System.out.println("Fe:" + ((Button)fe).getLabel());
					} else if (fe instanceof TextArea) {
						System.out.println("Fe:"+ ((TextArea)fe).getData());
					} else if (fe instanceof Select) {
						System.out.println("Fe:" + ((Select)fe).getValue());
					}
					System.out.println("Fe:" + fe);
				}
                System.out.println("|||||||||||||||||||||||||||||||||||||||");
            }

			
//            System.out.println(forms.get("reviewer"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
