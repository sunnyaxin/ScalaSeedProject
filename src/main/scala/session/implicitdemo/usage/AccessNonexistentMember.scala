package session.implicitdemo.usage

object AccessNonexistentMember {
  def main(args: Array[String]): Unit = {
    val a = new A()
    a.show()
  }
}

class A {
  def log(): Unit = ???
}

object A {
  implicit class Show(a: A) {
    def show(): Unit = println("This is implicit show method.")
  }
}
