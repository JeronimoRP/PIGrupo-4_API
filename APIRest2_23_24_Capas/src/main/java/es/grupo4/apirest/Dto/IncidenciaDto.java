package es.grupo4.apirest.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import es.grupo4.apirest.constant.Utils;
import es.grupo4.apirest.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class IncidenciaDto {
    private Integer id;

    private int num;

    private String adjuntoUrl;

    private String descripcion;

    private String estado;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCierre;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    private String tipo;

    private List<ComentarioDto> comentarios;

    private String equipo;

    private IncidenciaSubtipoDto incidenciasSubtipo;

    private PersonalDto profesorIncidencia;

    private PersonalDto profesorAdministrador;

    private Time tiempoDec;

    private String fichero;

    private String extension;

    public String getFichero() {
        return fichero;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

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

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
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

    public IncidenciaSubtipoDto getIncidenciasSubtipo() {
        return incidenciasSubtipo;
    }

    public void setIncidenciasSubtipo(IncidenciaSubtipoDto incidenciasSubtipo) {
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

    public Time getTiempoDec() {
        return tiempoDec;
    }

    public void setTiempoDec(Time tiempoDec) {
        this.tiempoDec = tiempoDec;
    }

    public static IncidenciaDto fromEntity(Incidencia inci){
        IncidenciaDto dto=new IncidenciaDto();
        dto.setNum(inci.getNum());

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
            dto.setIncidenciasSubtipo(IncidenciaSubtipoDto.fromEntity(inci.getIncidenciasSubtipo()));
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
         if(inci.getAdjuntoUrl()!=null){
            File file=new File(Utils.path+inci.getAdjuntoUrl());
            dto.setFichero(encodeFileToBase64(file));
            dto.setExtension(FilenameUtils.getExtension(file.getName()));
        }
        return dto;
    }

    public static Incidencia toEntity(IncidenciaDto dto){
        Incidencia incidencia=new Incidencia();
        incidencia.setNum(dto.getNum());

        if (dto.getAdjuntoUrl() != null) {
            incidencia.setAdjuntoUrl(dto.getAdjuntoUrl());
        } else {
            incidencia.setAdjuntoUrl(null);
        }
        if (dto.getDescripcion() != null) {
            incidencia.setDescripcion(dto.getDescripcion());
        } else {
            incidencia.setDescripcion(null);
        }
        if (dto.getEstado() != null) {
            incidencia.setEstado(dto.getEstado());
        } else {
            incidencia.setEstado(null);
        }
        if (dto.getFechaCreacion() != null) {
            incidencia.setFechaCreacion(dto.getFechaCreacion());
        } else {
            incidencia.setFechaCreacion(null);
        }
        if (dto.getFechaCierre() != null) {
            incidencia.setFechaCierre(dto.getFechaCierre());
        } else {
            incidencia.setFechaCierre(null);
        }
        if(dto.getTipo()!=null){
            incidencia.setTipo(dto.getTipo());
        }else{
            incidencia.setTipo(null);
        }
        if (dto.getIncidenciasSubtipo() != null) {
            incidencia.setIncidenciasSubtipo(IncidenciaSubtipoDto.toEntity(dto.getIncidenciasSubtipo()));
        } else {
            incidencia.setIncidenciasSubtipo(null);
        }
        if (dto.getProfesorIncidencia() != null) {
            incidencia.setPersonal1(PersonalDto.toEntity(dto.getProfesorIncidencia()));
        }
        if (dto.getProfesorAdministrador() != null) {
            incidencia.setPersonal2(PersonalDto.toEntity(dto.getProfesorAdministrador()));
        }else{
            dto.setProfesorAdministrador(null);
        }
        if(dto.getAdjuntoUrl()!=null){

        }else {

        }

        return incidencia;
    }

    private static String encodeFileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("Fallo al codificar el archivo " + file, e);
        }
    }

    private static File decodeFileFromBase64(String fic, String url){
        try{
            File file=new File(Utils.path+url);
            byte[] fileContent=Base64.getDecoder().decode(fic);
            FileUtils.writeByteArrayToFile(file, fileContent);
            return file;
        }catch (IOException e) {
            throw new IllegalStateException("Fallo al codificar el archivo " + fic, e);
        }
    }
}
