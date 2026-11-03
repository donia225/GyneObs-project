package com.gyneobs.gyneobs_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.gyneobs.gyneobs_backend.entities.Appointment;
import com.gyneobs.gyneobs_backend.services.AppointmentServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController 
public class AppointmentController {

    private final AppointmentServiceImpl appointmentServiceImpl;

    AppointmentController(AppointmentServiceImpl appointmentServiceImpl) {
        this.appointmentServiceImpl = appointmentServiceImpl;
    }

@PostMapping("/appointment")
public Appointment saveAppointment(@RequestBody Appointment appointment) {
    return appointmentServiceImpl.createAppointment(appointment);
}

@GetMapping("/appointments")
public List<Appointment> fetchAppointmentList() {
    return appointmentServiceImpl.listAppointment();
}
@PutMapping("/appointment/{id}")
public Appointment updateAppointment(@PathVariable("id") Long appointmentId, @RequestBody Appointment appointment) {
   
    
    return appointmentServiceImpl.updateAppointment(appointment, appointmentId);
}

@DeleteMapping("/appointments/{id}")
 public String deleteAppointmentById(@PathVariable("id")
                                       Long appointmentId)
    {
        appointmentServiceImpl.deleteAppointmentById(
            appointmentId);
        return "Appointment deleted Successfully";
    }

}
