package br.com.ctetool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import br.com.ctetool.entity.Benchmark;
import br.com.ctetool.entity.Result;
import br.com.ctetool.entity.vo.GraphicVO;

@Service
@Transactional
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

	@SuppressWarnings("unchecked")
	public List<Result> findByBenchmark(Benchmark benchmark) {
		return sessionFactory.getCurrentSession()
				  			 .createQuery("SELECT res FROM Result res WHERE res.benchmark = :benchmark")
				  			 .setParameter("benchmark", benchmark)
				  			 .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<GraphicVO> findResultGraphic(Benchmark benchmark) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT res.workload as workload, AVG(res.meanResponseTime) as responseTime ");
		sql.append("FROM Result res ");
		sql.append("INNER JOIN res.benchmark ben ");
		sql.append("WHERE ben = :benchmark ");
		sql.append("GROUP BY ben.id, res.workload");
		
		return sessionFactory.getCurrentSession()
				  			 .createQuery(sql.toString())
				  			 .setParameter("benchmark", benchmark)
				  			 .setResultTransformer(Transformers.aliasToBean(GraphicVO.class))
				  			 .list();
	}

}

