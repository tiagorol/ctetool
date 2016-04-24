package br.com.ctetool.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopologyController {
	
	@RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
	
    @RequestMapping("createTopologySingle")
    public ModelAndView createTopologySingle() {
        return new ModelAndView("topologySingleForm");
    }
    
    @RequestMapping("createTopologyMulti")
    public ModelAndView createTopologyMulti() {
        return new ModelAndView("topologyMultiForm");
    }
    
}