package example.basic

object CollectTest {
  def main(args: Array[String]): Unit = {

//    println(Age(0).add(1))
    def addAge(delta: Int)(v: Age): Age = Age(v.value + delta)
    addAge(1)(addAge(2)(Age(0)))

    Age(0).add(1)
  }

//  case class Age(var value: Int){
//    def add(delta: Int):Unit =
//      value += delta
//  }

  case class Age(value: Int)

  object Age {
    implicit class AgeOps(v: Age){
      def add(delta: Int): Age = Age(v.value + delta)
    }
  }
}
