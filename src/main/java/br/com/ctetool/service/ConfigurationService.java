package br.com.ctetool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ctetool.entity.Configuration;

@Service
@Transactional
public class ConfigurationService extends BaseService {

	public Configuration save(Configuration configuration) {
		if(configuration.getId() == null){
			return create(configuration);
		}
		return update(configuration);
	}

	public Configuration findConfiguration() {
		List<Configuration> listConfiguration = fetchAll(Configuration.class);
		return !listConfiguration.isEmpty() ? listConfiguration.get(0) : null;
	}

}
