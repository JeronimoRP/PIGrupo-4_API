package es.grupo4.apirest.Dto;

import es.grupo4.apirest.model.Aula;

public class AulaDto {
    private int num;

    private String codigo;

    private String descripcion;

    private int planta;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public static AulaDto fromEntity(Aula aula)
    {
        AulaDto dto=new AulaDto();
        dto.setNum(aula.getNum());
        if (aula.getCodigo() != null) {
            dto.setCodigo(aula.getCodigo());
        } else {
            dto.setCodigo(null);
        }
        if (aula.getDescripcion() != null) {
            dto.setDescripcion(aula.getDescripcion());
        } else {
            dto.setDescripcion(null);
        }
        if (aula.getPlanta() != 0) {
            dto.setPlanta(aula.getPlanta());
        } else {
            dto.setPlanta(0);
        }
        return dto;
    }
}
