
import hlib.mykhailenko.IO.{readChallenge, readSolution}
import org.rogach.scallop._

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val challenge = opt[String](required = true)
  val solution = opt[String](required = true)
  verify()
}



object Main {
  def main(args: Array[String]): Unit = {
    val cmd = new Conf(args)

    val challenge = readChallenge(cmd.challenge())

    val solution = readSolution(cmd.solution())


    println("Hello, world!")
  }
}