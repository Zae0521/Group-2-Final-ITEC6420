package edu.mga.G2_Final.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;

@Table(name = "inventory")
@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foreign_car_id")
	private Car carObject = new Car();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foreign_branch_id")
	private Branch branchObject = new Branch();

	@Column(name = "quantity")
	private Integer quantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarId() {
		return carObject.getId();
	}

	public void setCarId(Integer carId) {
		this.carObject.setId(carId);
	}

	public Integer getBranchId() {
		return branchObject.getId();
	}
	
	public void setBranchId(Integer branchId) {
		this.branchObject.setId(branchId);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
