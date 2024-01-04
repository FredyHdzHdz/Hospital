package com.hospital.hospital.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class EspecialidadesRequest {
    private int idEspecialidad;
    private String Especialidad;
    private boolean activo;
}

