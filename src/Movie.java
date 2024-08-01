public class Movie extends Entertainment {
    public String Date;

    // constructor
    public Movie(String attributeType,String uniqueId, String title, String person, double cost, String haveIt, String comment,String Date){
        super(attributeType,uniqueId,title,person,cost,haveIt,comment);
        this.Date = Date;
    }
    public String getDate() {
        return this.Date;
    }

    public void setDate(String newDate) {
        this.Date = newDate;
    }

    public String toString(){
        String res = super.toString();

        res+= " date: "+ this.getDate();

        return res;
    }



}
