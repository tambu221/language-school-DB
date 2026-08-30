package it.uniroma2.bd.Model.Domain;


public class Credentials {
     private final String Username;
     private final String Password;
     private final Role role;

     public Credentials(String Username,String Password,Role role){
         this.Username=Username;
         this.Password=Password;
         this.role=role;
     }

     public String getUsername() {return Username;}

     public String getPassword() {return Password;}

     public Role getRole() {return role;}


}

