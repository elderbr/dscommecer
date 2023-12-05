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
    public ProductDTO insert(ProductDTO dto){
        Product entity = copyDTOtoProduct(dto);
        Product newProduct = repository.save(entity);
        return new ProductDTO(newProduct);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        if(repository.getReferenceById(id) == null){
            throw new ResourceNotFoundException("O produto não existe!");
        }
        Product newProduct = copyDTOtoProduct(dto);
        newProduct.setId(id);

        Product productUpdate = repository.save(newProduct);
        return new ProductDTO(productUpdate);
    }

    private Product copyDTOtoProduct(ProductDTO dto){
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        return entity;
    }
}
