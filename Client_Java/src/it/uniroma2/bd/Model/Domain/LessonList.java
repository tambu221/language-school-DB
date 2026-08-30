package it.uniroma2.bd.Model.Domain;
import java.util.ArrayList;
import java.util.List;


public class LessonList {
    List<Lesson> LessonList= new ArrayList<>();


    public void addLesson(Lesson l){
        this.LessonList.add(l);
    }

    public int getSize(){return this.LessonList.size();}

    public Lesson getItem(int i){ return this.LessonList.get(i);}
}
