/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.persistencia;

import br.univates.system32.db.DataBaseConnectionManager;
import br.univates.system32.db.DataBaseException;
import br.univates.system32.db.DuplicateKeyException;
import br.univates.system32.db.Filter;
import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Pessoa;
import br.univates.estacionamento.negocio.Veiculo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaoh
 */
public class VeiculoBDPostgresql implements EstacionamentoVeiculoBD
{
    private DataBaseConnectionManager conexao;
    
    public VeiculoBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "Estacionamento_BD", "postgres", "9611");
        conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Veiculo v ) throws DataBaseException,DuplicateKeyException
    {
        if (v != null)
        {
            // criar SQL string
            String sql = "insert into veiculo (idveiculo,placa,modelo,\"pessoa_id\") values ("+v.getId()+", '"+v.getPlaca()+"', '"+v.getModelo()+"', "+v.getPessoa().getId()+");";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
    }
    
    @Override
    public void edit( Veiculo v ) throws DataBaseException
    {
        if (v != null)
        {
            // criar SQL string
            String sql = "update veiculo set placa='"+v.getPlaca()+"', modelo='"+v.getModelo()+"', \"pessoa_id\"="+v.getPessoa().getId()+" where idveiculo="+v.getId()+";";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
    }
    
    @Override
    public void delete( Veiculo v ) throws DataBaseException
    {
        if (v != null)
        {
            // criar SQL string
            String sql = "delete from veiculo where idveiculo="+v.getId()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
    }
    
    @Override
    public Veiculo read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from veiculo where idveiculo="+id+";";
        Veiculo ca = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                rs.next();
                int cod              = rs.getInt("idveiculo");
                String placa         = rs.getString("placa");
                String modelo        = rs.getString("modelo");
                int idPessoa         = rs.getInt("pessoa_id");
                
                EstacionamentoPessoaBD bd = BDFactory.newPessoaBD();
                Pessoa pessoa             = bd.read(idPessoa);
                
                ca =  new Veiculo(cod, placa, modelo, pessoa);
            }
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return ca;
    }
    
    @Override
    public ArrayList<Veiculo> readAll() throws DataBaseException
    {
        ArrayList<Veiculo> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from veiculo;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int cod        = rs.getInt("idveiculo");
                    String placa   = rs.getString("placa");
                    String modelo  = rs.getString("modelo");
                    int idPessoa   = rs.getInt("pessoa_id");
                    
                    EstacionamentoPessoaBD bd = BDFactory.newPessoaBD();
                    Pessoa pessoa             = bd.read(idPessoa);
                
                    Veiculo ca = new Veiculo(cod, placa, modelo, pessoa);
                    temp.add(ca);
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
    public ArrayList<Veiculo> read(Filter filter) throws DataBaseException
    {
        ArrayList<Veiculo> veiculosFiltrados = new ArrayList();
        ArrayList<Veiculo> veiculos = this.readAll();
        for (Veiculo veiculo: veiculos)
        {
            if (filter.isApproved(veiculo))
            {
                veiculosFiltrados.add(veiculo);
            }
        }
        return veiculosFiltrados;
    }
}