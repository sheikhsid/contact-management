package com.contact.contact.repository;

import com.contact.contact.model.ContactDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactDto, Long> {
}
