package br.com.ctetool.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mchange.io.FileUtils;

import br.com.ctetool.entity.Benchmark;
import br.com.ctetool.entity.Configuration;
import br.com.ctetool.enumeration.Status;
import br.com.ctetool.enumeration.TypeTopology;
import br.com.ctetool.service.BenchmarkService;
import br.com.ctetool.service.ConfigurationService;

@Controller
public class DeployController {
	
	private static final String SO_WINDOWS = "WINDOWS";

	@Value("${path.file.inputs}")
	private String pathFileInputs;
	
	@Value("${path.file.script}")
	private String pathFileScript;
	
	@Value("${name.file.script.single}")
	private String nameFileScriptSingle;
	
	@Value("${name.file.script.multi}")
	private String nameFileScriptMulti;
	
	@Autowired
	private BenchmarkService benchmarkService;

	@Autowired
	private ConfigurationService configurationService; 

	@RequestMapping("deploy")
	public ModelAndView deploy(@RequestParam long id) throws IOException{
		Benchmark benchmark = benchmarkService.fetchById(id);
		
		ClassLoader classLoader = getClass().getClassLoader();
		File inputs = new File(classLoader.getResource("templateInputs.yaml").getFile());
		String contentsAsString = FileUtils.getContentsAsString(inputs);
		contentsAsString = replaceValues(contentsAsString, benchmark);
		FileCopyUtils.copy(contentsAsString.getBytes(), new File(pathFileInputs));
		benchmark.setStatus(Status.WAITING);
		benchmarkService.updateStatus(benchmark);
		run(benchmark);
		return new ModelAndView("redirect:/listBenchmark");
	}

	private String replaceValues(String contentsAsString, Benchmark benchmark) {
		
		Configuration configuration = configurationService.findConfiguration();
		
		contentsAsString = contentsAsString.replace("#{image}", configuration.getImage());
		contentsAsString = contentsAsString.replace("#{size}", benchmark.getSize());
		contentsAsString = contentsAsString.replace("#{size_wordpress}", benchmark.getSizeWordpress());
		contentsAsString = contentsAsString.replace("#{agent_user}", configuration.getAgentUser());
		contentsAsString = contentsAsString.replace("#{host_crawler}", configuration.getHostCrawler());
		contentsAsString = contentsAsString.replace("#{benchmark_id}", benchmark.getId().toString());
		contentsAsString = contentsAsString.replace("#{rounds}", benchmark.getRounds().toString());
		contentsAsString = contentsAsString.replace("#{workloads}", benchmark.getWorkloads());
		
		return contentsAsString;
	}
	
	private void run(Benchmark benchmark) throws IOException{
		if(isWindows()){
			Runtime.getRuntime().exec("cmd /c start " + pathFileScript + getNameFileScript(benchmark));
		}else{
			ProcessBuilder pb = new ProcessBuilder(pathFileScript + getNameFileScript(benchmark));
			pb.start();
		}
	}

	private String getNameFileScript(Benchmark benchmark) {
		return benchmark.getTypeTopology().equals(TypeTopology.SINGLE) ? nameFileScriptSingle : nameFileScriptMulti;
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toUpperCase().contains(SO_WINDOWS);
	}

}
