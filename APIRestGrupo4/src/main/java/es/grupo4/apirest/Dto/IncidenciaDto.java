package es.grupo4.apirest.Dto;

import es.grupo4.apirest.model.*;

import java.util.Date;
import java.util.List;

public class IncidenciaDto {
    private Integer id;

    private int num;

    private String adjuntoUrl;

    private String descripcion;

    private String estado;

    private Date fechaCierre;

    private Date fechaCreacion;

    private String tipo;

    private List<ComentarioDto> comentarios;

    private String equipo;

    private String incidenciasSubtipo;

    private PersonalDto profesorIncidencia;

    private PersonalDto profesorAdministrador;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAdjuntoUrl() {
        return adjuntoUrl;
    }

    public void setAdjuntoUrl(String adjuntoUrl) {
        this.adjuntoUrl = adjuntoUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<ComentarioDto> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDto> comentarios) {
        this.comentarios = comentarios;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getIncidenciasSubtipo() {
        return incidenciasSubtipo;
    }

    public void setIncidenciasSubtipo(String incidenciasSubtipo) {
        this.incidenciasSubtipo = incidenciasSubtipo;
    }

    public PersonalDto getProfesorIncidencia() {
        return profesorIncidencia;
    }

    public void setProfesorIncidencia(PersonalDto profesorIncidencia) {
        this.profesorIncidencia = profesorIncidencia;
    }

    public PersonalDto getProfesorAdministrador() {
        return profesorAdministrador;
    }

    public void setProfesorAdministrador(PersonalDto profesorAdministrador) {
        this.profesorAdministrador = profesorAdministrador;
    }

    public static IncidenciaDto fromEntity(Incidencia inci){
        IncidenciaDto dto=new IncidenciaDto();
        dto.setNum(inci.getNum());
        if (inci.getAdjuntoUrl() != null) {
            dto.setAdjuntoUrl(inci.getAdjuntoUrl());
        } else {
            dto.setAdjuntoUrl(null);
        }
        if (inci.getDescripcion() != null) {
            dto.setDescripcion(inci.getDescripcion());
        } else {
            dto.setDescripcion(null);
        }
        if (inci.getEstado() != null) {
            dto.setEstado(inci.getEstado());
        } else {
            dto.setEstado(null);
        }
        if (inci.getFechaCreacion() != null) {
            dto.setFechaCreacion(inci.getFechaCreacion());
        } else {
            dto.setFechaCreacion(null);
        }
        if (inci.getFechaCierre() != null) {
            dto.setFechaCierre(inci.getFechaCierre());
        } else {
            dto.setFechaCierre(null);
        }
        if (inci.getTipo() != null) {
            dto.setTipo(inci.getTipo());
        } else {
            dto.setTipo(null);
        }
        if (inci.getEquipo() != null) {
            dto.setEquipo(inci.getEquipo().getEtiqueta());
        } else {
            dto.setEquipo(null);
        }
        if (inci.getIncidenciasSubtipo() != null) {
            dto.setIncidenciasSubtipo(inci.getIncidenciasSubtipo().getSubtipoNombre());
        } else {
            dto.setIncidenciasSubtipo(null);
        }
        if (inci.getPersonal1() != null) {
            dto.setProfesorIncidencia(PersonalDto.fromEntity(inci.getPersonal1()));
        }
        if (inci.getPersonal2() != null) {
            dto.setProfesorAdministrador(PersonalDto.fromEntity(inci.getPersonal2()));
        }else{
            dto.setProfesorAdministrador(null);
        }
        return dto;
    }
}
