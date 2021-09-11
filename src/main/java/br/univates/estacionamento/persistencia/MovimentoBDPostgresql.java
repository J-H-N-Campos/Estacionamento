/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.persistencia;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Movimento;
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
public class MovimentoBDPostgresql implements EstacionamentoMovimentoBD
{
    private DataBaseConnectionManager conexao;
    
    public MovimentoBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "Estacionamento_BD", "postgres", "9611");
        conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Movimento mv ) throws DataBaseException,DuplicateKeyException
    {
        if (mv != null)
        {
            // criar SQL string
            String sql = "insert into movimento(idmovimento,dt_inicio,dt_fim,\"veiculo_id\") values ("+mv.getId()+", '"+mv.getDtIni()+"', '"+mv.getDtFim()+"', "+mv.getVeiculo().getId()+");";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Data Errada");
        }
    }
    
    @Override
    public void edit( Movimento mv ) throws DataBaseException
    {
        if (mv != null)
        {
            // criar SQL string
            String sql = "update movimento set dt_inicio='"+mv.getDtIni()+"', dt_fim='"+mv.getDtFim()+"', \"veiculo_id\"="+mv.getVeiculo().getId()+" where idmovimento="+mv.getId()+";";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Data Errada");
        }
    }
    
    @Override
    public void delete( Movimento mv ) throws DataBaseException
    {
        if (mv != null)
        {
            // criar SQL string
            String sql = "delete from movimento where idmovimento="+mv.getId()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
        
    }
    
    @Override
    public Movimento read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from movimento where idmovimento="+id+";";
        Movimento mv = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);
            
            if (rs.isBeforeFirst())
            {
                rs.next();
                int cod                 = rs.getInt("idmovimento");
                String dt_inicio        = rs.getString("dt_inicio");
                String dt_fim           = rs.getString("dt_fim");
                int idVeiculo           = rs.getInt("veiculo_id");
                
                EstacionamentoVeiculoBD bd = BDFactory.newVeiculoBD();
                Veiculo veiculo            = bd.read(idVeiculo);
                
                mv = new Movimento(cod, dt_inicio, dt_fim, veiculo);
            } 
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return mv;
    }
    
    @Override
    public ArrayList<Movimento> readAll() throws DataBaseException
    {
        ArrayList<Movimento> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from movimento;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int id              = rs.getInt("idmovimento");
                    String dt_inicio    = rs.getString("dt_inicio");
                    String dt_fim       = rs.getString("dt_fim");
                    int idVeiculo       = rs.getInt("veiculo_id");
                    
                    EstacionamentoVeiculoBD bd = BDFactory.newVeiculoBD();
                    Veiculo veiculo            = bd.read(idVeiculo);
                    
                    Movimento mv = new Movimento(id, dt_inicio, dt_fim, veiculo);
                    temp.add(mv);
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
    public ArrayList<Movimento> read(Filter filter) throws DataBaseException
    {
        ArrayList<Movimento> movimentosFiltrados = new ArrayList();
        ArrayList<Movimento> movimentos = this.readAll();
        for (Movimento movimento: movimentos)
        {
            if (filter.isApproved(movimento))
            {
                movimentosFiltrados.add(movimento);
            }
        }
        return movimentosFiltrados;
    }
}