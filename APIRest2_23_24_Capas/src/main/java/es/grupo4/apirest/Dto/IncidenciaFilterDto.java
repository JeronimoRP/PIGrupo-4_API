package es.grupo4.apirest.Dto;

import java.time.LocalDateTime;

public class IncidenciaFilterDto {
    private String tipo;
    private String subtipoNombre;
    private String estado;
    private LocalDateTime date;

    public String getTipo() {
        return tipo;
    }

    public String getSubtipoNombre() {
        return subtipoNombre;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
