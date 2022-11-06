/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler_gui;

import aplikaciona_logika.ServerskaNit;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class PoveziSe {
    private static PoveziSe instance;

    public static PoveziSe getInstance() {
        if(instance == null){
            instance = new PoveziSe();
        }
        return instance; 
    }
    private ServerskaNit server;
    
    private PoveziSe()
    {
    
    }
    public void serverObrada(boolean isStart) throws IOException{
        if(isStart) {
            if(server == null || server.isAlive() == false){
            server = new ServerskaNit();
            server.start();
            }
        }else{
            if(server!=null && server.getServerSoket().isBound()){
            server.zaustaviServer();
            }
        }
        
    }

   
}
