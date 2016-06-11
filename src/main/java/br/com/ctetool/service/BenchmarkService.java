package br.com.ctetool.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ctetool.entity.Benchmark;

@Service
@Transactional
public class BenchmarkService extends BaseService {

	public Benchmark save(Benchmark benchmark) {
		if(benchmark.getId() == null){
			return create(benchmark);
		}
		return update(benchmark);
	}

}
