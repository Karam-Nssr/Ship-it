package Problem1.Model;

import java.util.Date;

public class Shipment {
    int id;
    String location;
    double cost;
    Date date;
    public Shipment(int id,String location,double cost,Date date){
        this.id=id;
        this.location=location;
        this.cost=cost;
        this.date=date;
    }
}
