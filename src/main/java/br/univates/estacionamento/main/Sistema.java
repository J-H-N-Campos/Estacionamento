/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.main;

import br.univates.authenticator.Authenticator;
import br.univates.authenticator.GenericUser;
import br.univates.estacionamento.apresentacao.TelaMenu;
import br.univates.estacionamento.negocio.Usuario;
import br.univates.estacionamento.persistencia.BDFactory;
import br.univates.estacionamento.persistencia.EstacionamentoUsuarioBD;
import br.univates.system32.app.Application;
import br.univates.system32.app.ApplicationProcess;
import br.univates.system32.app.FatalSystemException;
import br.univates.system32.db.DataBaseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaoh
 */
public class Sistema extends Application
{
    
    private static Sistema sys = new Sistema();
    
    public static Sistema getInstance()
    {
        return sys;
    }

    private Sistema()
    {
        super("Sistema Estacionamento", "estacionamento");
    }

    @Override
    public void defineFirstExecutionProcesses()
    {
        this.addFirstExecutionProcess(new ApplicationProcess("Criando o esquema do banco de dados...")
        {
            @Override
            public void run() throws FatalSystemException
            {
                createDataBaseSchemaGenerationProcess("/br/univates/estacionamento/resources/script/estacionamento_postgres.sql");
            }
        });
    }

    @Override
    public void defineInitialProcesses()
    {
        this.addInitialProcess(new ApplicationProcess("Solicitando login...",false)
        {
            @Override
            public void run() throws FatalSystemException
            {
                try
                {
                    EstacionamentoUsuarioBD userBD = BDFactory.newUsuarioBD();
                    ArrayList<Usuario>      user   = userBD.readAll();
                    ArrayList<GenericUser>  user2  = new ArrayList();
                    
                    for (Usuario usuario : user)
                    {
                        user2.add(usuario);
                    }
                    
                    Authenticator auth = new Authenticator(user2);
                    
                    
                    auth.runAuthentication();
                    
                    if (auth.getLoggedUser() == null)
                    {
                        throw new FatalSystemException("Processo de autenticação cancelado");
                    }
                    
                    setLoggedUser( auth.getLoggedUser() );
                }
                catch (DataBaseException ex)
                {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        this.addInitialProcess(new ApplicationProcess("Abrindo a tela do sistema...")
        {
            @Override
            public void run()
            {
                TelaMenu t = new TelaMenu();
                t.setVisible(true);
            }
        });
    }

    @Override
    public void defineFinalProcesses()
    {

    }
}