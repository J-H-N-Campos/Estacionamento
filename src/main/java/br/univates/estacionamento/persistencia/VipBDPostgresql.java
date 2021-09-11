/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.persistencia;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Vip;
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
public class VipBDPostgresql implements EstacionamentoVipBD
{
    private DataBaseConnectionManager conexao;
    
    public VipBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "agenda", "postgres", "mhd102030");
        this.conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Vip vip ) throws DataBaseException,DuplicateKeyException
    {
        if (vip != null)
        {
            // criar SQL string
            String sql = "insert into vip values ("+vip.getIdVip()+", '"+vip.getDescricao()+"' );";

            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Vip null");
        }
    }
    
    public void edit( Vip vip ) throws DataBaseException
    {
        if (vip != null)
        {
            // criar SQL string
            String sql = "update vip set descricao='"+vip.getDescricao()+"' where idvip="+vip.getIdVip()+";";

            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Vip null");
        }
    }
    
    public void delete( Vip vip)  throws DataBaseException
    {
        if (vip != null)
        {
            // criar SQL string
            String sql = "delete from vip where idvip="+vip.getIdVip()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Vip null");
        }
    }
    
    @Override
    public Vip read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from vip where idvip="+id+";";
        Vip v = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);
            
            if (rs.isBeforeFirst())
            {
                rs.next();
                int idVip        = rs.getInt("idvip");
                String descricao = rs.getString("descricao");
                
                v = new Vip(idVip,descricao);
            }
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return v;
    }
    
    public ArrayList<Vip> readAll() throws DataBaseException
    {
        ArrayList<Vip> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from vip;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int idVip        = rs.getInt("idvip");
                    String descricao = rs.getString("descricao");
                                        
                    Vip v = new Vip(idVip,descricao);
                    temp.add(v);
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
    public ArrayList<Vip> read(Filter filter) throws DataBaseException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}