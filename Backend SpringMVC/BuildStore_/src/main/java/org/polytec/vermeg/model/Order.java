package org.polytec.vermeg.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * This is our model class and it corresponds to Country table in database
 */
@Entity
@Table(name="orders")
public class Order{
	
	@Id
	@Column(name="id_orders")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private	int id_orders;
	@Column(name="id_user")
	private int id_user ;
	

	@Column(name="total")
	private Double total ;
	@Column(name="date")
	private Date date ;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order( int id ) {
		this.id_orders  = id ;
	}


	public Order(int id_orders, int id_user ) {
	
		this.id_orders = id_orders;
		this.id_user = id_user;
	
		
	}

	public Order(int id_orders, int id_user ,double total ) {
		this.total=total ;
		this.id_orders = id_orders;
		this.id_user = id_user;
	
		
	}




	public int getId_orders() {
		return id_orders;
	}
	public void setId_orders(int id_orders) {
		this.id_orders = id_orders;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


}