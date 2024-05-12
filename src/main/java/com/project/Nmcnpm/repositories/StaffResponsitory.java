package com.project.Nmcnpm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Nmcnpm.models.Staff;

public interface StaffResponsitory extends JpaRepository<Staff, Integer> {
    
}
