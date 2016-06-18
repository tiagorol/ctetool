package br.com.ctetool.service;

import org.springframework.stereotype.Service;

import br.com.ctetool.entity.Benchmark;

@Service
public class ResultService extends BaseService {
	
	public Integer calculatingPercentage(Benchmark benchmark){
		int totalRecords = count(benchmark);
		int totalExpected = benchmark.getRounds() * benchmark.getWorkloads().split(",").length; 
		return totalRecords * 100 / totalExpected;
	}

	private int count(Benchmark benchmark) {
		return ((Number)sessionFactory.getCurrentSession()
					  				  .createQuery("SELECT COUNT(id) FROM Result WHERE benchmark = :benchmark")
					  				  .setParameter("benchmark", benchmark)
					  				  .uniqueResult())
					  				  .intValue();
	}

}
