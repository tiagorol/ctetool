package br.com.ctetool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctetool.entity.Configuration;
import br.com.ctetool.service.ConfigurationService;
import br.com.ctetool.validator.ConfigurationValidator;

@Controller
public class ConfigurationController {

	@Autowired
	private ConfigurationValidator configurationValidator;

	@Autowired
	private ConfigurationService configurationService;

	@RequestMapping("createConfiguration")
	public ModelAndView createConfiguration(@ModelAttribute Configuration configuration) {
		configuration = configurationService.findConfiguration();
		return new ModelAndView("configuration/configurationForm", "configurationObject", configuration);
	}

	@RequestMapping("saveConfiguration")
	public ModelAndView saveConfiguration(@Valid Configuration configuration, BindingResult bindingResult) {

		configurationValidator.validate(configuration, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("configuration/configurationForm");
		}

		configurationService.save(configuration);
		return new ModelAndView("redirect:index");
	}

}
