package br.com.ctetool.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ctetool.enumeration.Status;
import br.com.ctetool.enumeration.TypeTopology;

@Entity
public class Benchmark implements Serializable {

	private static final long serialVersionUID = 6649533005740029993L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String name;

	@NotNull
	@Column(name = "type_topology")
	@Enumerated(EnumType.ORDINAL)
	private TypeTopology typeTopology;
	
	@NotNull
	@Column(name = "number_instance_wp")
	private Integer numberInstanceWp;
	
	@NotNull
	@Column(name = "number_instance_db")
	private Integer numberInstanceDb;
	
	@NotNull
	@Column(name = "number_instance_lb")
	private Integer numberInstanceLb;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String size;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "size_wordpress")
	private String sizeWordpress;
	
	@NotNull
	private Integer rounds;
	
	@NotNull
	@Size(min = 2, max = 40)
	private String workloads;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@Transient
	private Integer type;
	
	@Transient
	private Integer percentage;
	
	public Benchmark() {
		super();
		this.status = Status.CREATED;
	}

	public Benchmark(long id) {
		this();
		this.id = id;
	}
	
	public Benchmark(Benchmark benchmark, Configuration configuration) {
		this();
		this.numberInstanceLb = benchmark.getNumberInstanceLb();
		this.numberInstanceWp = benchmark.getNumberInstanceWp();
		this.numberInstanceDb = benchmark.getNumberInstanceDb();
		this.name = benchmark.getName();
		this.rounds = configuration.getRounds();
		this.workloads = configuration.getWorkloads();
		this.size = configuration.getSize();
		this.sizeWordpress = configuration.getSizeWordpress();
		if(benchmark.type == 0){
			this.typeTopology = TypeTopology.SINGLE;
		}else{
			this.typeTopology = TypeTopology.MULTI;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypeTopology getTypeTopology() {
		return typeTopology;
	}

	public void setTypeTopology(TypeTopology typeTopology) {
		this.typeTopology = typeTopology;
	}

	public Integer getNumberInstanceWp() {
		return numberInstanceWp;
	}

	public void setNumberInstanceWp(Integer numberInstanceWp) {
		this.numberInstanceWp = numberInstanceWp;
	}

	public Integer getNumberInstanceDb() {
		return numberInstanceDb;
	}

	public void setNumberInstanceDb(Integer numberInstanceDb) {
		this.numberInstanceDb = numberInstanceDb;
	}

	public Integer getNumberInstanceLb() {
		return numberInstanceLb;
	}

	public void setNumberInstanceLb(Integer numberInstanceLb) {
		this.numberInstanceLb = numberInstanceLb;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeWordpress() {
		return sizeWordpress;
	}

	public void setSizeWordpress(String sizeWordpress) {
		this.sizeWordpress = sizeWordpress;
	}

	public Integer getRounds() {
		return rounds;
	}

	public void setRounds(Integer rounds) {
		this.rounds = rounds;
	}

	public String getWorkloads() {
		return workloads;
	}

	public void setWorkloads(String workloads) {
		this.workloads = workloads;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

}
