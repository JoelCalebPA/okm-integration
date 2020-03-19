package com.domain.controller.workflow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.service.WorkflowService;
import com.openkm.sdk4j.bean.form.FormElement;
import com.openkm.sdk4j.bean.form.Select;
import com.openkm.sdk4j.bean.workflow.TaskInstance;

@Controller
@RequestMapping("/features/workflow")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;

	@GetMapping
	public String getUserTasks(Model model) {
		try {
			model.addAttribute("tasks", workflowService.getUserTasks());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "features/workflow";
	}

	@GetMapping("/task")
	public String getTaskForm(@RequestParam(value = "tiId", required = false) Long tiId, String form, Model model) {
		try {
			TaskInstance instance = workflowService.getTaskInstance(tiId);
			List<FormElement> fes = workflowService.getTaskForm(instance.getProcessDefinitionId(), instance.getName());
			model.addAttribute("fields", fes);
			model.addAttribute("tasks", workflowService.getUserTasks());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "features/workflow/task";
	}

	@GetMapping("/start")
	public String startProcess(HttpServletRequest request) {
		return "";
	}

}
