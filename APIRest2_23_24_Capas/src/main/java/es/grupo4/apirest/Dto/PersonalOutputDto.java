package es.grupo4.apirest.Dto;

import java.util.List;

public class PersonalOutputDto {
    private Integer personalId;
    private String nombre;
    private List<IncidenciaDto> incidencias;

    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIncidencias(List<IncidenciaDto> incidencias) {
        this.incidencias = incidencias;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public String getNombre() {
        return nombre;
    }

    public List<IncidenciaDto> getIncidencias() {
        return incidencias;
    }
}
