/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas4;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
/**
 *
 * @author WXO
 */
public class Login extends JFrame{
    public String username, password;
    
    Connector connector = new Connector(); 
    
    JFrame window = new JFrame("JDBC");
   
    JLabel l_user = new JLabel("Username  ");
        JTextField lUser = new JTextField();
    JLabel l_pwd = new JLabel("Password  ");
        JTextField lPwd = new JTextField();
    JLabel r_user = new JLabel("Username ");
        JTextField rUser = new JTextField();
    JLabel r_pwd = new JLabel("Password ");
        JTextField rPwd = new JTextField();

    JButton login = new JButton("Login");
    JButton daftar = new JButton("Daftar");  
    
    public Login() {
        window.setLayout(null);
        window.setSize(550,200);
        window.setDefaultCloseOperation(3);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);


       // setDefaultCloseOperation(EXIT_ON_CLOSE);

//ADD COMPONENT
        window.add(lUser);
        window.add(lPwd);
        window.add(rUser);
        window.add(rPwd);
        window.add(l_user);
        window.add(l_pwd);
        window.add(r_user);
        window.add(r_pwd);
        window.add(login);
        window.add(daftar);

//LABEL
        l_user.setBounds(45, 10, 120, 20);
        l_pwd.setBounds(45, 60, 120, 20);
        r_user.setBounds(200,10,120,20);
        r_pwd.setBounds(200,60,120,20);

//TEXTFIELD
        lUser.setBounds(45, 35, 120, 20);
        lPwd.setBounds(45, 85, 120, 20);
        rUser.setBounds(200, 35, 120, 20);
        rPwd.setBounds(200, 85, 120, 20);

//BUTTON PANEL
        login.setBounds(65, 120, 90, 20);
        daftar.setBounds(220,120,90,20);
        
        daftar.addActionListener(new ActionListener() {
            String query;
                
            @Override
            public void actionPerformed(ActionEvent arg0) {
                   try {
            query = "SELECT * FROM users WHERE username = '"+getR_user()+"'";
            
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);

            if(!resultSet.next()){
                if("".equals(getR_user()) && "".equals(getR_pwd()) ) {
                    JOptionPane.showMessageDialog(null,"Input kosong");
                } else {
                    query = "INSERT INTO `users`(`username`, `password`) VALUES ('"+getR_user()+"','"+getR_pwd()+"')";

                    connector.statement = connector.koneksi.createStatement();
                    connector.statement.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,"Berhasil Mendaftarkan User");
                }
                
            }else{
                JOptionPane.showMessageDialog(null,"Username sudah digunakan");
            }
            
        } catch (  HeadlessException | SQLException ex){
            System.out.println(ex.getMessage());
        }
            }
        });  

        login.addActionListener(new ActionListener() {
            String query;
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                   try {
            query = "SELECT * FROM users WHERE username = '"+getL_user()+"'";
            
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);

            if(resultSet.next()){
                if(resultSet.getString("password").equals(getL_pwd()) )
                    JOptionPane.showMessageDialog(null,"Berhasil Login");
                else
                    JOptionPane.showMessageDialog(null,"Password salah");
            }else{
                if("".equals(getL_user()) && "".equals(getL_pwd()) ) {
                    JOptionPane.showMessageDialog(null,"Input kosong");
                } else {
                    JOptionPane.showMessageDialog(null,"Username tidak ditemukan");
                }
            }    
        } catch (  HeadlessException | SQLException ex){
            System.out.println(ex.getMessage());
        }
            }
        });    
    }

    public String getL_user(){
        return lUser.getText();
    }

    public String getL_pwd() {
        return lPwd.getText();
    }

    public String getR_user() {
        return rUser.getText();
    }

    public String getR_pwd() {
        return rPwd.getText();
    }
    
    public void inputDB(){
        
    }    
 
}


