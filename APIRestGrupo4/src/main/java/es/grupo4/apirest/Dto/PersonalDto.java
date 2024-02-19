package es.grupo4.apirest.Dto;

import es.grupo4.apirest.model.Departamento;
import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.model.Personal;

public class PersonalDto {
    private int id;

    private byte activo;

    private String apellido1;

    private String apellido2;

    private String cp;

    private String direccion;

    private String dni;

    private String localidad;

    private String nombre;

    private String tlf;

    private String perfile;

    private String departamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getActivo() {
        return activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getPerfile() {
        return perfile;
    }

    public void setPerfile(String perfile) {
        this.perfile = perfile;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public static PersonalDto fromEntity(Personal prof){
        PersonalDto dto=new PersonalDto();
        dto.setId(prof.getId());
        dto.setNombre(prof.getNombre());
        dto.setApellido1(prof.getApellido1());
        if(prof.getDni()!=null){
            dto.setDni(prof.getDni());
        }else dto.setDni(null);
        if(prof.getActivo()!=0){
            dto.setActivo(prof.getActivo());
        }else dto.setActivo((byte)0);
        if(prof.getApellido2()!=null){
            dto.setApellido2(prof.getApellido2());
        }else dto.setApellido2(null);
        if(prof.getDireccion()!=null){
            dto.setDireccion(prof.getDireccion());
        }else dto.setDireccion(null);
        if(prof.getLocalidad()!=null){
            dto.setLocalidad(prof.getLocalidad());
        }else dto.setLocalidad(null);
        if(prof.getCp()!=null){
            dto.setCp(prof.getCp());
        }else dto.setCp(null);
        if(prof.getTlf()!=null){
            dto.setTlf(prof.getTlf());
        }else dto.setTlf(null);
        if(prof.getDepartamento()!=null){
            dto.setDepartamento(prof.getDepartamento().getNombre());
        }else dto.setDepartamento(null);
        return dto;
    }

    public static Personal toEntity(PersonalDto dto){
        Personal prof=new Personal();
        prof.setId(dto.getId());
        prof.setNombre(dto.getNombre());
        prof.setApellido1(dto.getApellido1());
        if(dto.getDni()!=null){
            prof.setDni(dto.getDni());
        }else prof.setDni(null);
        if(dto.getActivo()!=0){
            prof.setActivo(dto.getActivo());
        }else prof.setActivo((byte)0);
        if(dto.getApellido2()!=null){
            prof.setApellido2(dto.getApellido2());
        }else prof.setApellido2(null);
        if(dto.getDireccion()!=null){
            prof.setDireccion(dto.getDireccion());
        }else prof.setDireccion(null);
        if(dto.getLocalidad()!=null){
            prof.setLocalidad(dto.getLocalidad());
        }else prof.setLocalidad(null);
        if(dto.getCp()!=null){
            prof.setCp(dto.getCp());
        }else prof.setCp(null);
        if(dto.getTlf()!=null){
            prof.setTlf(dto.getTlf());
        }else prof.setTlf(null);
        //prof.setDepartamento(departamentoRepository.getDepartamentoByNombre(dto.getDepartamento()).orElse(null));
        return prof;
    }
}
