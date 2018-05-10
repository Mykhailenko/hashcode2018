package hlib.mykhailenko

import java.io.PrintWriter
import io._

object IO {

  val separator = " "

  def readChallenge(path: String): Challenge = {

    val lines = Source.fromFile(path).getLines

    val Array(r, c, max, nBuildings) = lines.next().split(separator).map(_.toInt)
    val challenge = new Challenge(r, c, max);

    val buildings = (0 until nBuildings).map(id => {
      val Array(rTypeRow, rows, columns, valueAssos)  = lines.next().split(separator);
      val buildingType = if(rTypeRow.equals("R")) {
        BuildingType.Residence
      } else {
        BuildingType.Utility
      }
      val plan = (0 until rows.toInt).map(r => {
        lines.next().split("").map(_.equals("#")).toArray
      }).toArray
      new Building(id, buildingType, valueAssos.toInt, plan)
    }).toList

    challenge.buildings = buildings

    challenge
  }

  def readSolution(path: String): Solution = {
    val lines = Source.fromFile(path).getLines

    val solution = new Solution()

    val nBuildings = lines.next().toInt


    solution.buildings = (0 until nBuildings).map(_ => {
      val Array(id, r, c) = lines.next().split(" ").map(_.toInt)
      new BuildCoord(id, r, c)
    }).toList

    solution
  }



//  def writeToFile(path: String, city: City): Unit = {
//    val writer = new PrintWriter(path)
//
//    for (building <- city.buildings.sortBy(_.id)){
//      writer.write(s"${building.id} ${building.coord.c} ${building.coord.r}")
//    }
//
//    writer.close
//  }
}