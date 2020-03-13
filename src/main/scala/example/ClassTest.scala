package example

object ClassTest {

  def main(args: Array[String]): Unit = {
    //    new Person("yaxin", 18)
    //    val employee = new Employee
    //    println(employee.description)
    //    println(employee.isInstanceOf[Person])
    //    println(employee.isInstanceOf[Employee])
    //    println(employee.getClass == classOf[Employee])
    //    println(employee.getClass == classOf[Person])

    //    val alien: Person {def greeting: String} = new Person("Fred", 99) {
    //      def greeting = "Greeting, Earthling! My name is Fred."
    //    }
    //    println(alien.greeting)

    println(new Ant())
    println(new Ant2)
  }
}

class Person(val name: String, val age: Int) {
  println("Just constructed another person")

  def description = s"$name is $age years old"
}

class Employee extends Person("yaxin", 18) {
  override def description: String = "employ description"
}

class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature {
  override val range: Int = 2

  override def toString: String = "range: " + range + " env:" + env.length
}

class Ant2 extends {
  override val range = 3
} with Creature {
  override def toString: String = "range: " + range + " env:" + env.length
}