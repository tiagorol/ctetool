package br.com.ctetool.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctetool.entity.Benchmark;
import br.com.ctetool.entity.Configuration;
import br.com.ctetool.service.BenchmarkService;
import br.com.ctetool.service.ConfigurationService;
import br.com.ctetool.validator.BenchmarkValidator;

@Controller
public class TopologyController {
	
	private static final String[] FIELD_VALIDADE = new String[]{"name", "numberInstanceLb", "numberInstanceWp", "numberInstanceDb"};
	
	@Autowired
	private BenchmarkValidator benchmarkValidator; 
	
	@Autowired
	private BenchmarkService benchmarkService; 
	
	@Autowired
	private ConfigurationService configurationService;
	
	@RequestMapping(value = {"listBenchmark"})
    public ModelAndView listBenchmark() {
        List<Benchmark> listBenchmark = benchmarkService.fetchAll();
        return new ModelAndView("topology/topologyList", "listBenchmark", listBenchmark);
    }
	
   @RequestMapping("deleteBenchmark")
    public ModelAndView deleteBenchmark(@RequestParam long id) {
	   benchmarkService.delete(new Benchmark(id));
       return new ModelAndView("redirect:listBenchmark");
    }
	
    @RequestMapping("createTopologySingle")
    public ModelAndView createTopologySingle(@ModelAttribute Benchmark benchmark) {
        return new ModelAndView("topology/topologySingleForm", "benchmarkObject", new Benchmark());
    }
    
    @RequestMapping("createTopologyMulti")
    public ModelAndView createTopologyMulti(@ModelAttribute Benchmark benchmark) {
        return new ModelAndView("topology/topologyMultiForm", "benchmarkObject", new Benchmark());
    }
    
    @RequestMapping("saveBenchmark")
	public ModelAndView saveBenchmark(@Valid Benchmark benchmark, BindingResult bindingResult) {
    	benchmarkValidator.validate(benchmark, bindingResult);
    	
    	if(hasFieldErrors(bindingResult)){
    		return new ModelAndView(getFrom(benchmark));
    	}
    	
    	Configuration configuration = configurationService.findConfiguration();
    	benchmark = new Benchmark(benchmark, configuration);
    	benchmarkService.save(benchmark);
    	return new ModelAndView("redirect:index");
	}

	private boolean hasFieldErrors(BindingResult bindingResult) {
		for (String field : FIELD_VALIDADE) {
			if(bindingResult.hasFieldErrors(field)){
				return true;
			}
		}
		return false;
	}

	private String getFrom(Benchmark benchmark) {
		return benchmark.getType() == 0 ?  "topology/topologySingleForm" :  "topology/topologyMultiForm";
	}
    
}