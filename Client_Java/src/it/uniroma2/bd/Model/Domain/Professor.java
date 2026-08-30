package it.uniroma2.bd.Model.Domain;

public class Professor {
   private String Name;
   private String Nationality;
   private String Address;


   public Professor(String name,String Nationality,String Address){
       this.Name = name;
       this.Nationality = Nationality;
       this.Address = Address;
   }

   public String getName(){ return Name;}
   public String getNationality(){ return Nationality;}
   public String getAddress(){ return Address;}
}
