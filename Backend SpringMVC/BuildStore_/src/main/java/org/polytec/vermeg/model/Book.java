package org.polytec.vermeg.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_book")
	 int id ;
	
	@Column(name="author")
	private String  Author ;
	
	@Column(name="title")
	private String title ;
	
	@Column(name="price")
	private double  price_unit ;
	
	@Column(name="quantity")
	private int quantity ;
	
	@Column(name="date")
	private Date date ;
	

		public Book() {

}
		
		public Book(int id, String author, String title, double price_unit, int quantity) {
			super();
			this.id = id;
			Author = author;
			this.title = title;
			this.price_unit = price_unit;
			this.quantity = quantity;
		}

		public String getAuthor() {
	return Author;
}
		public void setAuthor(String auther) {
	Author = auther;
}
	public String getTitle() {
	return title;
}
	public void setTitle(String title) {
	this.title = title;
}
	public double getPrice_unit() {
	return price_unit;
}
	public void setPrice_unit(double price_unit) {
	this.price_unit = price_unit;
}
	public int getQuantity() {
	return quantity;
}
	public void setQuantity(int quantity) {
	this.quantity = quantity;
}
	public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


}
