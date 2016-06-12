import GradientDescent.TrainingData

/**********
  *
  * This is a test in both my use of VIM as well as my use of Gradient Descent
  * The goal is to read in a provided CSV file and match a straight line to it.
  *
  **********/

object GradientDescent {
  case class TrainingData(input:Seq[Double],output:Double){
    def x = input
    def y = output
  }
}

class GradientDescent(alpha:Double,input:TrainingData*){

  private val trainingSet = input.map{case TrainingData(x,y)=>TrainingData(1d +: x, y)}
  private var theta = trainingSet.head.x.map(_=>0d)
  private var oldTheta = theta

  private val m = trainingSet.size

  private def hypothesis(values:Seq[Double],th:Seq[Double]):Double = {values.zip(th).foldLeft(0d){case (acc,(v,t))=>acc+v*t}}

  def query(input:Seq[Double]) = {
    require(input.size == theta.size - 1, "query must be of corrent feature cardinality.")
    val data = 1d +: input
    hypothesis(data,theta)
  }

  private def converg(t1:Seq[Double],t2:Seq[Double]):Boolean = {
    Math.abs(cost(t1)-cost(t2)) < 0.000001
  }

  private def cost(testTheta:Seq[Double]):Double = (1d/(2*m)) * trainingSet.foldLeft(0d){ case (acc,TrainingData(xi,yi))=>
    Math.pow(hypothesis(xi,testTheta)-yi,2)
  }

  do {
    oldTheta = theta
    val tmp = theta.zipWithIndex.map{case (t,i)=>
      t - alpha * (1d/m) * trainingSet.foldLeft(0d){case (acc,TrainingData(x,y)) =>
        acc + (hypothesis(x,oldTheta)-y) * x(i)
      }
    }
    theta = tmp
    //println(cost(theta))
  } while(!converg(theta,oldTheta))

  println(s"Theta:$theta")

}
