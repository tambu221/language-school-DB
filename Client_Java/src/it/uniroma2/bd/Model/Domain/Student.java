package it.uniroma2.bd.Model.Domain;

public class Student {

    private final String name;
    private final String Surname;
    private final String number_telephone;

    public Student(String n,String s,String nt){
        this.name=n;
        this.Surname = s;
        this.number_telephone = nt;
    }

    public String getName(){ return name;}
    public String getSurname() { return Surname;}
    public String getNumber_telephone(){ return number_telephone;}
}
