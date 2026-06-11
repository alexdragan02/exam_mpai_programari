package com.example.examenprogramarimpai.repository;

import com.example.examenprogramarimpai.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import com.example.examenprogramarimpai.model.AppointmentStatus;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Metode pentru filtrare/cautare (utilizate de Administratori/Receptie)
    List<Appointment> findByPacientContainingIgnoreCase(String pacient);
    List<Appointment> findByMedicContainingIgnoreCase(String medic);
    List<Appointment> findByStatus(AppointmentStatus status);
    
    // Filtrare dupa interval daca e necesar (mai avansat, folosim findAll si filtram in service pentru simplitate la examen)
}



