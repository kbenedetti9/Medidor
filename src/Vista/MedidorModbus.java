package Vista;

import Logica.ConsumoReal;
import Logica.SoftwareMedidor;
import Persistencia.ConexionServidor;
import com.ghgande.j2mod.modbus.ModbusException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jssc.SerialPortException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ayneer Luis Gonzalez
 */
public class MedidorModbus extends javax.swing.JFrame {

    /**
     * Creates new form MedidorModbus
     *
     */
    public Hilo hilo;
    public SoftwareMedidor softwareMedidor = new SoftwareMedidor();
    public int valorDif = 0;

    public MedidorModbus() {
        initComponents();
        valorDif = softwareMedidor.leerValorDiferencial();
        this.valorDiferencial.setText(String.valueOf(valorDif));
    }

    int id = 0;
    String puerto = "";
    int ultimaLectura = softwareMedidor.leerLectura();
    ConexionServidor conex = new ConexionServidor();

    public boolean leerCampos() {

        boolean estado;

        String texto1 = this.idMedidor.getText();
        String texto2 = this.puertoCOM.getText();        
          
        if ("".equals(texto1) || "".equals(texto2)) {
            estado = false;
        } else {
            id = Integer.parseInt(this.idMedidor.getText().trim());
            puerto = this.puertoCOM.getText().trim();
            estado = true;
        }

        return estado;
    }

