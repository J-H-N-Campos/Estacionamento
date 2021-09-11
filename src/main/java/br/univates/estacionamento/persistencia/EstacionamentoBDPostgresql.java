/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.persistencia;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Caixa;
import br.univates.estacionamento.negocio.Estacionamento;
import br.univates.estacionamento.negocio.Movimento;
import br.univates.estacionamento.negocio.Pessoa;
import br.univates.estacionamento.negocio.Veiculo;
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
public class EstacionamentoBDPostgresql implements EstacionamentoBD
{
    private DataBaseConnectionManager conexao;
    
    public EstacionamentoBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "Estacionamento_BD", "postgres", "9611");
        this.conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Estacionamento e ) throws DataBaseException,DuplicateKeyException
    {
        if (e != null)
        {
            // criar SQL string
            String sql = "insert into vaga (idvaga,\"pessoa_id\",\"veiculo_id\",\"movimento_id\",\"vip_id\",\"caixa_id\") values ("+e.getIdVaga()+", "+e.getPessoa().getId()+", "+e.getVeiculo().getId()+", "+e.getMovimento().getId()+", "+e.getVip().getIdVip()+", "+e.getCaixa().getIdCaixa()+");";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    public void edit( Estacionamento e ) throws DataBaseException
    {
        if (e != null)
        {
            // criar SQL string
            String sql = "update vaga set \"pessoa_id\"="+e.getPessoa().getId()+", \"veiculo_id\"="+e.getVeiculo().getId()+", \"movimento_id\"="+e.getMovimento().getId()+", \"vip_id\"="+e.getVip().getIdVip()+", \"caixa_id\"="+e.getCaixa().getIdCaixa()+" where idvaga="+e.getIdVaga()+";";

            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    public void delete( Estacionamento e)  throws DataBaseException
    {
        if (e != null)
        {
            // criar SQL string
            String sql = "delete from vaga where idvaga="+e.getIdVaga()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Erro");
        }
    }
    
    @Override
    public Estacionamento read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from vaga where idvaga="+id+";";
        Estacionamento e = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);
            
            if (rs.isBeforeFirst())
            {
                rs.next();
                int     idVaga           = rs.getInt("idvaga");
                int     pessoa_id        = rs.getInt("pessoa_id");
                int     veiculo_id       = rs.getInt("veiculo_id");
                int     movimento_id     = rs.getInt("movimento_id");
                int     vip_id           = rs.getInt("vip_id");
                int     caixa_id         = rs.getInt("caixa_id");
                
                EstacionamentoPessoaBD bd       = BDFactory.newPessoaBD();
                Pessoa pessoa                   = bd.read(pessoa_id);
                
                EstacionamentoVeiculoBD bd1      = BDFactory.newVeiculoBD();
                Veiculo veiculo                  = bd1.read(veiculo_id);
                
                EstacionamentoMovimentoBD bd2    = BDFactory.newMovimentoBD();
                Movimento movimento              = bd2.read(movimento_id);
                
                EstacionamentoVipBD bd3          = BDFactory.newVipBD();
                Vip vip                          = bd3.read(vip_id);
                
                EstacionamentoCaixaBD bd4        = BDFactory.newCaixaBD();
                Caixa caixa                       = bd4.read(caixa_id);

                e = new Estacionamento(idVaga, pessoa, veiculo, movimento, vip, caixa);
            }
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return e;
    }
    
    public ArrayList<Estacionamento> readAll() throws DataBaseException
    {
        ArrayList<Estacionamento> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from vaga;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int     idVaga           = rs.getInt("idvaga");
                    int     pessoa_id        = rs.getInt("pessoa_id");
                    int     veiculo_id       = rs.getInt("veiculo_id");
                    int     movimento_id     = rs.getInt("movimento_id");
                    int     vip_id           = rs.getInt("vip_id");
                    int     caixa_id         = rs.getInt("caixa_id");
                    
                    EstacionamentoPessoaBD bd       = BDFactory.newPessoaBD();
                    Pessoa pessoa                   = bd.read(pessoa_id);
                
                    EstacionamentoVeiculoBD bd1      = BDFactory.newVeiculoBD();
                    Veiculo veiculo                  = bd1.read(veiculo_id);
                
                    EstacionamentoMovimentoBD bd2    = BDFactory.newMovimentoBD();
                    Movimento movimento              = bd2.read(movimento_id);
                
                    EstacionamentoVipBD bd3          = BDFactory.newVipBD();
                    Vip vip                          = bd3.read(vip_id);
                    
                    EstacionamentoCaixaBD bd4        = BDFactory.newCaixaBD();
                    Caixa caixa                      = bd4.read(caixa_id);
                    
                    Estacionamento e = new Estacionamento(idVaga, pessoa, veiculo, movimento, vip, caixa);
                    temp.add(e);
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
    public ArrayList<Estacionamento> read(Filter filter) throws DataBaseException
    {
        ArrayList<Estacionamento> estacionamentosFiltros = new ArrayList();
        ArrayList<Estacionamento> estacionamentos = this.readAll();
        for (Estacionamento estacionamento: estacionamentos)
        {
            if (filter.isApproved(estacionamento))
            {
                estacionamentosFiltros.add(estacionamento);
            }
        }
        return estacionamentosFiltros;
    }
}