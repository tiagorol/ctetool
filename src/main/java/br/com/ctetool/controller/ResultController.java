package br.com.ctetool.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.ctetool.entity.Benchmark;
import br.com.ctetool.entity.Result;
import br.com.ctetool.entity.vo.ResultVO;
import br.com.ctetool.entity.vo.Serie;
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

	@RequestMapping(value = "graphic", method = RequestMethod.GET, headers="Accept=*/*")
	@ResponseBody
	public ResultVO createNewProject() {
		
		
		
		ResultVO resultVO = new ResultVO();
		resultVO.setSeries(new ArrayList<>());
		resultVO.setCategories(new String[]{"200","400","700"});
		
		Serie serie = new Serie();
		serie.setName("T1-M");
		serie.setData(new int[]{200, 500, 900});
		
		Serie serie1 = new Serie();
		serie1.setName("T1-L");
		serie1.setData(new int[]{500, 1000, 1800});
		
		resultVO.getSeries().add(serie);
		resultVO.getSeries().add(serie1);
		
		return resultVO;
	}

}
