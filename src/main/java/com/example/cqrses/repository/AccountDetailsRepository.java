package com.example.cqrses.repository;

import com.example.cqrses.entity.AccountDetailsQueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountDetailsRepository extends
    CrudRepository<AccountDetailsQueryEntity, String> {

}
