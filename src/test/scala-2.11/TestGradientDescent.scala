import GradientDescent.TrainingData

/**
  * Created by nalta on 6/11/2016.
  */
object TestGradientDescent {

  def main(args:Array[String]):Unit = {
    val gradDec = new GradientDescent(0.01,
      TrainingData(Seq(1,1),2),
      TrainingData(Seq(2,2),4),
      TrainingData(Seq(2,6),8),
      TrainingData(Seq(15,2),17),
      TrainingData(Seq(2,15),17),
      TrainingData(Seq(6,6),12)
    )
    println(s"2+4 ~= ${gradDec.query(Seq(2,4))}")
    println(s"1+1 ~= ${gradDec.query(Seq(1,1))}")
    println(s"10+15 ~= ${gradDec.query(Seq(10,15))}")


    val identity = new GradientDescent(0.001,
      TrainingData(Seq(1),1),
      TrainingData(Seq(2),2),
      TrainingData(Seq(4),4),
      TrainingData(Seq(7),7),
      TrainingData(Seq(17),17),
      TrainingData(Seq(45),45)
    )

    println(s"1 = ${identity.query(Seq(1))}")
    println(s"5 = ${identity.query(Seq(5))}")
    println(s"500 = ${identity.query(Seq(500))}")

  }

}
