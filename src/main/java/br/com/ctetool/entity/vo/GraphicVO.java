package br.com.ctetool.entity.vo;

public class GraphicVO {
	
	private Long idBenchmark;
	
	private Integer workload;
	
	private Integer responseTime;
	
	public Long getIdBenchmark() {
		return idBenchmark;
	}

	public void setIdBenchmark(Long idBenchmark) {
		this.idBenchmark = idBenchmark;
	}

	public Integer getWorkload() {
		return workload;
	}

	public void setWorkload(Integer workload) {
		this.workload = workload;
	}

	public Integer getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Number responseTime) {
		if(responseTime != null){
			this.responseTime = responseTime.intValue();
		}
	}

}
