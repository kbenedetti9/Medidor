/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Karen Benedetti M
 */
public class ConexionServidor {

    public void enviarConsumo(int idMedidor, int consumoReal, String fecha) throws IOException {
        URL url;

        url = new URL("http://semard.com.co:3500/consumo");
       // url = new URL("http://localhost:3500/consumo");
        HttpURLConnection servidor = (HttpURLConnection) url.openConnection();

        // Enable output for the connection.
        servidor.setDoOutput(true);
        servidor.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        servidor.setRequestProperty("Accept", "application/json");

        // Set HTTP request method.
        servidor.setRequestMethod("POST");

        // Create JSON request.
        JSONObject jsonObj = new JSONObject().put("id_medidor", idMedidor).put("consumoTotal", consumoReal).put("fecha", fecha);

        OutputStreamWriter writer = new OutputStreamWriter(servidor.getOutputStream());
        writer.write(jsonObj.toString());
        writer.close();
        String line;

        // Read input data stream.
        BufferedReader reader = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        System.out.println(response.toString());

    }

}
