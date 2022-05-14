package com.haulmont.spring.data.demo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ForesterRepository extends CrudRepository<Forester, Long> {

    List<Forester> findByLastNameIgnoreCase(@NonNull String lastName);

    List<Forester> findByReserve(Reserve reserve);


}
