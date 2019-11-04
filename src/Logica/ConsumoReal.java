/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;

/**
 *
 * @author Ayneer Luis Gonzalez
 */
public class ConsumoReal implements Serializable {

    int id;
    int lecturaMedidor;
    String fecha;

    public ConsumoReal() {
        id = 0;
        lecturaMedidor = 0;
        fecha = "";
    }
    
    public ConsumoReal(int id, int lecturaMedidor, String fecha) {
        this.id = id;
        this.lecturaMedidor = lecturaMedidor;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLecturaMedidor() {
        return lecturaMedidor;
    }

    public void setLecturaMedidor(int lecturaMedidor) {
        this.lecturaMedidor = lecturaMedidor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ConsumoReal{" + "id=" + id + ", lecturaMedidor=" + lecturaMedidor + ", fecha=" + fecha + '}';
    }
    
    
}
