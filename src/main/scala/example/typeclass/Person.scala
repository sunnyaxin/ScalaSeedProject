package example.typeclass

import scala.language.implicitConversions

case class Person (name: String, age: Int)

trait PersonOps {
  def show: String
}

object ToPersonOps {
  implicit def toOps(p : Person)(implicit s: MyShow[Person]): PersonOps = new PersonOps {
    override def show: String = s.show
  }
}