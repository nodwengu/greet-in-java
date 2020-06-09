package net.greet;

public class Person {
   private String username;
   private int greetCount;
   
   public Person() {
      super();
   }
   
   public Person(String username, int greetCount) {
      this.username = username;
      this.greetCount = greetCount;
   }
   
   public void set(String username, int count) {
      this.username = username;
      this.greetCount = count;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   public void setGreetCount(int greetCount) {
      this.greetCount = greetCount;
   }
   
   public String getUsername() {
      return username;
   }
   
   public int getGreetCount() {
      return greetCount;
   }
   
   public void updateCounter() {
      greetCount++;
   }
   
   @Override
   public String toString() {
      return String.format("User [name=%s, counter=%s]", username, greetCount);
   }
   
}
