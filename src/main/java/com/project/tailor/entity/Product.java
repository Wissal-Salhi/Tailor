package com.project.tailor.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.tailor.dto.ProductRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name",unique=true)
	@NotBlank(message = "The name can't be null")
	private String name;
	
	@Column(name="code",unique=true)
	private long code;
	
	@Column
	private String description;
	
	@Column
	private String photos;
	
	@Column
	private String color;
	
	@Column
	private String size;
	
	@Column
	private String fabric;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "products_categories",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id",
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id",
                            nullable = false)})
	private List<Category> categories= new ArrayList<>();
	
	
	public Product(ProductRequestDTO product, Brand brand, List<Category> categories) {
		this.name=product.getName();
		this.code=product.getCode();
		this.description=product.getDescription();
		this.photos=product.getPhotos();
		this.color=product.getColor();
		this.size=product.getSize();
		this.fabric=product.getFabric();
		this.brand=brand;
		this.categories.addAll(categories);
		System.out.println("heeeeeeeeeeere"+ this.categories);
	}

}
