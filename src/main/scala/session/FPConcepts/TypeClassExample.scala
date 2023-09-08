package session.FPConcepts

import cats.effect.{ExitCode, IO, IOApp}

object TypeClassExample extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO.println("hello type class")
//      _ <- IO.println(processAdd[Age](Age(0), 1))
//      _ <- IO.println(processSub[Age](Age(0), 1))
    } yield ExitCode.Success

  case class Age(value: Int) {
    def add(delta: Int): Age = ???
  }
  case class Person(name: String, age: Age)
  case class Point(x: Int, y: Int)

  //todo stage0 OO Pattern
//  case class Age(var value: Int) {
//    def add(delta: Int): Unit =
//      value += delta
//  }
//
//  case class Person(var name: String, var age: Age) {
//    def add(delta: Int): Unit =
//      age.add(delta)
//  }
//
//  case class Point(var x: Int, var y: Int) {
//    def add(delta: Int): Unit = {
//      x += delta
//      y += delta
//    }
//  }

  //todo stage1 FP Style
//  def addAge(delta: Int)(v: Age): Age = Age(v.value + delta)
//  def addPerson(delta: Int)(v: Person): Person =
//    Person(v.name, addAge(delta)(v.age))
//  def addPoint(delta: Int)(v: Point): Point = Point(v.x + delta, v.y + delta)
//
//  //Age(0).addAge(1).addAge(2).addAge(3)
//  val result = addAge(1)(addAge(2)(addAge(3)(Age(0))))

  //todo stage2 Fake OO
//  object Age {
//    implicit class AgeOps(v: Age) {
//      def add(delta: Int): Age = Age(v.value + delta)
//    }
//  }
//
//  object Person {
//    implicit class PersonOps(v: Person) {
//      def add(delta: Int): Person = Person(v.name, v.age.add(delta))
//    }
//  }
//
//  object Point {
//    implicit class PointOps(v: Point) {
//      def add(delta: Int): Point = Point(v.x + delta, v.y + delta)
//    }
//  }
//
//  val result = Age(0).add(1).add(2).add(3)
//
//  def processAdd[A](a: A, delta: Int): A =
//    a match {
//      case x: Age    => x.add(delta).asInstanceOf[A]
//      case x: Person => x.add(delta).asInstanceOf[A]
//      case x: Point  => x.add(delta).asInstanceOf[A]
//      case _         => throw new Exception(s"Can not process ${a}")
//    }

  //todo stage3 Unified Implicit Class and Hook
//  object AddSyntax {
//    implicit class AddOps[A](v: A)(implicit f: (A, Int) => A) {
//      def add(delta: Int): A = f(v, delta)
//    }
//  }
//
//  implicit def ageAddFunction(age: Age, delta: Int): Age = Age(age.value + delta)
//  implicit def personAddFunction(person: Person, delta: Int): Person = Person(person.name, person.age.add(delta))
//  implicit def pointAddFunction(point: Point, delta: Int): Point = Point(point.x + delta, point.y + delta)
//
//  import session.FPConcepts.TypeClassExample.AddSyntax.AddOps
//  def processAdd[A](a: A, delta: Int)(implicit x: (A, Int) => A): A = a.add(delta)
//
//  //add sub function
//  object SubSyntax {
//    implicit class SubOps[A](v: A)(implicit f: (A, Int) => A) {
//      def sub(delta: Int): A = f(v, delta)
//    }
//  }
//
//  implicit def ageSubFunction(age: Age, delta: Int): Age = Age(age.value - delta)
//  implicit def personSubFunction(person: Person, delta: Int): Person = Person(person.name, person.age.add(delta))
//  implicit def pointSubFunction(point: Point, delta: Int): Point = Point(point.x - delta, point.y - delta)
//
//  import session.FPConcepts.TypeClassExample.SubSyntax.SubOps
//  def processSub[A](a: A, delta: Int)(implicit x: (A, Int) => A): A = a.sub(delta)

  //todo stage4 use trait replace function
//  object AddSyntax {
//    implicit class AddOps[A](v: A)(implicit addInstance: AddInterface[A]) {
//      def add(delta: Int): A = addInstance.add(v, delta)
//    }
//  }
//
//  object SubSyntax {
//    implicit class SubOps[A](v: A)(implicit subInstance: SubInterface[A]) {
//      def sub(delta: Int): A = subInstance.sub(v, delta)
//    }
//  }
//
//  trait AddInterface[A] {
//    def add(value: A, delta: Int): A
//  }
//  trait SubInterface[A] {
//    def sub(value: A, delta: Int): A
//  }
//
//  implicit val ageAddInstance: AddInterface[Age] = new AddInterface[Age] {
//    override def add(age: Age, delta: Int): Age = Age(age.value + delta)
//  }
//  implicit val personAddInstance: AddInterface[Person] =
//    new AddInterface[Person] {
//      override def add(person: Person, delta: Int): Person =
//        Person(person.name, person.age.add(delta))
//    }
//  implicit val pointAddInstance: AddInterface[Point] = new AddInterface[Point] {
//    override def add(point: Point, delta: Int): Point =
//      Point(point.x + delta, point.y + delta)
//  }
//  implicit val ageSubInstance: SubInterface[Age] = new SubInterface[Age] {
//    override def sub(age: Age, delta: Int): Age = Age(age.value - delta)
//  }
//  implicit val personSubInstance: SubInterface[Person] =
//    new SubInterface[Person] {
//      override def sub(person: Person, delta: Int): Person =
//        Person(person.name, person.age.add(delta))
//    }
//  implicit val pointSubInstance: SubInterface[Point] = new SubInterface[Point] {
//    override def sub(point: Point, delta: Int): Point =
//      Point(point.x - delta, point.y - delta)
//  }
//
//  import session.FPConcepts.TypeClassExample.AddSyntax.AddOps
//  def processAdd[A: AddInterface](a: A, delta: Int): A = a.add(delta)
//
//  import session.FPConcepts.TypeClassExample.SubSyntax.SubOps
//  def processSub[A: SubInterface](a: A, delta: Int): A = a.sub(delta)

  //todo bingo type class
//  trait DoSomethingInterface[A] {
//    def doSomething(a: A)
//  }
//
//  implicit val someTypeDoSomething = new DoSomethingInterface[SomeType] {
//      def doSomething(a: SomeType) = ???
//  }
//
//  implicit class DoSomethingOps[A: DoSomethingInterface](v: A) {
//    def doSomething(a: A) = implicitly[DoSomethingInterface[A]].doSomething(a)
//  }
//
//  def processDoSomething[A: DoSomethingInterface](a: A) = ???
}
