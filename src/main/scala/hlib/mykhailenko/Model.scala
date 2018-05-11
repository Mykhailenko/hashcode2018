package hlib.mykhailenko

object BuildingType extends Enumeration {
  type BuildingType = Value
  val Residence, Utility = Value
}
object Direction extends Enumeration {
  type Direction = Value
  val Up, Right, Down, Left = Value
}


import hlib.mykhailenko.BuildingType.BuildingType

class Building (val id: Int, val buildingType: BuildingType, val assosiactedValue: Int, val plan: Array[Array[Boolean]]) {
  var coord = Coord()

  def isPlaced = coord.r != -1 && coord.c != -1

  def getRightBottomCorner = Coord(coord.r + plan.length - 1, coord.c + plan(0).length - 1)

  lazy val occupiedCoords = {
    val coords = for { r <- plan.indices
                       c <- plan(0).indices}
      yield if (plan(r)(c)){
        Some(Coord(r + coord.r - 1, c + coord.c - 1))
      } else {
        None
      }
    coords.filter(_.isDefined).map(_.get)
  }

  lazy val w = plan(0).length

  lazy val h = plan.length
}

case class Coord(var r: Int = -1, var c: Int = -1) {
  def dist(another: Coord) = Math.abs(r - another.r) + Math.abs(c - another.c)

}

class Challenge (val r: Int, val c: Int, val maxDistance: Int) {
  var buildings: List[Building] = List()

  def insidePlan(coord: Coord) : Boolean = {
    0 <= coord.r && coord.r < r && 0 <= coord.c && coord.c < c
  }
}

class BuildCoord(val id: Int, val r: Int, val c: Int)

class Solution (var buildings : List[BuildCoord] = List())