package com.datastructure.code.gestionbeneficiarios.FuncionesApp.Clases;

public class cls_beneficiario {
    private String id;
    private String nombre;
    private  int puntaje;
    private boolean estado;
    private Integer turno;

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public cls_beneficiario(String id, String nombre, int puntaje, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
