package org.example.dao;

import org.example.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDao extends CrudRepository<UserAccount, Long> {
    UserAccount findByUserId(Long userId);
}