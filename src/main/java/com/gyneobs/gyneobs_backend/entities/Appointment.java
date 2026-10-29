package com.gyneobs.gyneobs_backend.entities;
import java.time.LocalDateTime;

import com.gyneobs.gyneobs_backend.enums.AppointmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(LocalDateTime appointmentDateTime, AppointmentStatus status) {
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
    }

    public Appointment(Long id, LocalDateTime appointmentDateTime, AppointmentStatus status) {
        this.id = id;
        this.appointmentDateTime = appointmentDateTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    
}
