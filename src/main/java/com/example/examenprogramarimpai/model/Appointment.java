package com.example.examenprogramarimpai.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pacient;
    private String medic;
    private LocalDateTime dataOra;
    private String motiv;
    
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPacient() {
        return pacient;
    }

    public void setPacient(String pacient) {
        this.pacient = pacient;
    }

    public String getMedic() {
        return medic;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public String getMotiv() {
        return motiv;
    }

    public void setMotiv(String motiv) {
        this.motiv = motiv;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
