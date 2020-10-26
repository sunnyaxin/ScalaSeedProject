package example

import io.circe.{DecodingFailure, Json}

object CirceTest {

  def main(args: Array[String]): Unit = {
//    testBasicCirceInfo()

//    testParseJson()

//    testTraverseAndModifyJson()

//    testEncodingAndDecoding()

    testSemiAutoDerivationWithDefaultName()

    testSemiAutoDerivationWithDifferentName()

//    testAutoDerivation()
  }

  private def testBasicCirceInfo(): Unit = {
    import io.circe.generic.auto._
    import io.circe.parser._
    import io.circe.syntax._

    val foo: Foo = Qux(13, Some(14.0))

    val json = foo.asJson.noSpaces
    println(json)

    val decodedFoo = decode[Foo](json)
    println(decodedFoo)
  }

  def testParseJson(): Unit = {
    import io.circe._
    import io.circe.parser._

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

  def testTraverseAndModifyJson(): Unit = {
    import io.circe._
    import io.circe.parser._

    val json: String = """
      {
        "id": "c730433b-082c-4984-9d66-855c243266f0",
        "name": "Foo",
        "counts": [1, 2, 3],
        "values": {
          "bar": true,
          "baz": 100.001,
          "qux": ["a", "b"]
        }
      }
    """

    val doc: Json = parse(json).getOrElse(Json.Null)

    // Extracting data - HCursor
    val cursor: HCursor = doc.hcursor
    val baz: Decoder.Result[Double] =
      cursor.downField("values").downField("baz").as[Double]
    val baz2 = cursor.downField("values").get[Double]("baz")
    val qux = cursor.downField("values").downField("qux").downArray.as[String]
    println(baz + " " + baz2 + " " + qux)

    //Transforming data - ACursor
    val reversedNameCursor: ACursor =
      cursor.downField("name").withFocus(_.mapString(_.reverse))
    val reversedName: Option[Json] = reversedNameCursor.top
    println(reversedName)

//    Cursor provides functionality for moving around a tree and making modifications
//    HCursor tracks the history of operations performed. This can be used to provide useful error messages when something goes wrong.
//    ACursor also tracks history, but represents the possibility of failure (e.g. calling downField on a field that doesn’t exist)
  }

  def testEncodingAndDecoding(): Unit = {
    import io.circe.parser.decode
    import io.circe.syntax._
    import io.circe.{DecodingFailure, Json}

    val intsJson: Json = List(1, 2, 3).asJson
    println(intsJson)

    val listInstances: Either[DecodingFailure, List[Int]] =
      intsJson.as[List[Int]]
    println(listInstances)

    val listRes = decode[List[Int]]("[1, 2, 3]")
    println(listRes)
  }

  def testSemiAutoDerivationWithDefaultName(): Unit = {
    import io.circe._
    import io.circe.generic.semiauto._
    import io.circe.syntax._

    case class Foo(a: Int, b: String, c: Boolean)

    implicit val fooDecoder: Decoder[Foo] = deriveDecoder[Foo]
    implicit val fooEncoder: Encoder[Foo] = deriveEncoder[Foo]

    val fooJson: Json = Foo(1, "hello", c = true).asJson
    val fooObj: Either[DecodingFailure, Foo] = fooJson.as[Foo]
    println(fooJson)
    println(fooObj)
  }

  def testSemiAutoDerivationWithDifferentName(): Unit = {
    import io.circe.syntax._
    import io.circe.{Decoder, Encoder}

    case class User(id: Long, firstName: String, lastName: String)

    implicit val decodeUser: Decoder[User] =
      Decoder.forProduct3("id", "first_name", "last_name")(User.apply)

    implicit val encodeUser: Encoder[User] =
      Encoder.forProduct3("id", "first_name", "last_name")(
        u => (u.id, u.firstName, u.lastName)
      )

    val userJson: Json = User(1L, "Yaxin", "Sunny").asJson
    val userObj: Either[DecodingFailure, User] = userJson.as[User]
    println(userJson)
    println(userObj)
  }

  def testAutoDerivation(): Unit = {
    import io.circe.generic.auto._
    import io.circe.syntax._

    case class Person(name: String)
    case class Greeting(salutation: String,
                        person: Person,
                        exclamationMarks: Int)

    val greetingJson = Greeting("Hey", Person("Chris"), 3).asJson
    println(greetingJson)
  }
}

sealed trait Foo
case class Bar(xs: Vector[String]) extends Foo
case class Qux(i: Int, d: Option[Double]) extends Foo
