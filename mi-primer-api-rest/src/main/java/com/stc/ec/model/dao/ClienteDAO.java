package com.stc.ec.model.dao;

import com.stc.ec.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDAO extends CrudRepository<Cliente, Integer> {
}
