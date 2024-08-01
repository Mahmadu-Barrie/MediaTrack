public class Game extends Entertainment{



    public String Date;
    public int numberOfPlayers;

    // constructor
    public Game(String attributeType,String uniqueId, String title, String person, double cost, String haveIt, String comment,String Date, int numberOfPlayers){
        super(attributeType,uniqueId,title,person,cost,haveIt,comment);
        this.numberOfPlayers = numberOfPlayers;
        this.Date = Date;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String newDate) {
        this.Date = newDate;
    }

    public double getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public void setnumberOfPlayers(int newNum) {
        this.numberOfPlayers = newNum;
    }

    public String toString(){
        String res = super.toString();

        res+= " date: "+ this.getDate()+ " numberOfPlayers: "+ this.getNumberOfPlayers();

        return res;
    }




}
