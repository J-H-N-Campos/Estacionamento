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
public class Caixa
{ 
    private int    idCaixa;
    private String valor;
    private String descricao;
    
    public Caixa(int idCaixa, String valor, String descricao)
    {
        this.idCaixa   = idCaixa;
        this.valor     = valor;
        this.descricao = descricao;
    }
    
    public int getIdCaixa()
    {
        return idCaixa;
    }
    
    public void setIdCaixa(int idCaixa)
    {
        this.idCaixa = idCaixa;
    }

    public String getValor()
    {
        return valor;
    }

    public void setValor(String valor)
    {
        this.valor = valor;
    }
    
    public String getDescricao()
    {
        return descricao;
    }
    
    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }
    
    @Override
    public String toString()
    {
        return "Valor:"+descricao+" "+"Preço:"+valor;
    }
    
    public boolean equals(Object caixa)
    {
        return this.idCaixa == ((Caixa)caixa).idCaixa;
    }
}