package br.com.ctetool.controller;

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
import br.com.ctetool.entity.vo.GraphicVO;
import br.com.ctetool.entity.vo.ResultVO;
import br.com.ctetool.entity.vo.Serie;
import br.com.ctetool.service.BenchmarkService;
import br.com.ctetool.service.ResultService;

@Controller
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private BenchmarkService benchmarkService;

	@RequestMapping("viewResult")
	public ModelAndView deploy(@RequestParam long id) {
		List<Result> listResul = resultService.findByBenchmark(new Benchmark(id));
		return new ModelAndView("result/resultList", "listResult", listResul);
	}

	@RequestMapping(value = "graphic", method = RequestMethod.GET, headers="Accept=*/*")
	@ResponseBody
	public ResultVO findGraphic(@RequestParam long id) {
		Benchmark benchmark = benchmarkService.fetchById(id);
		List<GraphicVO> resultGraphic = resultService.findResultGraphic(benchmark);
		return criateResultVO(resultGraphic, benchmark);
	}

	private ResultVO criateResultVO(List<GraphicVO> resultGraphic, Benchmark benchmark) {
		ResultVO resultVO = new ResultVO(resultGraphic.size());
		Serie serie = new Serie(benchmark.getName(), resultGraphic.size());
		
		for (int i = 0; i < resultGraphic.size(); i++) {
			serie.getData()[i] = resultGraphic.get(i).getResponseTime();
			resultVO.getCategories()[i] = resultGraphic.get(i).getWorkload().toString();
		}
		
		resultVO.getSeries().add(serie);
		
		return resultVO;
	}

}
