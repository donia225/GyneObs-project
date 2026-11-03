package com.gyneobs.gyneobs_backend.services;

import java.util.List;
import com.gyneobs.gyneobs_backend.entities.Appointment;
import com.gyneobs.gyneobs_backend.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service 
public class AppointmentServiceImpl implements AppointmentService {
private final AppointmentRepository appointmentRepository;

    AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public Appointment createAppointment(Appointment appointment) {
        return  appointmentRepository.save(appointment);
      
    }

    @Override
    public List<Appointment> listAppointment() {
      return (List<Appointment>)
        appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, Long appointmentId) {
             Appointment appointmentDB = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (appointment.getAppointmentDateTime() != null) {
            appointmentDB.setAppointmentDateTime(
                    appointment.getAppointmentDateTime()
            );
        }

        if (appointment.getStatus() != null) {
            appointmentDB.setStatus(appointment.getStatus());
        }

        return appointmentRepository.save(appointmentDB);
    }
            
    

    @Override
    public void deleteAppointmentById(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

 

   
  



}

