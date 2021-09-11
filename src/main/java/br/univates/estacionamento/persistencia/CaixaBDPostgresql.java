/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.persistencia;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Caixa;
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
public class CaixaBDPostgresql implements EstacionamentoCaixaBD
{
    
    private DataBaseConnectionManager conexao;
    
    public CaixaBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "agenda", "postgres", "mhd102030");
        this.conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Caixa caixa ) throws DataBaseException,DuplicateKeyException
    {
        if (caixa != null)
        {
            // criar SQL string
            String sql = "insert into caixa values ("+caixa.getIdCaixa()+", '"+caixa.getValor()+"', '"+caixa.getDescricao()+"' );";

            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    public void edit( Caixa caixa ) throws DataBaseException
    {
        if (caixa != null)
        {
            // criar SQL string
            String sql = "update caixa set valor='"+caixa.getValor()+"', descricao='"+caixa.getDescricao()+"' where idcaixa="+caixa.getIdCaixa()+";";

            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    public void delete( Caixa caixa)  throws DataBaseException
    {
        if (caixa != null)
        {
            // criar SQL string
            String sql = "delete from caixa where idcaixa="+caixa.getIdCaixa()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    @Override
    public Caixa read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from caixa where idcaixa="+id+";";
        Caixa c = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);
            
            if (rs.isBeforeFirst())
            {
                rs.next();
                int idCaixa      = rs.getInt("idcaixa");
                String valor     = rs.getString("valor");
                String descricao = rs.getString("descricao");
                
                c = new Caixa(idCaixa,valor,descricao);
            }
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return c;
    }
    
    public ArrayList<Caixa> readAll() throws DataBaseException
    {
        ArrayList<Caixa> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from caixa;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int    idCaixa   = rs.getInt("idcaixa");
                    String valor     = rs.getString("valor");
                    String descricao = rs.getString("descricao");
                                        
                    Caixa v = new Caixa(idCaixa,valor,descricao);
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
    public ArrayList<Caixa> read(Filter filter) throws DataBaseException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
