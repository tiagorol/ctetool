package br.com.ctetool.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jboss.logging.Logger;
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
	
	private static final Logger logger = Logger.getLogger(DeployController.class);

	private static final String SO_WINDOWS = "WINDOWS";
	private static final String TEMPLATE_INPUTS = "template-inputs.yaml";
	private static final String TEMPLATE_BLUEPRINT = "template-wordpress-multi-blueprint.yaml";

	@Value("${path.file.inputs}")
	private String pathFileInputs;
	
	@Value("${path.file.blueprint}")
	private String pathFileBlueprint;

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
		logger.info("Buscando Benchmark com Id: " + id + "...");
		Benchmark benchmark = benchmarkService.fetchById(id);
		
		changeInputs(benchmark);
		changeBlueprint(benchmark);
		
		logger.info("Atualizando status do Benchmark...");
		benchmark.setStatus(Status.WAITING);
		benchmarkService.updateStatus(benchmark);
		run(benchmark);
		return new ModelAndView("redirect:/listBenchmark");
	}
	
	private void changeBlueprint(Benchmark benchmark) throws IOException {
		logger.info("Alterando blueprint.yaml");
		ClassLoader classLoader = getClass().getClassLoader();
		File inputs = new File(classLoader.getResource(TEMPLATE_BLUEPRINT).getFile());
		String contentsAsString = FileUtils.getContentsAsString(inputs);
		contentsAsString = replaceValuesBluePrint(contentsAsString, benchmark);
		FileCopyUtils.copy(contentsAsString.getBytes(), new File(pathFileBlueprint));
	}

	private void changeInputs(Benchmark benchmark) throws IOException {
		logger.info("Alterando inputs.yaml");
		ClassLoader classLoader = getClass().getClassLoader();
		File inputs = new File(classLoader.getResource(TEMPLATE_INPUTS).getFile());
		String contentsAsString = FileUtils.getContentsAsString(inputs);
		contentsAsString = replaceValuesInputs(contentsAsString, benchmark);
		FileCopyUtils.copy(contentsAsString.getBytes(), new File(pathFileInputs));
	}
	
	private String replaceValuesBluePrint(String contentsAsString, Benchmark benchmark) {
		contentsAsString = contentsAsString.replace("#{instances_deploy}", benchmark.getNumberInstanceWp().toString());
		
		logger.info("Conteudo blueprint.yaml");
		logger.info(contentsAsString);
		return contentsAsString;
	}

	private String replaceValuesInputs(String contentsAsString, Benchmark benchmark) {
		
		Configuration configuration = configurationService.findConfiguration();
		
		contentsAsString = contentsAsString.replace("#{image}", configuration.getImage());
		contentsAsString = contentsAsString.replace("#{size}", benchmark.getSize());
		contentsAsString = contentsAsString.replace("#{size_wordpress}", benchmark.getSizeWordpress());
		contentsAsString = contentsAsString.replace("#{agent_user}", configuration.getAgentUser());
		contentsAsString = contentsAsString.replace("#{host_crawler}", configuration.getHostCrawler());
		contentsAsString = contentsAsString.replace("#{benchmark_id}", benchmark.getId().toString());
		contentsAsString = contentsAsString.replace("#{rounds}", benchmark.getRounds().toString());
		contentsAsString = contentsAsString.replace("#{workloads}", benchmark.getWorkloads());
		
		logger.info("Conteudo inputs.yaml");
		logger.info(contentsAsString);
		return contentsAsString;
	}
	
	private void run(Benchmark benchmark) throws IOException{
		logger.info("Inicio da execucao do Script...");
		if(isWindows()){
			logger.info("SO Windows...");
			Runtime.getRuntime().exec("cmd /c start " + pathFileScript + getNameFileScript(benchmark));
		}else{
			logger.info("SO Linux...");
			ProcessBuilder pb = new ProcessBuilder(pathFileScript + getNameFileScript(benchmark)).inheritIO();
			logConsole(pb);
		}
	}
	
	private void logConsole(ProcessBuilder process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.start().getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		String result = builder.toString();
		logger.info(result);
	}

	private String getNameFileScript(Benchmark benchmark) {
		return benchmark.getTypeTopology().equals(TypeTopology.SINGLE) ? nameFileScriptSingle : nameFileScriptMulti;
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toUpperCase().contains(SO_WINDOWS);
	}

}
