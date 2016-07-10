package br.com.ctetool.entity.vo;

public class GraphicVO {
	
	private Integer workload;
	
	private Integer responseTime;

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
