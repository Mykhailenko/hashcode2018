package hlib.mykhailenko

import java.io.PrintWriter

object Util {

  implicit  def strTo2DArrayBoolean(s: String): Array[Array[Boolean]] = {
    s.split("\n").map(line => {
      line.split("").map(_.equals("#"))
    })
  }

  def tempFile(content: String) (implicit pathToFile : String) : Unit = {
    val writer = new PrintWriter(pathToFile)

    writer.print(content)

    writer.close()
  }


}
