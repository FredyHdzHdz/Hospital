package com.hospital.hospital.Service;


import com.hospital.hospital.Models.EspecialidadesResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EspecialidadesService {



    private final EntityManager entityManager;

    @Autowired
    public EspecialidadesService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<EspecialidadesResponse> obtenerEspecialidades() {
        String sqlQuery = "select idEspecialidad,Especialidad,activo from hospital153991.especialidades;";

        Query query = entityManager.createNativeQuery(sqlQuery);


        List<Object[]> results = query.getResultList();
        List<EspecialidadesResponse> responseList = new ArrayList<>();

        for (Object[] result : results) {
            int idEspecialidad = ((Number) result[0]).intValue();
            String Especialidad = ((String) result[1]).toString();
            boolean activo = ((boolean) result[2]);
            EspecialidadesResponse response = new EspecialidadesResponse();
            response.setIdEspecialidad(idEspecialidad);
            response.setEspecialidad(Especialidad);
            response.setActivo(activo);
            responseList.add(response);
        }

        return responseList;
    }
    public List<EspecialidadesResponse> obtenerEspecialidadesFiltro(int idespecialidad) {


        String sqlQuery = "select idEspecialidad,Especialidad,activo from hospital153991.especialidades where idEspecialidad= :idespecialidad" ;

        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idEspecialidad", idespecialidad);

        List<Object[]> results = query.getResultList();
        List<EspecialidadesResponse> responseList = new ArrayList<>();

        for (Object[] result : results) {
            int idEspecialidad = ((Number) result[0]).intValue();
            String Especialidad = ((String) result[1]).toString();
            boolean activo = ((boolean) result[2]);
            EspecialidadesResponse response = new EspecialidadesResponse();
            response.setIdEspecialidad(idEspecialidad);
            response.setEspecialidad(Especialidad);
            response.setActivo(activo);
            responseList.add(response);
        }

        return responseList;
    }
}
