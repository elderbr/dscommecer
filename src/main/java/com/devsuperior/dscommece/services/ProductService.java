package com.devsuperior.dscommece.services;

import com.devsuperior.dscommece.dto.ProductDTO;
import com.devsuperior.dscommece.entities.Product;
import com.devsuperior.dscommece.repositories.ProductRepository;
import com.devsuperior.dscommece.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> page = repository.findAll(pageable);
        return page.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        try {
            Product product = repository.findById(id).get();
            return new ProductDTO(product);
        } catch (Exception e) {
            throw new ResourceNotFoundException("O produto não existe!!!");
        }
    }

    @Transactional
    public ProductDTO insert(Product entity){
        Product product = repository.save(entity);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, Product entity){
        Product product = repository.getReferenceById(id);
        if(product == null){
            throw new ResourceNotFoundException("O produto não existe!");
        }
        entity.setId(id);
        Product productUpdate = repository.save(entity);
        return new ProductDTO(productUpdate);
    }
}
