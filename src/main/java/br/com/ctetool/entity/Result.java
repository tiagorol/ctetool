package br.com.ctetool.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "result")
public class Result implements Serializable{
	
	private static final long serialVersionUID = -8652025339282526392L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@Column(name = "date_test")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTest;
	
	@NotNull
	@JoinColumn(name = "id_benchmark")
	@ManyToOne(fetch = FetchType.LAZY)
	private Benchmark benchmark;
	
	@NotNull
	private Integer execution;

	@NotNull
	private Integer workload;
	
	@NotNull
	@Column(name = "number_request")
	private Integer numberRequest;
	
	@NotNull
	@Column(name = "number_request_ok")
	private Integer numberRequestOK;
	
	@NotNull
	@Column(name = "number_request_ko")
	private Integer numberRequestKO;
	
	@NotNull
	@Column(name = "min_response_time")
	private Integer minResponseTime;
	
	@NotNull
	@Column(name = "max_response_time")
	private Integer maxResponseTime;
	
	@NotNull
	@Column(name = "mean_response_time")
	private Integer meanResponseTime;
	
	@Column(name = "mean_memory_use")
	private Double meanMemoryUse;
	
	@Column(name = "mean_cpu_use")
	private Double meanCpuUse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateTest() {
		return dateTest;
	}

	public void setDateTest(Date dateTest) {
		this.dateTest = dateTest;
	}

	public Benchmark getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(Benchmark benchmark) {
		this.benchmark = benchmark;
	}

	public Integer getExecution() {
		return execution;
	}

	public void setExecution(Integer execution) {
		this.execution = execution;
	}

	public Integer getWorkload() {
		return workload;
	}

	public void setWorkload(Integer workload) {
		this.workload = workload;
	}

	public Integer getNumberRequest() {
		return numberRequest;
	}

	public void setNumberRequest(Integer numberRequest) {
		this.numberRequest = numberRequest;
	}

	public Integer getNumberRequestOK() {
		return numberRequestOK;
	}

	public void setNumberRequestOK(Integer numberRequestOK) {
		this.numberRequestOK = numberRequestOK;
	}

	public Integer getNumberRequestKO() {
		return numberRequestKO;
	}

	public void setNumberRequestKO(Integer numberRequestKO) {
		this.numberRequestKO = numberRequestKO;
	}

	public Integer getMinResponseTime() {
		return minResponseTime;
	}

	public void setMinResponseTime(Integer minResponseTime) {
		this.minResponseTime = minResponseTime;
	}

	public Integer getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(Integer maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public Integer getMeanResponseTime() {
		return meanResponseTime;
	}

	public void setMeanResponseTime(Integer meanResponseTime) {
		this.meanResponseTime = meanResponseTime;
	}

	public Double getMeanMemoryUse() {
		return meanMemoryUse;
	}

	public void setMeanMemoryUse(Double meanMemoryUse) {
		this.meanMemoryUse = meanMemoryUse;
	}

	public Double getMeanCpuUse() {
		return meanCpuUse;
	}

	public void setMeanCpuUse(Double meanCpuUse) {
		this.meanCpuUse = meanCpuUse;
	}
	
}
