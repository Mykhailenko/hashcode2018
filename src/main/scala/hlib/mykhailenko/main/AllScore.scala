package hlib.mykhailenko.main

import java.io.File

import hlib.mykhailenko.IO.{readChallenge, readSolution}
import hlib.mykhailenko.Scorer.score
import org.rogach.scallop._


object AllScore {

  class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
    val folder = opt[String](required = true)
    verify()
  }

  def main(args: Array[String]): Unit = {
    val cmd = new Conf(args)

    val folder = new File(cmd.folder())
    if (folder.exists && folder.isDirectory) {

      val scores = folder.listFiles
        .filter(_.isFile)
        .filter(file => file.getName.endsWith(".in")
          || file.getName.endsWith(".out"))
        .groupBy(file => fileNameWithoutExtension(file.getName))
        .filter(pair => pair._2.length == 2)
        .map(pair => {
          val baseName = pair._1
          val arrayOfTwoFiles = pair._2

          val pathToChallenge = if (arrayOfTwoFiles(0).getName.endsWith(".in")) {
            arrayOfTwoFiles(0).getAbsolutePath
          } else {
            arrayOfTwoFiles(1).getAbsolutePath
          }

          val pathToSolution = if (arrayOfTwoFiles(0).getName.endsWith(".out")) {
            arrayOfTwoFiles(0).getAbsolutePath
          } else {
            arrayOfTwoFiles(1).getAbsolutePath
          }

          val s = score(readChallenge(pathToChallenge), readSolution(pathToSolution))
          (baseName, s)
        })

      scores.foreach(pair => {
        println(s"You have scored ${pair._2} for ${pair._1}.")
      })
      println("=================")
      println("TOTAL SCORE: " + scores.map(_._2).reduce(_ + _))
    }
  }

  def fileNameWithoutExtension(fileName: String): String = {
    if (fileName.lastIndexOf(".") > 0) {
      fileName.substring(0, fileName.lastIndexOf("."))
    } else fileName
  }
}