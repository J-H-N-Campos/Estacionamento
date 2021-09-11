/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
 
package br.univates.estacionamento.persistencia;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Usuario;
import br.univates.system32.db.DataBaseConnectionManager;
import br.univates.system32.db.DataBaseException;
import br.univates.system32.db.DuplicateKeyException;
import br.univates.system32.db.Filter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaoh
 */

public class UsuarioBDPostgresql implements EstacionamentoUsuarioBD
{
    
    private DataBaseConnectionManager conexao;
    
    public UsuarioBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "Estacionamento_BD", "postgres", "9611");
        conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Usuario us ) throws DataBaseException,DuplicateKeyException
    {
        if (us != null)
        {
            // criar SQL string
            String sql = "insert into usuario values ("+us.getId()+ ",'"+us.getLogin()+ "','"+us.getPassword()+"');";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
    }
    
    @Override
    public void edit( Usuario us ) throws DataBaseException
    {
        if (us != null)
        {
            // criar SQL string
            String sql = "update usuario set senha='"+us.getPassword()+"', login='"+us.getLogin()+"', " + "where idusuario="+us.getId()+";";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
    }
    
    @Override
    public void delete( Usuario us ) throws DataBaseException
    {
        if (us != null)
        {
            // criar SQL string
            String sql = "delete from usuario where idusuario="+us.getId()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
        
    }
    
    @Override
    public Usuario read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from usuario where idusuario="+id+";";
        Usuario us = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);
            
            if (rs.isBeforeFirst())
            {
                rs.next();
                int cod         = rs.getInt("idusuario");
                String login    = rs.getString("login");
                String senha    = rs.getString("senha");
                us = new Usuario(cod, login, senha);
                us.setPassword(senha);
            } 
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return us;
    }
    
    @Override
    public ArrayList<Usuario> readAll() throws DataBaseException
    {
        ArrayList<Usuario> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from usuario;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int id          = rs.getInt("idusuario");
                    String login    = rs.getString("login");
                    String password = rs.getString("senha");
                    
                    Usuario us = new Usuario(id, login, password);
                    us.setPassword(password);
                    temp.add(us);
                }
            }
        } 
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        } 
        
        return temp;
    }

    @Override
    public ArrayList<Usuario> read(Filter filter) throws DataBaseException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}