/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class Conectar {
    private static final String usuario = "root";
    private static final String senha = "";
    private static final String url = "jdbc:mysql://localhost/consultaa3";
    public static Connection getConectar(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados "+ex.getMessage());
        }
       return con;
    }
}
