package com.example.examenprogramarimpai.config;

import com.example.examenprogramarimpai.model.Appointment;
import com.example.examenprogramarimpai.model.AppointmentStatus;
import com.example.examenprogramarimpai.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {
        if (appointmentRepository.count() == 0) {
            ClassPathResource resource = new ClassPathResource("programari.txt");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        Appointment app = new Appointment();
                        app.setPacient(parts[0].trim());
                        app.setMedic(parts[1].trim());
                        app.setDataOra(LocalDateTime.parse(parts[2].trim()));
                        app.setMotiv(parts[3].trim());
                        app.setStatus(AppointmentStatus.valueOf(parts[4].trim()));
                        appointmentRepository.save(app);
                    }
                }
            }
        }
    }
}
