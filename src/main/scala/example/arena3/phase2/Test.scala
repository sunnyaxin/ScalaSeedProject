package example.arena3.phase2

import java.io.FileOutputStream
import scala.io.Source

object Test extends App {
  val input = Source.fromFile("input.csv").getLines().toList
  for(i <- input.indices)
    println("i: " + i + ",input:" + input(i))
//  new FileOutputStream("output.csv").write(input.getBytes)
}
