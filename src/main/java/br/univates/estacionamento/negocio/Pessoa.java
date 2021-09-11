/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.negocio;

import javax.swing.JOptionPane;

/**
 *
 * @author joaoh
 */
public class Pessoa
{
    private int    id;
    private String nome;
    private String endereco;
    private String cpf;
    private int    idade;
    private Vip    vip;
    private String logradouro;
    
    public Pessoa(int id, String nome , String endereco, String cpf, int idade, Vip vip, String logradouro)
    {
        this.id         = id;
        this.nome       = nome;
        this.endereco   = endereco;
        this.cpf        = cpf;
        this.idade      = idade;
        this.vip        = vip;
        this.logradouro = logradouro;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public String getEndereco()
    {
        return endereco;
    }
    
    public int getIdade()
    {
        return idade;
    }
    
    public String getCPF()
    {
        return cpf;
    }
    
    public Vip getVip()
    {
        return vip;
    }
    
    public String getLogradouro()
    {
        return logradouro;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }
    
    public void setIdade(int idade)
    {
        if(idade >= 18 && idade <= 100)
        {
            this.idade = idade;
        }else
        {
            JOptionPane.showMessageDialog(null, "Idade Inválida");
        }
    }
    
    public void setCPF(String cpf)
    {
        this.cpf = cpf;
    }
    
    public void setVip(Vip vip)
    {
        this.vip = vip;
    }
    
    public void setLogradouro(String logradouro)
    {
        this.logradouro = logradouro;
    }

    @Override
    public String toString()
    {
        return this.nome;
    }
    
    public boolean equals(Object pessoa)
    {
        return this.id == ((Pessoa)pessoa).id;
    }
}