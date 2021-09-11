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
public class Movimento
{
    private int id;
    private String dtIni;
    private String dtFim;
    private Veiculo veiculo;

    public Movimento(int id, String dtIni, String dtFim, Veiculo veiculo)
    {
        this.id          = id;
        this.dtIni       = dtIni;
        this.dtFim       = dtFim;
        this.veiculo     = veiculo;
    }
    
    public int getId() 
    {
        return id;
    }

    public String getDtIni() 
    {
        return dtIni;
    }

    public String getDtFim()
    {
        return dtFim;
    }
    
    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setDtIni(String dtIni)
    {
        this.dtIni = dtIni;
    }
    
    public void setDtFim(String dtFim)
    {
        this.dtFim = dtFim;
    }

    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    @Override
    public String toString()
    {
        return "Início:"+" "+dtIni+" "+"Final:"+" "+dtFim;
    }
    
    public boolean equals(Object movimento)
    {
        return this.id == ((Movimento)movimento).id;
    }
}