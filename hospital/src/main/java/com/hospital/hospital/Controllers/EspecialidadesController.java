package com.hospital.hospital.Controllers;


import com.hospital.hospital.Models.EspecialidadesRequest;

import com.hospital.hospital.Models.Response;
import com.hospital.hospital.Models.EspecialidadesResponse;
import com.hospital.hospital.Service.EspecialidadesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/10.0.0/")
@Tag(name = " Especialidades: Catalogo de especialidades", description = "Servicio donde se visualizan las especialidades ofertadas por el hospital")
public class EspecialidadesController {
    private final EspecialidadesService especialidadesService;

    @Autowired
    public EspecialidadesController(EspecialidadesService especialidadesService) {
        this.especialidadesService = especialidadesService;
    }
    @Operation(summary = "Endpoint que consultar las especialidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidades",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class, type = "array"))}),
            @ApiResponse(responseCode = "404", description = "Sin especialidades",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class, type = "array"))})})
    @Transactional
    @GetMapping("/hospital/especialidades/search/all")
    public ResponseEntity obtenerEspecialidades() {
        List<EspecialidadesResponse> especialidadesList = especialidadesService.obtenerEspecialidades();
        boolean succes=true;
        int status=0;
        String message="OK";
        int code = 200;
              if (especialidadesList.isEmpty()) {
               succes=false;
               status=1;
               message="Sin datos que mostrar";
               code=404;
           }

        Response response = Response.builder().success(succes).message(message).status(status).especialidades(especialidadesList).build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(code));
    }

    @Transactional
    @GetMapping("/hospital/especialidades/search")
    public ResponseEntity obtenerEspecialidadesporactivo(@RequestBody EspecialidadesRequest filtro, BindingResult bindingResult) {
        int idespecialidad = filtro.getIdEspecialidad();
        //String especialidad = filtro.getEspecialidad();
        //boolean activo = filtro.isActivo();
        List<EspecialidadesResponse> especialidadesList = especialidadesService.obtenerEspecialidadesFiltro(idespecialidad);
        boolean succes=true;
        int status=0;
        String message="OK";
        int code = 200;
        if (especialidadesList.isEmpty()) {
            succes=false;
            status=1;
            message="Sin datos que mostrar";
            code=404;
        }

        Response response = Response.builder().success(succes).message(message).status(status).especialidades(especialidadesList).build();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(code));
    }
}

