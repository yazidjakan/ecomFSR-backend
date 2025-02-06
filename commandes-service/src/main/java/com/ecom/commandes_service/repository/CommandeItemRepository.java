package com.ecom.commandes_service.repository;

import com.ecom.commandes_service.entity.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeItemRepository extends JpaRepository<CommandeItem, Long> {
}
