/**
  * Created on 5/29/2016.
  */
object TestNaiveBays {

  def main(args:Array[String]):Unit = {




    val doc1 = new Document("Would you like to try a new all natural penis enlargement pill pill pill pill pill", Spam)
    val doc2 = new Document("Hello Johnny its your Grandma i love you", Ham)
    val doc3 = new Document("Greetings Sir Smith I am a nigerian prince who has too much money pill pill", Spam)
    val doc4 = new Document("Johnny its me from the future you need to save the world", Ham)
    val doc5 = new Document("new hot deal doctors hate me it turns out this wonderful pill can cure all muscle aches and pains while you lose weight", Spam)
    val doc6 = new Document("Hey man I was wondering if you wanted to hang out next tuesday its Geoffery", Ham)
    val doc7 = new Document("dr abechenezer wants you to turn from previous medicine and instead take up this new medication which is doing wonders costs almost nothing and is guaranteed to get you ladies", Spam)
    val doc8 = new Document("hey johnny remember to bring in the water from the back of the car", Ham)
    val doc9 = new Document("hey johnny this is your older brother and this is a strange example for the exersize",Ham)
    val doc10 = new Document("hey there all who are listening its me mr mc donnald and i want you to know about the super sale thats going on this tuesday",Spam)

    val unknown1 = new Document("hey johnny its your grandmother again I was wondering if you still loved me", Ham)
    val unknown2 = new Document("pill", Spam)
    val unknown3 = new Document("hey johnny remember to bring in the water from the back of the car", Ham)
    val unknown4 = new Document("viagra is a wonderful pill for any men who are experiencing medical issues down under", Spam)
    val unknown5 = new Document("im not really sure how this letter is going to get interpreted i think it containts a lot of non spam words but i cant be sure i just know that i love machine learning", Ham)
    val unknown6 = new Document("hey there boys and girls this is ronald mc donnald and i want you to buy as many hamburgers as possible tonight at our big sale", Spam)

    val classifier = new NaiveBays(1, doc1, doc2, doc3, doc4, doc5, doc6, doc7, doc8, doc9, doc10)

    val unknowns = Seq(unknown1, unknown2, unknown3, unknown4, unknown5, unknown6)

    unknowns foreach { uk =>
      println(s"Text:${uk.data}\nResult:${classifier.classify(uk).getClass.getName}")
    }

  }


}
