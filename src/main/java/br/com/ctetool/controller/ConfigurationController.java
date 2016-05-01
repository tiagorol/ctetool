package br.com.ctetool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctetool.entity.Configuration;
import br.com.ctetool.service.ConfigurationService;

@Controller
public class ConfigurationController {
	
	@Autowired
	private ConfigurationService configurationService;

	@RequestMapping("createConfiguration")
	public ModelAndView createConfiguration(@ModelAttribute Configuration configuration) {
		configuration = configurationService.findConfiguration();
		return new ModelAndView("configurationForm", "configurationObject", configuration);
	}

	@RequestMapping("saveConfiguration")
	public ModelAndView saveConfiguration(@ModelAttribute Configuration configuration) {
		configurationService.save(configuration);
		return new ModelAndView("redirect:index");
	}

}
