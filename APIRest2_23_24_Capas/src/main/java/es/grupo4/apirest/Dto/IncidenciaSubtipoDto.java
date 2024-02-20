package es.grupo4.apirest.Dto;

import es.grupo4.apirest.enums.Tipo;
import es.grupo4.apirest.model.IncidenciasSubtipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class IncidenciaSubtipoDto {

    private int id;

    private String subSubtipo;

    private String subtipoNombre;

    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubSubtipo() {
        return subSubtipo;
    }

    public void setSubSubtipo(String subSubtipo) {
        this.subSubtipo = subSubtipo;
    }

    public String getSubtipoNombre() {
        return subtipoNombre;
    }

    public void setSubtipoNombre(String subtipoNombre) {
        this.subtipoNombre = subtipoNombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static IncidenciaSubtipoDto fromEntity(IncidenciasSubtipo sub){
        IncidenciaSubtipoDto dto= new IncidenciaSubtipoDto();
        dto.setId(sub.getId());
        dto.setTipo(sub.getTipo().toString());
        if(sub.getSubtipoNombre()!=null){
            dto.setSubtipoNombre(sub.getSubtipoNombre());
        }
        if(sub.getSubSubtipo()!=null){
            dto.setSubSubtipo(sub.getSubSubtipo());
        }
        return dto;
    }

    public static IncidenciasSubtipo toEntity(IncidenciaSubtipoDto dto){
        IncidenciasSubtipo sub=new IncidenciasSubtipo();
        sub.setId(dto.getId());
        sub.setTipo(Tipo.valueOf(dto.getTipo().toUpperCase()));
        if(dto.getSubtipoNombre()!=null){
            sub.setSubtipoNombre(dto.getSubtipoNombre());
        }
        if(dto.getSubSubtipo()!=null){
            sub.setSubSubtipo(dto.getSubSubtipo());
        }
        return sub;
    }
}
