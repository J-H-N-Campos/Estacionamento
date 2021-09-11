/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.persistencia;

import br.univates.system32.db.DataBaseException;

/**
 *
 * @author joaoh
 */
public class BDFactory
{
    public static EstacionamentoVeiculoBD newVeiculoBD() throws DataBaseException
    {
        return (EstacionamentoVeiculoBD) new VeiculoBDPostgresql();       
    }
    
    public static EstacionamentoPessoaBD newPessoaBD() throws DataBaseException
    {
        return (EstacionamentoPessoaBD) new PessoaBDPostgresql();       
    }
    
    public static EstacionamentoUsuarioBD newUsuarioBD() throws DataBaseException
    {
        return (EstacionamentoUsuarioBD) new UsuarioBDPostgresql();       
    }
    
    public static EstacionamentoVipBD newVipBD() throws DataBaseException
    {
        return (EstacionamentoVipBD) new VipBDPostgresql();       
    }
    
    public static EstacionamentoMovimentoBD newMovimentoBD() throws DataBaseException
    {
        return (EstacionamentoMovimentoBD) new MovimentoBDPostgresql();       
    }
    
    public static EstacionamentoBD newEstacionamentoBD() throws DataBaseException
    {
        return (EstacionamentoBD) new EstacionamentoBDPostgresql();       
    }
    
    public static EstacionamentoCaixaBD newCaixaBD() throws DataBaseException
    {
        return (EstacionamentoCaixaBD) new CaixaBDPostgresql();       
    }
    
    public static EstacionamentoLimpezaBD newLimpezaBD() throws DataBaseException
    {
        return (EstacionamentoLimpezaBD) new LimpezaBDPostgresql();       
    }
}
