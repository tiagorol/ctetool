package br.com.ctetool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctetool.entity.Benchmark;
import br.com.ctetool.entity.Result;
import br.com.ctetool.service.ResultService;

@Controller
public class ResultController {
	
	@Autowired
	private ResultService resultService; 
	
	@RequestMapping("viewResult")
	public ModelAndView deploy(@RequestParam long id) {
		List<Result> listResul = resultService.findByBenchmark(new Benchmark(id));
		return new ModelAndView("result/resultList", "listResult", listResul);
	}

}
