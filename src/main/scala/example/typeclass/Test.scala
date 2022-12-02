package example.typeclass

import cats.effect.{ExitCode, IO, IOApp}
import example.typeclass.PersonInstance.show
import example.typeclass.MyShow._

import scala.language.implicitConversions

object Test extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val p = Person("yx", 18)
    val result = p.show
    println(result)
    IO(ExitCode.Success)
  }
}

object PersonInstance {
  implicit val show : MyShowSyntax[Person] = new MyShowSyntax[Person] {
    override def show(p: Person): String = s"Name: ${p.name}, Age: ${p.age}"
  }
}
