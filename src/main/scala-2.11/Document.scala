/**
  * Created on 5/28/2016.
  */
class Document(val data:String, val label: Label = NoLabel) {
  val words = data.toLowerCase.split(' ').foldLeft(Map[String,Int]())((counts,str)=>{
    counts + (str -> (counts.getOrElse(str,0)+1))
  })
}
