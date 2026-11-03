package com.gyneobs.gyneobs_backend.services;

import java.util.List;

import com.gyneobs.gyneobs_backend.entities.Appointment;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);

    List<Appointment> listAppointment();
    Appointment updateAppointment(Appointment appointment, Long appointmentId);
    void deleteAppointmentById(Long appointmentId);
}
