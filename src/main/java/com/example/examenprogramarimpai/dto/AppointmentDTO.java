package com.example.examenprogramarimpai.dto;

import java.time.LocalDateTime;
import com.example.examenprogramarimpai.model.AppointmentStatus;

public class AppointmentDTO {

    private Long id;
    private String pacient;
    private String medic;
    private LocalDateTime dataOra;
    private String motiv;
    private AppointmentStatus status;

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
