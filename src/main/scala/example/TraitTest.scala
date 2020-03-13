package example

object TraitTest {

  def main(args: Array[String]): Unit = {
//    new SavingsAccount().withdraw(123)
//    val absAccount = new AbsSavingsAccount with ConsoleLogger
//    absAccount.log("abstract account created with console logger")
//
//    val acct1 = new SavingsAccount with ConsoleLogger with TimestampLogger with ShortLogger
//    val acct2 = new SavingsAccount with ConsoleLogger with ShortLogger with TimestampLogger
//
//    acct1.withdraw(111)
//    acct2.withdraw(222)

    val account = new SavingsAccount with AbsTimestampLogger
    account.withdraw(444)
  }
}

trait Logger {
  def log(msg: String)
}

trait ConsoleLogger extends Logger {
  def log(myMsg: String) {
    println(myMsg)
  }
}

trait TimestampLogger extends ConsoleLogger {
  override def log(msg: String): Unit = super.log(s"${java.time.Instant.now()} $msg")
}

trait AbsTimestampLogger extends Logger {
  abstract override def log(msg: String): Unit = super.log(s"${java.time.Instant.now()} $msg")
}

trait ShortLogger extends ConsoleLogger {
  val maxLength = 15

  override def log(msg: String): Unit = super.log(if (msg.length <= maxLength) msg else msg.substring(0, maxLength - 3) + "...")
}

class SavingsAccount extends ConsoleLogger {
  def withdraw(amount: Double): Unit = {
    log("withdraw running... and the amount is:" + amount)
  }
}

abstract class AbsSavingsAccount extends Logger {
  def withdraw(amount: Double): Unit = {
    log("withdraw running... and the amount is:" + amount)
  }
}
