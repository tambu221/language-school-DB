package it.uniroma2.bd.Model.Domain;

import java.util.ArrayList;
import java.util.List;

public class ReportList {

    List<Report> reportList = new ArrayList<>();

    public void addReport(Report r){ this.reportList.add(r);}

    public int getSize(){return this.reportList.size();}

    public Report getItem(int i){ return this.reportList.get(i);}
}
