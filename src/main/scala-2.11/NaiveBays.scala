/**
  * Created on 5/28/2016.
  */
class NaiveBays(threshold:Double, documents:Document*) {

  def appendCounts[A](map1:Map[A,Int],map2:Map[A,Int]):Map[A,Int] = map1 ++ map2.map{ case (k,v) => k -> (v + map1.getOrElse(k,0)) }

  //********************************************************************************************************************
  //BEGIN TRAINING
  //********************************************************************************************************************

  //overall mapping for all docs
  val totalWords = documents.foldLeft(Map[String,Int]()){case (map,doc)=> appendCounts(map,doc.words)}

  //count label occurences
  val (mHam,mSpam) = documents.foldLeft(0,0){case ((mH,mS),doc)=>
    doc.label match {
      case Spam => (mH,mS+1)
      case Ham => (mH+1,mS)
    }
  }

  val initCountMap = totalWords map {case (k,v)=> k -> 1}

  val (spamMap,hamMap) = documents.foldLeft(initCountMap,initCountMap){case ((sMap,hMap),doc) =>
    doc.label match {
      case Spam=> (appendCounts(sMap,doc.words),hMap)
      case Ham => (sMap,appendCounts(hMap,doc.words))
    }
  }

  val spamWordCount = spamMap.foldLeft(0){case (acc,(_,value))=>acc+value}.toDouble
  val hamWordCount = hamMap.foldLeft(0){case (acc,(_,value))=>acc+value}.toDouble

  val wordProbDiffs = totalWords map {case (word,_)=> word -> (Math.log(spamMap(word)/spamWordCount)-Math.log(hamMap(word)/hamWordCount))}

  /*
  println(
    s"""
       |*************************
       |${wordProbDiffs.foldLeft(""){case (a,(k,v))=>a + s"$k\t$v\n"}}
       |*************************
     """.stripMargin)
     */

  //********************************************************************************************************************
  //END TRAINING
  //********************************************************************************************************************

  def classify(doc:Document):Label = {
    val classValue = totalWords.keys.foldLeft(-(Math.log(threshold)+Math.log(mHam)-Math.log(mSpam))){
      case (acc,word) => acc + doc.words.getOrElse(word,0) * wordProbDiffs(word)
    }

    //println(s"ClassValue:$classValue")

    if(classValue>0)
      Spam
    else
      Ham

  }

}
