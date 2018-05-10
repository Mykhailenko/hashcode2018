package hlib.mykhailenko.main

import hlib.mykhailenko.IO.{readChallenge, readSolution}
import hlib.mykhailenko.Scorer
import org.rogach.scallop._


object Score {

  class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
    val challenge = opt[String](required = true)
    val solution = opt[String](required = true)
    verify()
  }

  def main(args: Array[String]): Unit = {
    val cmd = new Conf(args)

    val challenge = readChallenge(cmd.challenge())

    val solution = readSolution(cmd.solution())

    println("Your score is " + Scorer.score(challenge, solution))
  }
}