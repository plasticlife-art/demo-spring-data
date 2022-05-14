package com.haulmont.spring.data.demo.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveRepository extends CrudRepository<Reserve, Long> {

    @Query("select r from reserve r where r.name = :name")
    List<Reserve> findByName(@Param("name") String name);

}