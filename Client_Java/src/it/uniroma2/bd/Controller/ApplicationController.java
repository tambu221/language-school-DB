package it.uniroma2.bd.Controller;


import it.uniroma2.bd.Model.Domain.Credentials;
import it.uniroma2.bd.Model.Domain.Role.*;
import static it.uniroma2.bd.Model.Domain.Role.INSEGNANTE;
import static it.uniroma2.bd.Model.Domain.Role.AMMINISTRATORE;
import static it.uniroma2.bd.Model.Domain.Role.SEGRETERIA;
import it.uniroma2.bd.Controller.AmministratoreController;



public class ApplicationController {
    Credentials cred;

    public void start(){
        LoginController loginController= new LoginController();
        loginController.start();
        cred=loginController.getCred();

        if(cred.getRole()==null){
            throw new RuntimeException("invalid credentials");
        }

       switch(cred.getRole()){
            case AMMINISTRATORE -> new AmministratoreController().start();
            case SEGRETERIA -> new SegretaryController().start();
            case INSEGNANTE -> new ProfessorController().start();


       }
    }

}
