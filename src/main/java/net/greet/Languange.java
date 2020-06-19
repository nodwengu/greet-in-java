package net.greet;

public enum Languange {

   ENGLISH("Hello, "), XHOSA("Mholo, "), AFRIKAANS("Goe more, ");
   
   // declaring private variable for getting values
   private String greet;
   
   Languange(String greet) {
      this.greet = greet;
   }
   
   // getter method
   public String getGreet()
   {
      return this.greet;
   }
   
   
}
