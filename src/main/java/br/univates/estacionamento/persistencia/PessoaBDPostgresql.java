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
import br.univates.estacionamento.negocio.Vip;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaoh
 */
public class PessoaBDPostgresql implements EstacionamentoPessoaBD
{
    private DataBaseConnectionManager conexao;
    
    public PessoaBDPostgresql() throws DataBaseException
    {
        this.conexao = new DataBaseConnectionManager( DataBaseConnectionManager.POSTGRESQL, "Estacionamento_BD", "postgres", "9611");
        conexao = Sistema.getInstance().getDataBaseManager();
    }
    
    @Override
    public void create( Pessoa cp ) throws DataBaseException,DuplicateKeyException
    {
        if (cp != null)
        {
            // criar SQL string
            String sql = "insert into pessoa (idpessoa,nome,endereco,cpf,idade,\"vip_id\",logradouro) values ("+cp.getId()+", '"+cp.getNome()+"', '"+cp.getEndereco()+"' , '"+cp.getCPF()+"' , "+cp.getIdade()+" , "+cp.getVip().getIdVip()+" , '"+cp.getLogradouro()+"' );";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
    }
    
    @Override
    public void edit( Pessoa cp ) throws DataBaseException
    {
        if (cp != null)
        {
            // criar SQL string
            String sql = "update pessoa set nome='"+cp.getNome()+"', endereco='"+cp.getEndereco()+"', idade="+cp.getIdade()+", cpf='"+cp.getCPF()+"', \"vip_id\"="+cp.getVip().getIdVip()+" , logradouro ='"+cp.getLogradouro()+"' where idpessoa="+cp.getId()+";";
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Cadastro nulo");
        }
    }
    
    @Override
    public void delete( Pessoa cp ) throws DataBaseException
    {
        if (cp != null)
        {
            // criar SQL string
            String sql = "delete from pessoa where idpessoa="+cp.getId()+";";
                
            conexao.runSQL(sql);
        }
        else
        {
            throw new DataBaseException("Contato nulo");
        }
    }
    
    @Override
    public Pessoa read( int id ) throws DataBaseException
    {
        // criar SQL string
        String sql = "select * from pessoa where idpessoa="+id+";";
        Pessoa cp = null;
        
        try
        {
            ResultSet rs = conexao.runQuerySQL(sql);
            
            if (rs.isBeforeFirst())
            {
                rs.next();
                int cod             = rs.getInt("idpessoa");
                String nome         = rs.getString("nome");
                String endereco     = rs.getString("endereco");
                String cpf          = rs.getString("cpf");
                int idade           = rs.getInt("idade");
                int idVip           = rs.getInt("vip_id");
                String logradouro   = rs.getString("logradouro");
                
                EstacionamentoVipBD bd = BDFactory.newVipBD();
                Vip vip                = bd.read(idVip);
                
                cp = new Pessoa(cod, nome, endereco, cpf, idade, vip, logradouro);
            } 
        }
        catch (SQLException ex) 
        {
            throw new DataBaseException( ex.getMessage() );
        }
        return cp;
    }
    
    @Override
    public ArrayList<Pessoa> readAll() throws DataBaseException
    {
        ArrayList<Pessoa> temp = new ArrayList();
        
        try {
            // criar SQL string
            String sql = "select * from pessoa order by nome;";

            // executar SQL
            ResultSet rs = conexao.runQuerySQL(sql);

            if (rs.isBeforeFirst())
            {
                while (rs.next())
                {
                    int id              = rs.getInt("idpessoa");
                    String nome         = rs.getString("nome");
                    String endereco     = rs.getString("endereco");
                    String cpf          = rs.getString("cpf");
                    int idade           = rs.getInt("idade");
                    int idVip           = rs.getInt("vip_id");
                    String logradouro   = rs.getString("logradouro");
                    
                    EstacionamentoVipBD bd = BDFactory.newVipBD();
                    Vip vip                 = bd.read(idVip);
                    
                    Pessoa cp = new Pessoa(id, nome, endereco, cpf, idade, vip, logradouro);
                    temp.add(cp);
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
    public ArrayList<Pessoa> read(Filter filter) throws DataBaseException
    {
        ArrayList<Pessoa> pessoasFiltradas = new ArrayList();
        ArrayList<Pessoa> pessoas = this.readAll();
        for (Pessoa pessoa: pessoas)
        {
            if (filter.isApproved(pessoa))
            {
                pessoasFiltradas.add(pessoa);
            }
        }
        return pessoasFiltradas;
    }
}