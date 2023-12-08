package com.devsuperior.dscommece.services;

import com.devsuperior.dscommece.dto.ProductDTO;
import com.devsuperior.dscommece.dto.ProductMinDTO;
import com.devsuperior.dscommece.entities.Product;
import com.devsuperior.dscommece.repositories.ProductRepository;
import com.devsuperior.dscommece.services.exceptions.DataBaseException;
import com.devsuperior.dscommece.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable) {
        Page<Product> page = repository.findAll(pageable);
        return page.map(x -> new ProductMinDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O produto não existe!!!"));
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        try {
            Product entity = copyDTOtoProduct(dto);
            Product newProduct = repository.save(entity);
            return new ProductDTO(newProduct);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            if (repository.getReferenceById(id) == null) {
                throw new ResourceNotFoundException("O produto não existe!");
            }
            Product newProduct = copyDTOtoProduct(dto);
            newProduct.setId(id);

            Product productUpdate = repository.save(newProduct);
            return new ProductDTO(productUpdate);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

    private Product copyDTOtoProduct(ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        return entity;
    }
}
