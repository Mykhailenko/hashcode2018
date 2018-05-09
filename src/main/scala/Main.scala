
import hlib.mykhailenko.IO.{readChallenge, readSolution}
import hlib.mykhailenko.{Challenge, Solution}
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


    println("Your score is " + score(challenge, solution))
  }

  def score(challenge: Challenge, solution: Solution): Int ={
    0
  }

}