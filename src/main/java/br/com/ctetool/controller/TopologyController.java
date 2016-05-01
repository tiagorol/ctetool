package br.com.ctetool.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopologyController {
	
    @RequestMapping("createTopologySingle")
    public ModelAndView createTopologySingle() {
        return new ModelAndView("topology/topologySingleForm");
    }
    
    @RequestMapping("createTopologyMulti")
    public ModelAndView createTopologyMulti() {
        return new ModelAndView("topology/topologyMultiForm");
    }
    
}