package es.grupo4.apirest.Dto;

import es.grupo4.apirest.model.Aula;
import es.grupo4.apirest.model.Equipo;
import jakarta.persistence.Id;

import java.util.Date;

public class EquipoDto {
    @Id
    private int id;

    private byte baja;

    private String descripcion;

    private String etiqueta;

    private Date fechaAdquisicion;

    private String marca;

    private String modelo;

    private int puesto;

    private String tipoEquipo;

    private String aula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getBaja() {
        return baja;
    }

    public void setBaja(byte baja) {
        this.baja = baja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public static EquipoDto fromEntity(Equipo equipo){
        EquipoDto dto=new EquipoDto();
        if (equipo.getId() != 0) {
            dto.setId(equipo.getId());
        } else {
            dto.setId(0);
        }
        if (equipo.getBaja() != 0) {
            dto.setBaja(equipo.getBaja());
        } else {
            dto.setBaja((byte) 0);
        }
        if (equipo.getDescripcion() != null) {
            dto.setDescripcion(equipo.getDescripcion());
        } else {
            dto.setDescripcion(null);
        }
        if (equipo.getEtiqueta() != null) {
            dto.setEtiqueta(equipo.getEtiqueta());
        } else {
            dto.setEtiqueta(null);
        }
        if (equipo.getFechaAdquisicion() != null) {
            dto.setFechaAdquisicion(equipo.getFechaAdquisicion());
        } else {
            dto.setFechaAdquisicion(null);
        }
        if (equipo.getMarca() != null) {
            dto.setMarca(equipo.getMarca());
        } else {
            dto.setMarca(null);
        }
        if (equipo.getModelo() != null) {
            dto.setModelo(equipo.getModelo());
        } else {
            dto.setModelo(null);
        }
        if (equipo.getPuesto() != 0) {
            dto.setPuesto(equipo.getPuesto());
        } else {
            dto.setPuesto(0);
        }
        if (equipo.getTipoEquipo() != null) {
            dto.setTipoEquipo(equipo.getTipoEquipo());
        } else {
            dto.setTipoEquipo(null);
        }
        if (equipo.getAula() != null) {
            dto.setAula(equipo.getAula().getCodigo());
        } else {
            dto.setAula(null);
        }
        return dto;
    }
}
