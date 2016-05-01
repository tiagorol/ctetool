package br.com.ctetool.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.ctetool.entity.Configuration;

@Component
public class ConfigurationValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Configuration.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Configuration configuration = (Configuration)target;
		
		if(configuration.getWorkloads().endsWith(",")){
			configuration.setWorkloads(configuration.getWorkloads().substring(0, configuration.getWorkloads().length() - 1));
		}
		
		String[] workloads = configuration.getWorkloads().split(",");
		
		for (String workload : workloads) {
			if(!StringUtils.isNumeric(workload)){
				errors.rejectValue("workloads","invalido");
				break;
			}
		}
	}

}