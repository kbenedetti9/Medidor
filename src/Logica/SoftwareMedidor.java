/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.ghgande.j2mod.modbus.Modbus;
import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;

/**
 *
 * @author Ayneer Luis Gonzalez
 */
public class SoftwareMedidor {

    private boolean estado = false;

    public SoftwareMedidor() {
        estado = false;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int leerConsumoMedidorInteligente(int idMedidor, String puerto) throws SerialPortException, ModbusException, Exception {

        //Respesta del medidor
        String valor = "";
        int valorConsulta = 0;

        //Instanciar objeto maestro (Medidor Inteligente).
        ModbusSerialMaster medidorSmart = null;
        //Codigo que se le enviará al maestro para hacer la consulta.
        int codigo = 8192;
        //Donde se almacenará la respuesta del maestro. 2 porque el valor de la lectura puede ser muy grande.
        InputRegister[] respuestaMaestro = new InputRegister[2];

        //Configuración para conectarse con el maestro.
        SerialParameters parametros = new SerialParameters();
        parametros.setPortName(puerto);
        parametros.setBaudRate(9600);
        parametros.setDatabits(8);
        parametros.setParity(SerialPort.PARITY_NONE);
        parametros.setStopbits(SerialPort.STOPBITS_1);
        parametros.setEncoding(Modbus.SERIAL_ENCODING_RTU);
        parametros.setEcho(false);

        //Se verifica la disponibilidad del puerto
        SerialPort portCom = new SerialPort(puerto);

        portCom.openPort();
        portCom.closePort();
        //Se abre una conexion con el esclavo.
        medidorSmart = new ModbusSerialMaster(parametros);
        medidorSmart.connect();

        //Se realiza la consulta al maestro.
        respuestaMaestro = medidorSmart.readInputRegisters(idMedidor, codigo, 2);

        //Se lee la respuesta del maestro.
        for (int i = 0; i < respuestaMaestro.length; i++) {
            valor += String.valueOf(respuestaMaestro[i]);
        }

        valorConsulta = Integer.parseInt(valor);

        //Se cierra la conexión.
        medidorSmart.disconnect();

        //Ahora se obtiene la lectura que realmente le interesa al cliente.
        return this.obtenerLecturaReal(valorConsulta);
    }

    public int obtenerLecturaReal(int lectura) {
        int lecturaReal = 0;
        int tamanoLectura = String.valueOf(lectura).length();
        if (tamanoLectura > 2) {

            int[] digitos = new int[tamanoLectura];
            int i = digitos.length - 1;
            while (lectura > 0) {
                digitos[i] = lectura % 10;
                lectura = lectura / 10;
                i--;
            }
            String numeros = "";

            for (int j = 0; j < digitos.length - 2; j++) {
                numeros += digitos[j];
            }
            lecturaReal = Integer.parseInt(numeros);
        }
        return lecturaReal;

    }

    public String obtenerFechaMedicion() {
        //Obtenemos la fecha y se pasa al formato requerido.
        LocalDate fecha = LocalDate.now();
        String fechaActual = String.valueOf(fecha.format(DateTimeFormatter.ofPattern("M/d/yyyy")));

        //Se obtiene la hora y se pasa a string con el formato requerido
        LocalTime hora = LocalTime.now();
        String horaActual = "";

        String formato = "";//AM o PM
        //La hora se debe trabajar en formato de 12h.
        if (hora.getHour() >= 12) {
            if (hora.getHour() == 24) {
                //son las 12 AM
                formato = "AM";
            } else {// son 12 am
                formato = "PM";
                horaActual = hora.getHour() + ":" + hora.getMinute() + ":" + hora.getSecond();
            }

            if (hora.getHour() > 12) {
                horaActual = (hora.getHour() - 12) + ":" + hora.getMinute() + ":" + hora.getSecond();
            }
        } else {
            if (hora.getHour() == 0) {
                //son las 12 AM
                formato = "AM";
                horaActual = 12 + ":" + hora.getMinute() + ":" + hora.getSecond();
            } else {//Hora normal de 12h
                horaActual = hora.getHour() + ":" + hora.getMinute() + ":" + hora.getSecond();
            }
            formato = "AM";
        }

        String fechaMedicion = fechaActual + ", " + horaActual + " " + formato;

        return fechaMedicion;
    }
    
    public void guardarConsumo(ArrayList<ConsumoReal> listaConsumo){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("consumo.dat"));
            oos.writeObject(listaConsumo);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo.");
        } catch (IOException ex) {
            System.out.println("Error al trabajar con archivo.");
        }
    }
    
    public void guardarLectura(int lectura){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lectura.dat"));
            oos.writeObject(lectura);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo.");
        } catch (IOException ex) {
            System.out.println("Error al trabajar con archivo.");
        }
    }
    
    public ArrayList<ConsumoReal> leerConsumo(){
        ArrayList<ConsumoReal> listaConsumo = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("consumo.dat"));
            Object aux = ois.readObject();
            listaConsumo = (ArrayList<ConsumoReal>) aux;
            ois.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encuentra el objeto.");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo.");
            return listaConsumo;
        } catch (EOFException ex) {
            System.out.println ("Lectura finalizada! Fin de fichero");
        } catch (IOException ex) {
            System.out.println ("Error al leer consumo");
        }
        
        return listaConsumo;
    }
    
    public int leerLectura(){
        int lectura = -1;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("lectura.dat"));
            Object aux = ois.readObject();
            lectura = (int) aux;
            ois.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("No se puede castear el objeto.");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo.");
            return lectura;
        } catch (EOFException ex) {
            System.out.println ("Lectura finalizada! Fin de fichero");
        } catch (IOException ex) {
            System.out.println ("Error al leer consumo");
        }
        
        return lectura;
    }
    
    public int leerValorDiferencial(){
        int valorDiferencial = 0;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("valorDiferencial.dat"));
            Object aux = ois.readObject();
            valorDiferencial = (int) aux;
            ois.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("No se puede castear el objeto.");
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo.");
            return valorDiferencial;
        } catch (EOFException ex) {
            System.out.println ("Lectura finalizada! Fin de fichero");
            return valorDiferencial;
        } catch (IOException ex) {
            System.out.println ("Error al leer consumo");
            return valorDiferencial;
        }
        
        return valorDiferencial;
    }
    
    public void guardarValorDiferencial(int valorDiferencial){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("valorDiferencial.dat"));
            oos.writeObject(valorDiferencial);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo.");
        } catch (IOException ex) {
            System.out.println("Error al trabajar con archivo.");
        }
    }
    
}
