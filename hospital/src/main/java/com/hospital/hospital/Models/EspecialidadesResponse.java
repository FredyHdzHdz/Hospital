package com.hospital.hospital.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EspecialidadesResponse {
    private int idEspecialidad;
    private String Especialidad;
    private boolean activo;
 }