package example.typeclass

import scala.language.implicitConversions

case class Person (name: String, age: Int)

object Person {
  implicit def toOps(p : Person): PersonInstance = new PersonInstance(p)
}