/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication18;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnok;

    @FXML
    private TextField txtname;

    @FXML
    private Label label;
    

    @FXML
    private PasswordField txtpass;
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    

    @FXML
    void login(ActionEvent event) {
        //JOptionPane.showMessageDialog(null,"Hi"); //kalau tombol dipencet mengeluarkan ini.
        String username  = txtname.getText();
        String password  = txtpass.getText();
        if(username.equals("") && password.equals("")){
            JOptionPane.showMessageDialog(null, "Ada yang kosong");
        }else{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/laundry","root", "");
                pst  = conn.prepareStatement("select * from admin where username =? and password =? ");
                pst.setString(1,username);
                pst.setString(2,password);
                rs = pst.executeQuery();
                if(rs.next()){
                                JOptionPane.showMessageDialog(null, "Login berhasil");

                }else{
                    JOptionPane.showMessageDialog(null, "Failed");
                    txtname.setText("");
                    txtpass.setText("");
                   
                    
                }
                
                
                
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
