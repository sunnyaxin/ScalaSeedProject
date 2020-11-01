package session.implicitdemo.definition

object Demo5 {
  //class Ages(ages:List[Int])(implicit order:Ordering[Int])
  def main(args: Array[String]): Unit = {
    implicit val t: B5 = B5(0)
    println(new A5(1)(B5(2)))
    println(new A5(1))
  }
}

class A5(a: Int)(implicit b: B5) {
  override def toString: String = s"a is $a, b is ${b.value}"
}

case class B5(value: Int)