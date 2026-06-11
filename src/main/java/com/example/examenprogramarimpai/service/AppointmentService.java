package com.example.examenprogramarimpai.service;

import com.example.examenprogramarimpai.dto.AppointmentDTO;
import com.example.examenprogramarimpai.model.Appointment;
import com.example.examenprogramarimpai.model.AppointmentStatus;
import com.example.examenprogramarimpai.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Conversie Entity -> DTO
    private AppointmentDTO convertToDTO(Appointment app) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(app.getId());
        dto.setPacient(app.getPacient());
        dto.setMedic(app.getMedic());
        dto.setDataOra(app.getDataOra());
        dto.setMotiv(app.getMotiv());
        dto.setStatus(app.getStatus());
        return dto;
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> getAppointmentsByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void createAppointment(AppointmentDTO dto) {
        Appointment app = new Appointment();
        app.setPacient(dto.getPacient());
        app.setMedic(dto.getMedic());
        app.setDataOra(dto.getDataOra());
        app.setMotiv(dto.getMotiv());
        app.setStatus(AppointmentStatus.SOLICITATA); // O programare noua e by default solicitata
        
        appointmentRepository.save(app);
        System.out.println("NOTIFICARE PACIENT: Programarea pentru " + app.getMedic() + " a fost INREGISTRATA (status: SOLICITATA).");
    }

    public void updateStatus(Long id, AppointmentStatus newStatus) {
        Optional<Appointment> opt = appointmentRepository.findById(id);
        if(opt.isPresent()) {
            Appointment app = opt.get();
            app.setStatus(newStatus);
            appointmentRepository.save(app);
            // Simulare Notificare la schimbare stare
            System.out.println("NOTIFICARE PACIENT: Programarea ta (ID: " + id + ") a trecut in starea: " + newStatus);
        }
    }

    public void cancelAppointment(Long id) {
        Optional<Appointment> opt = appointmentRepository.findById(id);
        if(opt.isPresent()) {
            Appointment app = opt.get();
            // Permite anularea doar daca e SOLICITATA sau CONFIRMATA
            if(AppointmentStatus.SOLICITATA.equals(app.getStatus()) || AppointmentStatus.CONFIRMATA.equals(app.getStatus())) {
                app.setStatus(AppointmentStatus.ANULATA);
                appointmentRepository.save(app);
                System.out.println("NOTIFICARE PACIENT: Programarea ta a fost ANULATA cu succes.");
            }
        }
    }
}
