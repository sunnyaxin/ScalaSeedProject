package example.typeclass

import cats.effect.{ExitCode, IO, IOApp}

import scala.language.implicitConversions

object Test extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val p = Person("yx", 18)
    val result = p.show
    println(result)
    IO(ExitCode.Success)
  }
}

class PersonInstance(p: Person) extends MyShow[Person] {
  override def show: String = s"Name: ${p.name}, Age: ${p.age}"
}

