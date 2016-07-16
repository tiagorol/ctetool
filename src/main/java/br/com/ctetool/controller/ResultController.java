package br.com.ctetool.controller;

import java.util.ArrayList;
import java.util.Collections;
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

	@RequestMapping("viewResultSingle")
	public ModelAndView viewResultSingle(@RequestParam long id) {
		Benchmark benchmark = new Benchmark(id);
		List<Result> listResul = resultService.findByBenchmark(new ArrayList<>(Collections.singleton(benchmark)));
		return new ModelAndView("result/resultList", "listResult", listResul);
	}
	
	@RequestMapping("viewResultMulti")
	public ModelAndView viewResultMulti(@RequestParam String id) {
		List<Benchmark> listBenchmark = findListBenchmark(id.split(","));
		List<Result> listResult = resultService.findByBenchmark(listBenchmark);
		return new ModelAndView("result/resultList", "listResult", listResult);
	}
	
	@RequestMapping(value = "graphic", method = RequestMethod.GET, headers="Accept=*/*")
	@ResponseBody
	public ResultVO findGraphic(@RequestParam String id) {
		List<Benchmark> listBenchmark = findListBenchmark(id.split(","));
		List<GraphicVO> resultGraphic = resultService.findResultGraphic(listBenchmark);
		return criateResultVO(resultGraphic, listBenchmark);
	}

	private List<Benchmark> findListBenchmark(String[] ids) {
		List<Benchmark> listBenchmark = new ArrayList<>();
		for (String id : ids) {
			listBenchmark.add(benchmarkService.fetchById(Long.parseLong(id)));
		}
		return listBenchmark;
	}

	private ResultVO criateResultVO(List<GraphicVO> resultGraphic, List<Benchmark> listBenchmark) {
		ResultVO resultVO = new ResultVO(resultGraphic);
		
		List<Serie> listSerie = new ArrayList<>();
		
		for (Benchmark benchmark : listBenchmark) {
			listSerie.add(new Serie(benchmark.getId(), benchmark.getName(), resultVO.getCategories().length));
		}
		
		for (int i = 0; i < resultGraphic.size(); i++) {
			Serie serie = getSerie(resultGraphic.get(i).getIdBenchmark(), listSerie);
			serie.getData()[getIndexOf(resultGraphic.get(i), resultVO.getCategories())] = resultGraphic.get(i).getResponseTime();
		}
		
		resultVO.getSeries().addAll(listSerie);
		return resultVO;
	}

	private int getIndexOf(GraphicVO graphicVO, String[] categories) {
		for (int i = 0; i < categories.length; i++) {
			if(String.valueOf(graphicVO.getWorkload()).equals(categories[i])){
				return i;
			}
		}
		return 0;
	}

	private Serie getSerie(Long idBenchmark, List<Serie> listSerie) {
		for (Serie serie : listSerie) {
			if(serie.getId().equals(idBenchmark)){
				return serie;
			}
		}
		return null;
	}

}
