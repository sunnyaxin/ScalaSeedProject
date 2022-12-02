package example.typeclass

trait MyShow[A] {
  def show(a: A): String
}

object MyShow {
  trait Ops {
    def show: String
  }

  implicit def toOps[A](a: A)(implicit s: MyShow[A]): Ops =
    new Ops {
      override def show: String = s.show(a)
    }
}
