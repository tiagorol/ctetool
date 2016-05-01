package br.com.ctetool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"index", "/"})
	public ModelAndView getAllEmployees() {
		return new ModelAndView("index");
	}

}
