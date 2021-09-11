/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.negocio;

/**
 *
 * @author joaoh
 */
public class Veiculo
{
    private int    id;
    private String placa;
    private String modelo;
    private Pessoa pessoa;
    
    public Veiculo(int id, String placa, String modelo, Pessoa pessoa)
    {
        this.id     = id;
        this.placa  = placa;
        this.modelo = modelo;
        this.pessoa = pessoa;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getPlaca()
    {
        return placa;
    }
    
    public String getModelo()
    {
        return modelo;
    }
    
    public Pessoa getPessoa()
    {
        return pessoa;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }
    
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }
    
    public void setPessoa(Pessoa pessoa)
    {
        this.pessoa = pessoa;
    }
      
    @Override
    public String toString()
    {
        return this.placa;
    }
    
    public boolean equals(Object veiculo)
    {
        return this.id == ((Veiculo)veiculo).id;
    }
}