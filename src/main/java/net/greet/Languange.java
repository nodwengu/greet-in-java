package net.greet;

public enum Languange {

   ENGLISH("Hello, "), XHOSA("Mholo, "), AFRIKAANS("Goe more, "), ZULU("Sawubona, ");
   
   private String greet;
   
   Languange(String greet) {
      this.greet = greet;
   }
   
   public String getGreet() {
      return this.greet;
   }
}
