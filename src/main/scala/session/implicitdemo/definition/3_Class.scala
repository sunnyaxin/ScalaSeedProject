package session.implicitdemo.definition

object Demo3 {
  def main(args: Array[String]): Unit = {
    val a = new A()
    a.show()
  }
}

class A {
  def log(): Unit = ???
}

object A {
  implicit class Show(a: A) {  //3_class
    def show(): Unit = println("This is implicit show method.")
  }
}