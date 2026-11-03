package com.gyneobs.gyneobs_backend.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.gyneobs.gyneobs_backend.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


}
