package example.typeclass

object MyShow {
  trait MyShowSyntax[A] {
    def show(a: A): String
  }

  implicit class MyShowOps[A](a: A)(implicit s: MyShowSyntax[A]) {
    def show: String = s.show(a)
  }
}
