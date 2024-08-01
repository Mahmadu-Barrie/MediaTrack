abstract class Entertainment {
   private String attributeType;

   private String uniqueId;
   private String title;
   private String person;
   private double cost;

   private String haveIt;

   // most have alot in common up to
   private String comment;

   private String Date;

   private int numberOfPlayers;

   // constructor

   protected Entertainment(String attributeType,String uniqueId, String title, String person, double cost, String haveIt, String comment){
      this.attributeType = attributeType;
      this.uniqueId = uniqueId;
      this.title = title;
      this.person = person;
      this.cost =cost;
      this.haveIt = haveIt;
      this.comment = comment;

   }

   // Accessor methods for each attribute

   public String getAttributeType() {
      return this.attributeType;

   }

   public String getUniqueId() {
      return this.uniqueId;
   }



   public String getTitle() {
      return this.title;

   }

   public String getPerson() {
      return this.person;

   }

   public double getCost() {
      return this.cost;

   }

   public String gethaveIt() {
      return this.haveIt;
   }

   public String getComment() {
      return this.comment;

   }

   // Modifier methods
   public void setTitle(String newTitle) {
      this.title = newTitle;

   }

   public void setPerson(String newPerson) {
      this.person = newPerson;

   }

   public void setCost(double newCost) {
      this.cost = newCost;

   }

   public void setHaveIt(String newStatus) {
      this.haveIt = newStatus;
   }

   public void setComment(String newComment) {
      this.comment = newComment;

   }
   // toString Method

   public String toString(){
      String res ="";
      res+= "type: "+ this.getAttributeType()+ " uniqueID: "+ this.getUniqueId() +" title: "+ this.getTitle()+ " person: "+ this.getPerson()+" cost: "+ this.getCost()+
              " haveIt: "+ this.gethaveIt();

      return res;
   }

}