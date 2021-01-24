package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsdeliver.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dtos.ProductDTO;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}

	@Transactional
    public ProductDTO insert(ProductDTO dto) {
    	Product obj = new Product().fromDto(dto);
    	obj = repository.save(obj);

    	return new ProductDTO(obj);
	}

	@Transactional
	public ProductDTO update(ProductDTO dto) throws ObjectNotFoundException {
		Product entity = repository.findById(dto.getId()).orElseThrow(
				() -> new ObjectNotFoundException("Entity not found: " + dto.getId() + " " + Product.class.getName()));
		updateData(dto, entity);
		entity = repository.save(entity);

		return new ProductDTO(entity);
	}

	private void updateData(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setDescription(dto.getDescription());
		entity.setImageUri(dto.getImageUri());
	}
}
