/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.negocio;

import br.univates.authenticator.GenericUser;

/**
 *
 * @author joaoh
 */
public class Usuario implements GenericUser
{
    private int id;
    private String login;
    private String password;
    
    public Usuario(int id, String login, String password)
    {
        this.id       = id;
        this.login    = login;
        this.password = password.hashCode()+"";
    }
    
    public Usuario(String login)
    {
        this.login = login;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }
    
    @Override
    public String toString()
    {
        return id+";"+login+";"+password;
    }
}