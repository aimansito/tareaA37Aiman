/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author aiman
 */
public class Metodos {

    public static void escribirUsuario(String username, String password) {
        FileWriter fileWriter = null;

        try {
            // Abre el archivo para escribir
            fileWriter = new FileWriter("./usuarios.csv", true);

            // Escribe el usuario y la contraseña separados por coma
            fileWriter.write(username + "," + password + "\n");

            System.out.println("Usuario y contraseña escritos en el archivo CSV.");
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    public static void verificarCredenciales(String usuario, String passwd) {
        String filePath = "./usuarios.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Itera a través de cada línea del archivo CSV
            while ((line = br.readLine()) != null) {
                // Divide la línea por comas para obtener username y password
                String[] fields = line.split(",");

                if (fields.length == 2) {
                    // Obtén username y password de los campos
                    String fileUsername = fields[0].trim();
                    String filePassword = fields[1].trim();

                    // Compara el username y password con los dados
                    if (usuario.equals(fileUsername) && passwd.equals(filePassword)) {
                        JOptionPane.showMessageDialog(null, "Ya existe este usuario");
                    } else {
                        JOptionPane.showMessageDialog(null, "Se ha iniciado sesión con éxito");
                    }
                }
            }
        } catch (IOException e) {
            // Manejo de excepciones (error al leer el archivo)
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de usuarios: " + e.getMessage());
        }
    }

    public static void rellenarCSV(ArrayList<Usuarios> lista) {
        try (FileWriter fw = new FileWriter("./usuarios.csv", true)) {
            for (Usuarios us : lista) {
                fw.write(us.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
            e.printStackTrace();  
        }
    }

    public static void modificarContraseña(List<Usuarios> lista) {
        try (FileWriter fw = new FileWriter("./usuarios.csv");) {
            for (Usuarios us : lista) {
                fw.write(us.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();  
        }
    }

    public static ArrayList<Usuarios> crearLista(String usuario, String passwd) {
        ArrayList<Usuarios> lista = new ArrayList<>();
        Usuarios u1 = new Usuarios(usuario, passwd);
        lista.add(u1);
        return lista;
    }

    public static List<String> leerFichero(String fichero) {
        List<String> lineas = new ArrayList<>();
        try {
            lineas = Files.readAllLines(Paths.get(fichero),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Error leyendo el fichero");
        }
        return lineas;
    }

    public static ArrayList<Usuarios> crearListaPersonas(List<String> fichero) {
        ArrayList<Usuarios> usuarios = new ArrayList<>();
        for (String us : fichero) {
            String[] array = us.split(",");
            String usuario = array[0];
            String passwd = array[1];
            Usuarios us2 = new Usuarios(usuario, passwd);
            usuarios.add(us2);
        }
        return usuarios;
    }
}
