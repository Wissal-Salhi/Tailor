package com.project.tailor.service;

import java.util.List;

import com.project.tailor.entity.Brand;
import com.project.tailor.exceptionhandeling.BadRequestException;



public interface BrandService {

	public List<Brand> findAll();
	
	public Brand findById(Integer id) throws BadRequestException;
	
	public void save(Brand brand);
	
	void update(Integer brandId, Brand brand) throws BadRequestException;
	
	public Brand deleteById(Integer id) throws BadRequestException;


	
}
