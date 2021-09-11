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
public class Limpeza
{
    private int     idLimpeza;
    private Veiculo veiculo;
    
    public Limpeza(int idLimpeza, Veiculo veiculo)
    {
        this.idLimpeza   = idLimpeza;
        this.veiculo     = veiculo;
    }
    
    public int getIdLimpeza()
    {
        return idLimpeza;
    }
    
    public void setIdLimpeza(int idLimpeza)
    {
        this.idLimpeza = idLimpeza;
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }
    
    @Override
    public String toString()
    {
        return this.veiculo+" ";
    }
    
    public boolean equals(Object limpeza)
    {
        return this.idLimpeza == ((Limpeza)limpeza).idLimpeza;
    }
}