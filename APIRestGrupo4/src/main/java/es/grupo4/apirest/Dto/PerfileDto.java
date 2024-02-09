package es.grupo4.apirest.Dto;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.model.Personal;

public class PerfileDto {
    private int personalId;

    private String dominio;

    private String educantabria;

    private String password;

    private String perfil;


    public int getPersonalId() {
        return personalId;
    }

    public void setPersonalId(int personalId) {
        this.personalId = personalId;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getEducantabria() {
        return educantabria;
    }

    public void setEducantabria(String educantabria) {
        this.educantabria = educantabria;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }



    public static PerfileDto fromEntity(Perfile perfil){
        PerfileDto dto=new PerfileDto();
        dto.setPersonalId(perfil.getPersonalId());
        dto.setPerfil(perfil.getPerfil());
        if(perfil.getDominio()!=null){
            dto.setDominio(perfil.getDominio());
        }else dto.setDominio(null);
        if(perfil.getEducantabria()!=null){
            dto.setEducantabria(perfil.getEducantabria());
        }else dto.setEducantabria(null);
        if(perfil.getPassword()!=null){
            dto.setPassword(perfil.getPassword());
        }else dto.setPassword(null);

        return dto;
    }
}
