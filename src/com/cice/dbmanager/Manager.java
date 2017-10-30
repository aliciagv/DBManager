/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cice.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author cice
 */
public class Manager {
    
    
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private String host, port, user, pass, database, url;
    Connection connection;

    public Manager() {
       this.host="127.0.0.1";
       this.port="3307";
       this.user="root";
       this.pass="root";
       this.database="java";
       //jdbc:mysql://localhost:8889/t119
       this.url="jdbc:mysql://"+host+":"+port+ "/"+database+"?useSSL=false";
       
       
    }

    
    public Manager(String host, String port, String user, String pass, String database, String url) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.database = database;
        this.url = url;
    }

    
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    private void dbConnect() throws Exception {
        try {
            Class.forName(DRIVER);
            connection =(Connection) DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception("Se ha producido un error al conectar con la DB",ex);
           
        }
        
    }
    
    //insert,update,delete
    public void executeUpdate(String sql){
        
        try {
            dbConnect();
            
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
            
            st.close();
            dbCloseConnection();
        } catch (Exception ex) {
            Logger.getLogger(Manager.class.getCanonicalName()).log(Level.SEVERE, ex.getLocalizedMessage(),ex);
        }
            
            
        
        
    
    
    }
    
        public ResultSet executeSelect(String sql){
     
         ResultSet busqueda=null;
         
        try {
            dbConnect();
            Statement st = connection.createStatement();
            busqueda = st.executeQuery(sql);
            st.close();
            dbCloseConnection();
            
        } catch (Exception ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return busqueda;
     }
    
    public void dbCloseConnection() throws Exception{
        try {
            connection.close();
        } catch (SQLException ex) {
             throw new Exception("Se ha producido un error al desconectar con la DB",ex);
        }
    
    }
    
    
}
