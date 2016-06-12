package br.com.ctetool.service;

import java.util.List;

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
	
	public List<Benchmark> fetchAll() {
		return super.fetchAll(Benchmark.class);
	}
	
	@Override
	public <T> void delete(T entity) {
		super.delete(entity);
	}

}
