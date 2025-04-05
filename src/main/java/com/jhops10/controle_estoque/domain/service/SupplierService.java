package com.jhops10.controle_estoque.domain.service;

import com.jhops10.controle_estoque.api.dto.SupplierDTO;
import com.jhops10.controle_estoque.domain.model.Supplier;
import com.jhops10.controle_estoque.domain.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    public Supplier registerSupplier(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.name());
        supplier.setEmail(dto.email());

        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Erro! Fornecedor com id " + id + " não encontrado."));
    }

    public Supplier getSupplierByEmail(String email) {
        return supplierRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado nenhum fornecedor com o email: " + email));
    }

    public void deleteSupplierById(Long id) {
        getSupplierById(id);
        supplierRepository.deleteById(id);
    }
}
