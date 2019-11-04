/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modbus_protocol;

import Vista.MedidorModbus;

/**
 *
 * @author Ayneer Luis Gonzalez
 */
public class Modbus_protocol {

    public static void main(String[] args) {
        new MedidorModbus().setVisible(true);
        //Conocer el consumo = 8192
        //Conocer el id adress = 528
    }

}
