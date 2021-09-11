/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.main;

import br.univates.system32.db.DataBaseConnectionManager;
import br.univates.system32.db.DataBaseException;

/**
 *
 * @author joaoh
 */
public class Main
{
    public static void main(String[] args)
    {
        Sistema sys = Sistema.getInstance();
        
        sys.setSplashPath("/br/univates/estacionamento/resources/images/foto.jpg");
        sys.start();
    }
    
    public void script()
    {
        try
        {
            DataBaseConnectionManager db = new DataBaseConnectionManager(DataBaseConnectionManager.POSTGRESQL, "Estacionamento_BD", "postgres", "9611");

            db.connectDataBase();
            db.setScriptPath("/br/univates/estacionamento/resources/script/estacionamento_postgres.sql");
            db.runScritpSQL();

        } catch (DataBaseException ex)
        {
            System.out.println("erro ---> " + ex.getMessage());
        }
    }
}