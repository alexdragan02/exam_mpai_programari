package com.example.examenprogramarimpai.controller;

import com.example.examenprogramarimpai.dto.AppointmentDTO;
import com.example.examenprogramarimpai.model.AppointmentStatus;
import com.example.examenprogramarimpai.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/home")
    public String home(@RequestParam(required = false) AppointmentStatus statusFilter, Model model) {
        List<AppointmentDTO> appointments;
        
        if (statusFilter != null) {
            appointments = appointmentService.getAppointmentsByStatus(statusFilter);
        } else {
            appointments = appointmentService.getAllAppointments();
        }
        
        model.addAttribute("appointments", appointments);
        // Trimitem un DTO gol pentru formularul de "Programare Noua"
        model.addAttribute("newAppointment", new AppointmentDTO());
        
        return "home";
    }

    @PostMapping("/create")
    public String createAppointment(@ModelAttribute AppointmentDTO newAppointment) {
        appointmentService.createAppointment(newAppointment);
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateStatus(@RequestParam Long appointmentId, @RequestParam AppointmentStatus status) {
        appointmentService.updateStatus(appointmentId, status);
        return "redirect:/home";
    }

    @PostMapping("/cancel")
    public String cancelAppointment(@RequestParam Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return "redirect:/home";
    }
}
