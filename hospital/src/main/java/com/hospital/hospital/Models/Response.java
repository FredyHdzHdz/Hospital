package com.hospital.hospital.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@Getter
@Setter
@Value
@Builder
public class Response {
    private boolean success;
    private int status;
    private String message;

    private List<EspecialidadesResponse> especialidades;


    public boolean isSuccess() {
        return success;
    }


}