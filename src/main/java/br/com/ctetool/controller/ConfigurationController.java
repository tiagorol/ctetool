package br.com.ctetool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctetool.entity.Configuration;

@Controller
public class ConfigurationController {

	@RequestMapping("createConfiguration")
	public ModelAndView createConfiguration(@ModelAttribute Configuration configuration) {
		return new ModelAndView("configurationForm");
	}

	@RequestMapping("saveConfiguration")
	public ModelAndView saveConfiguration(@ModelAttribute Configuration configuration) {
		System.out.println(configuration);
		return createConfiguration(configuration);
	}

}
