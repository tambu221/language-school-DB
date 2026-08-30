package it.uniroma2.bd.View;

import it.uniroma2.bd.Model.Domain.Credentials;
import java.lang.System;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LoginView {

    public static Credentials autentication() throws IOException{

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the high school language System\n");
        System.out.println("Insert your Username:\n");
        String Username = buffer.readLine();
        System.out.println("Insert your password:\n");
        String Password = buffer.readLine();
        return new Credentials(Username,Password,null);
    }


}
