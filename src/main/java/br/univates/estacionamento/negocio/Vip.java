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
public class Vip
{
    private int    idVip;
    private String descricao;
    
    public Vip(int idVip, String descricao)
    {
        this.idVip     = idVip;
        this.descricao = descricao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public int getIdVip()
    {
        return idVip;
    }

    public void setIdVip(int idVip)
    {
        this.idVip = idVip;
    }
    
    @Override
    public String toString()
    {
        return this.descricao;
    }
    
    public boolean equals(Object vip)
    {
        return this.idVip == ((Vip)vip).idVip;
    }
}
