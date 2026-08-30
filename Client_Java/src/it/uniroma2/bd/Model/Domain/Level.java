package it.uniroma2.bd.Model.Domain;

public class Level {
    private String name_level;
    private String bookName;
    private boolean exam;

    public Level(String name,String book,boolean exam){
        this.name_level=name;
        this.bookName=book;
        this.exam = exam;
    }

    public String getNameLevel(){ return name_level;}

    public String getBookName(){ return bookName;}

    public Boolean getExam(){ return exam;}

}
