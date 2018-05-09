package hlib.mykhailenko

import java.io.PrintWriter
import io._

object IO {

  val separator = " "

  def readFile(path: String): City = {

    val lines = Source.fromFile(path).getLines

    val Array(w, h, max, nBuildings) = lines.next().split(separator).map(_.toInt)
    val city = new City(w, h, max);

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

    city.buildings = buildings

    city
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