/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.persistencia;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Limpeza;
import br.univates.estacionamento.negocio.Veiculo;
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
public class LimpezaBDPostgresql implements EstacionamentoLimpezaBD
{
    private DataBaseConnectionManager conexao;
    
    public LimpezaBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "Estacionamento_BD", "postgres", "9611");
        this.conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Limpeza l ) throws DataBaseException,DuplicateKeyException
    {
        if (l != null)
        {
            // criar SQL string
            String sql = "insert into limpeza (idlimpeza,\"veiculo_id\") values ("+l.getIdLimpeza()+", "+l.getVeiculo().getId()+");";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    public void edit( Limpeza l ) throws DataBaseException
    {
        if (l != null)
        {
            // criar SQL string
            String sql = "update limpeza set \"veiculo_id\"="+l.getVeiculo().getId()+" where idlimpeza="+l.getIdLimpeza()+";";

            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    public void delete( Limpeza l)  throws DataBaseException
    {
        if (l != null)
        {
            // criar SQL string
            String sql = "delete from limpeza where idlimpeza="+l.getIdLimpeza()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    @Override
    public Limpeza read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from limpeza where idlimpeza="+id+";";
        Limpeza l = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);
            
            if (rs.isBeforeFirst())
            {
                rs.next();
                int     idLimpeza        = rs.getInt("idlimpeza");
                int     veiculo_id       = rs.getInt("veiculo_id");
                  
                EstacionamentoVeiculoBD bd1      = BDFactory.newVeiculoBD();
                Veiculo veiculo                  = bd1.read(veiculo_id);

                l = new Limpeza(idLimpeza, veiculo);
            }
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return l;
    }
    
    public ArrayList<Limpeza> readAll() throws DataBaseException
    {
        ArrayList<Limpeza> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from limpeza;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int     idLimpeza        = rs.getInt("idvaga");
                    int     veiculo_id       = rs.getInt("veiculo_id");
                
                    EstacionamentoVeiculoBD bd1      = BDFactory.newVeiculoBD();
                    Veiculo veiculo                  = bd1.read(veiculo_id);
                    
                    Limpeza l = new Limpeza(idLimpeza, veiculo);
                    temp.add(l);
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
    public ArrayList<Limpeza> read(Filter filter) throws DataBaseException
    {
        ArrayList<Limpeza> limpezasFiltros = new ArrayList();
        ArrayList<Limpeza> limpezas = this.readAll();
        for (Limpeza limpeza: limpezas)
        {
            if (filter.isApproved(limpeza))
            {
                limpezasFiltros.add(limpeza);
            }
        }
        return limpezasFiltros;
    }
}