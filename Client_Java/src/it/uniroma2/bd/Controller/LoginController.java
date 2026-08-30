package it.uniroma2.bd.Controller;

import it.uniroma2.bd.Model.DAO.*;
import it.uniroma2.bd.Model.Domain.Credentials;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import it.uniroma2.bd.View.LoginView;
import it.uniroma2.bd.exception.*;

import java.io.IOException;

public class LoginController {
    Credentials cred = null;

    public void start(){
        try{
           cred = LoginView.autentication();
        }
        catch(IOException e){
          throw new RuntimeException(e);
        }
   
        try {
        cred=LoginDao.getRoleDAO(cred);

     }catch(DAOException e) {
    	throw new RuntimeException(e);
    }
    }
    public Credentials getCred() { return cred;}
}
