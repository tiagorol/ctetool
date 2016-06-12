package br.com.ctetool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.ctetool.entity.Benchmark;

@Component
public class BenchmarkValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Benchmark.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Benchmark benchmark = (Benchmark)target;
		
		if(benchmark.getType() != 0 && (benchmark.getNumberInstanceLb() == null || benchmark.getNumberInstanceLb() == 0)){
			errors.rejectValue("numberInstanceLb","invalido");
			return;
		}
		
		if(benchmark.getNumberInstanceWp() == null || benchmark.getNumberInstanceWp() == 0){
			errors.rejectValue("numberInstanceWp","invalido");
			return;
		}
		
		if(benchmark.getNumberInstanceDb() == null || benchmark.getNumberInstanceDb() == 0){
			errors.rejectValue("numberInstanceDb","invalido");
		}
	}

}