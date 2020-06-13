package net.greet;

public class Person {
   private String username;
   private int greetCount;
   private int id;
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   public void setGreetCount(int greetCount) {
      this.greetCount = greetCount;
   }
   
   public void setId(int id) {
      this.id = id;
   }
   
   public String getUsername() {
      return username;
   }
   
   public int getGreetCount() {
      return greetCount;
   }
   
   public int getId() {
      return id;
   }
   
   public void increaseCounter() {
      greetCount++;
   }
   
   public void decreaseCounter() {
      if (greetCount >=  0)
         greetCount--;
   }
}
