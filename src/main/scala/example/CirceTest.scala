package example

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import io.circe.{Json, ParsingFailure}

object CirceTest {

  def main(args: Array[String]): Unit = {
//    testBasicCirceInfo()

    testParseJson()
  }

  private def testBasicCirceInfo(): Unit = {
    val foo: Foo = Qux(13, Some(14.0))

    val json = foo.asJson.noSpaces
    println(json)

    val decodedFoo = decode[Foo](json)
    println(decodedFoo)
  }

  def testParseJson(): Unit = {
    val rawJson: String = """
        {
          "foo": "bar",
          "baz": 123,
          "list of stuff": [ 4, 5, 6 ]
        }
        """
    val parseResult: Either[ParsingFailure, Json] = parse(rawJson)
    val jsonResult: Json = parseResult.getOrElse(Json.Null)
    println(jsonResult)

    val badJson: String = "hahahah"
    println(parse(badJson).getOrElse(Json.Null))
  }
}

sealed trait Foo
case class Bar(xs: Vector[String]) extends Foo
case class Qux(i: Int, d: Option[Double]) extends Foo
