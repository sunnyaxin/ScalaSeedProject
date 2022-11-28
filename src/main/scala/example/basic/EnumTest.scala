package example.basic

object EnumTest {

  def main(args: Array[String]): Unit = {
    println(TrafficLightColor.Green)
    println(TrafficLightColor(0))
    println(TrafficLightColor.withName("Yellow"))
    println(TrafficLightColor.Other)
  }
}

object TrafficLightColor extends Enumeration {
  val Red, Yellow, Green = Value

  val Other: TrafficLightColor.Value = Value(11, "Hahaha, this is other")
}

