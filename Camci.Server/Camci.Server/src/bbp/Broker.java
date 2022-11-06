/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbp;

import domen.OpstiDomenskiObjekat;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author PC
 */
public class Broker {
    private Connection connection;
    private String url;
    private String username;
    private String password;
    private String driver;

    public Broker() {
       this.konfiguracija();
    }
    
    public void connection() throws Exception {
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void konfiguracija() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "config/db.properties";
            FileInputStream fileInputStream = new FileInputStream(propertiesFileName);
            properties.load(fileInputStream);
            
            
            this.url = properties.getProperty("url");
            this.driver = properties.getProperty("driver");
            this.username = properties.getProperty("user");
            this.password = properties.getProperty("password");
            
            fileInputStream.close();
        } catch (IOException ex) {
        }
    }
     public void disconnection() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new Exception();
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                this.connection.commit();
            } catch (SQLException ex) {
                throw new Exception();
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                throw new Exception();
            }
        }
    }

    public List<OpstiDomenskiObjekat> VratiOdo(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + odo.TabelaNotacijaOdo()+ " "+ odo.JoinUslovOdo()+ ";");
            return odo.ObjektiOdo(resultSet);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public Long DodajOdo(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO " + odo.TabeleOdo()+ "(" + odo.KoloneUnosOdo()+ ")" + " VALUES (" + odo.VrednostiUnosOdo()+ ")", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return -1l;
    }

    public int IzmeniOdo(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("UPDATE " + odo.TabeleOdo()+ " SET " + odo.IzmenaUpitOdo()+ " WHERE " + odo.WhereUpitOdo());
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int ObrisiOdo(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("DELETE FROM " + odo.TabeleOdo()+ " WHERE "+odo.WhereUpitOdo());
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
