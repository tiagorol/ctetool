package br.com.ctetool.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ctetool.entity.Benchmark;
import br.com.ctetool.enumeration.Status;

@Service
@Transactional
public class BenchmarkService extends BaseService {
	
	@Autowired
	private ResultService resultService;

	public Benchmark save(Benchmark benchmark) {
		if(benchmark.getId() == null){
			return create(benchmark);
		}
		return update(benchmark);
	}
	
	public List<Benchmark> fetchAll() {
		List<Benchmark> listBenchmark =  super.fetchAll(Benchmark.class);
		for (Benchmark benchmark : listBenchmark) {
			if(benchmark.getStatus().equals(Status.WAITING) || benchmark.getStatus().equals(Status.EXECUTION)){
				Double percentage = resultService.calculatingPercentage(benchmark);
				benchmark.setPercentage(percentage);
				if(percentage == 100){
					benchmark.setStatus(Status.FINALIZED);
					updateStatus(benchmark);
				}else if(percentage != 0){
					benchmark.setStatus(Status.EXECUTION);
					updateStatus(benchmark);
				}
			}
		}
		return listBenchmark;
	}
	
	@Override
	public <T> void delete(T entity) {
		super.delete(entity);
	}
	
	public Benchmark fetchById(Serializable id) {
		return super.fetchById(id, Benchmark.class);
	}

	public void updateStatus(Benchmark benchmark) {
		sessionFactory.getCurrentSession()
				  	  .createQuery("UPDATE Benchmark SET status = :status")
				  	  .setParameter("status", benchmark.getStatus())
				  	  .executeUpdate();
	}

}
