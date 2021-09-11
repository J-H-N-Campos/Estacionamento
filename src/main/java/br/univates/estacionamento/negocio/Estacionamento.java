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
public class Estacionamento
{
    private int             idVaga;
    private Pessoa          pessoa_id;
    private Veiculo         veiculo_id;
    private Movimento       movimento_id;
    private Vip             vip_id;
    private Caixa           caixa_id;
    
    public Estacionamento(int idVaga, Pessoa pessoa_id, Veiculo veiculo_id, Movimento movimento_id, Vip vip_id, Caixa caixa_id)
    {
        this.idVaga             = idVaga;
        this.pessoa_id          = pessoa_id;
        this.veiculo_id         = veiculo_id;
        this.movimento_id       = movimento_id;
        this.vip_id             = vip_id;
        this.caixa_id           = caixa_id;
    }

    public int getIdVaga()
    {
        return idVaga;
    }
    
    public Pessoa getPessoa()
    {
        return pessoa_id;
    }
    
    public Veiculo getVeiculo()
    {
        return veiculo_id;
    }
    
    public Movimento getMovimento()
    {
        return movimento_id;
    }
    
    public Vip getVip()
    {
        return vip_id;
    }
    
    public Caixa getCaixa()
    {
        return caixa_id;
    }
    
    public void setIdVaga(int idVaga)
    {
        this.idVaga = idVaga;
    }
    
    public void setPessoa(Pessoa pessoa_id)
    {
        this.pessoa_id = pessoa_id;
    }

    public void setVeiculo(Veiculo veiculo_id)
    {
        this.veiculo_id = veiculo_id;
    }
    
    public void setMovimento(Movimento movimento_id)
    {
        this.movimento_id = movimento_id;
    }
    
    public void setVip(Vip vip_id)
    {
        this.vip_id = vip_id;
    }
    
    public void setCaixa(Caixa caixa_id)
    {
        this.caixa_id = caixa_id;
    }
    
    @Override
    public String toString()
    {
        return idVaga+";"+pessoa_id+";"+veiculo_id+";"+movimento_id+";"+vip_id+";"+caixa_id;
    }
    
    public boolean equals(Object estacionamento)
    {
        return this.idVaga == ((Estacionamento)estacionamento).idVaga;
    }
}