    public void modificarComponentes(boolean estado) {
        btnPrueba.setEnabled(estado);
        idMedidor.setEditable(estado);
        puertoCOM.setEditable(estado);
        btnConexion.setEnabled(estado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnPrueba = new javax.swing.JButton();
        btnConexion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        idMedidor = new javax.swing.JTextField();
        jLabelIdMedidor = new javax.swing.JLabel();
        jLabelPuerto = new javax.swing.JLabel();
        puertoCOM = new javax.swing.JTextField();
        jlabelValor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        valorDiferencial = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Software");

        btnPrueba.setText("Realizar prueba");
        btnPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebaActionPerformed(evt);
            }
        });

        btnConexion.setText("Iniciar conexión");
        btnConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConexionActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar conexión");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabelIdMedidor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelIdMedidor.setText("ID del medidor");

        jLabelPuerto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPuerto.setText("Puerto COM");

        jlabelValor.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jlabelValor.setText("Kwh");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Valor diferencial");

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnPrueba)
                        .addGap(52, 52, 52)
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelIdMedidor)
                                    .addComponent(jLabelPuerto)
                                    .addComponent(jLabel2))
                                .addGap(80, 80, 80)
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(puertoCOM, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addComponent(idMedidor, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addComponent(valorDiferencial))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(btnConexion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addGap(12, 12, 12)))))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(348, 348, 348))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdMedidor)
                    .addComponent(idMedidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPuerto)
                    .addComponent(puertoCOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(valorDiferencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addComponent(jlabelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebaActionPerformed
        if (leerCampos()) {
            btnConexion.setEnabled(false);
            btnCancelar.setEnabled(false);
            idMedidor.setEditable(false);
            puertoCOM.setEditable(false);

            try {
                jlabelValor.setText("Lectura actual = " + softwareMedidor.leerConsumoMedidorInteligente(id, puerto) + " Kwh");
            } catch (Exception ex) {
                System.out.println("No se puede establecer comunicación con el medidor.");
            }

            btnConexion.setEnabled(true);
            btnCancelar.setEnabled(true);
            idMedidor.setEditable(true);
            puertoCOM.setEditable(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
        }


    }//GEN-LAST:event_btnPruebaActionPerformed

    public class Hilo extends Thread {

        public boolean estado = false;
        public long tiempo = 0;

        Hilo() {
            this.estado = false;
        }

        Hilo(boolean nuevoEstado) {
            this.estado = nuevoEstado;
        }

        @Override
        public void run() {

            while (estado) {
                int lecturaMedidor = 0;
                String fecha = "";
                ArrayList<ConsumoReal> listaConsumo = new ArrayList<>();
                try {
                    lecturaMedidor = (softwareMedidor.leerConsumoMedidorInteligente(id, puerto))-valorDif;
                    fecha = softwareMedidor.obtenerFechaMedicion();
                    System.out.println(ultimaLectura);
                    if (ultimaLectura == -1) {//Primera lectura
                        conex.enviarConsumo(id, lecturaMedidor, fecha);
                        System.out.println(id + " "+lecturaMedidor+ " "+fecha);
                        ultimaLectura = lecturaMedidor;
                        softwareMedidor.guardarLectura(ultimaLectura);
                    } else {//No es la primera lectura
                        if (lecturaMedidor > ultimaLectura) {
                            //Antes de enviar el nuevo consumo hacia el servidor
                            //se verifica si existen consumos almacenados por enviar.
                            listaConsumo = softwareMedidor.leerConsumo();

                            if (!listaConsumo.isEmpty()) {
                                //Existen consumos por enviar!!
                                int contadorError = 0;
                                int i = 0;
                                for (i = 0; i < listaConsumo.size(); i++) {

                                    //Intentamos enviar el primer consumo almacenado.
                                    try {
                                        conex.enviarConsumo(listaConsumo.get(i).getId(), listaConsumo.get(i).getLecturaMedidor(), listaConsumo.get(i).getFecha());
                                        //Si se envia, se debe eliminar del array.
                                        listaConsumo.remove(i);
                                        softwareMedidor.guardarConsumo(listaConsumo);//actualizamos la lista
                                    } catch (IOException ex) {
                                        System.out.println("Error al volver intentar conectar con el servidor...");
                                        contadorError++;
                                    }
                                    if (contadorError > 0) {
                                        //Si no se puede conectar con el servidor
                                        //No se sigue intentando, y se pasa a guardar
                                        //el nuevo consumo
                                        break;
                                    }
                                    i--;//reiniciamos el iterador.
                                }
                            }
                            //Vuelvo a verificar el estado de la lista
                            //actualizo la lista
                            listaConsumo = softwareMedidor.leerConsumo();
                            if (listaConsumo.isEmpty()) {
                                //Finalmente se enviaron los consumos guardados
                                conex.enviarConsumo(id, lecturaMedidor, fecha);
                                System.out.println(id + " "+lecturaMedidor+ " "+fecha);
                                ultimaLectura = lecturaMedidor;
                                softwareMedidor.guardarLectura(ultimaLectura);
                            } else {
                                //Se guarda de forma local
                                ConsumoReal consumo = new ConsumoReal(id, lecturaMedidor, fecha);
                                listaConsumo.add(consumo);
                                softwareMedidor.guardarConsumo(listaConsumo);
                                ultimaLectura = lecturaMedidor;
                                softwareMedidor.guardarLectura(ultimaLectura);
                            }
                        }
                    }

                    jlabelValor.setText("Lectura actual = " + lecturaMedidor + " Kwh | " + new Date());

                    TimeUnit.SECONDS.sleep(tiempo);

                } catch (IOException ex) {
                    System.out.println("Error al intentar conectar con el servidor.");
                    System.out.println("La lectura se almacenará de forma local...");
                    System.out.println(ex);
                    ConsumoReal cReal = new ConsumoReal(id, lecturaMedidor, fecha);
                    listaConsumo = softwareMedidor.leerConsumo();
                    listaConsumo.add(cReal);
                    softwareMedidor.guardarConsumo(listaConsumo);
                    /*ultimaLectura = lecturaMedidor;
                    softwareMedidor.guardarLectura(ultimaLectura);*/
                    System.out.println("Consumo guardado de forma local." + cReal);
                } catch (InterruptedException ex) {
                    System.out.println("Error al dormir el hilo");
                } catch (ModbusException ex) {
                    System.out.println("Error al intentar leer el medidor!!");
                } catch (SerialPortException ex) {//Ocurre cuando se desconecta el cable serial por accidente o se bloquea el computador
                    System.out.println("Error al intentar conectar con el medidor. Vuelva a conectarlo e intente de nuevo.");
                    estado = false;
                } catch (Exception ex) {
                    System.out.println("Error al intentar conectar con el medidor. SI esta conectado!!");
                    estado = false;
                }
            }
            System.out.println("Se acabo el hilo!!");
            estado = false;
        }
    }


    private void btnConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConexionActionPerformed
        if (leerCampos()) {
            
            String texto3 = this.valorDiferencial.getText();
            
            if(!"".equals(texto3)){
                softwareMedidor.guardarValorDiferencial(Integer.parseInt(texto3));
            }
            
            hilo = new Hilo();

            hilo.estado = true;
            hilo.tiempo = 3; //3 seg

            hilo.start();

            modificarComponentes(false);
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.");
        }


    }//GEN-LAST:event_btnConexionActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        hilo.estado = false;
        jlabelValor.setText("Kwh");
        modificarComponentes(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MedidorModbus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MedidorModbus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MedidorModbus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MedidorModbus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MedidorModbus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConexion;
    private javax.swing.JButton btnPrueba;
    private javax.swing.JTextField idMedidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelIdMedidor;
    private javax.swing.JLabel jLabelPuerto;
    private javax.swing.JLabel jlabelValor;
    private javax.swing.JTextField puertoCOM;
    private javax.swing.JTextField valorDiferencial;
    // End of variables declaration//GEN-END:variables
}
