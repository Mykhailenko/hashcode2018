package hlib.mykhailenko

object BuildingType extends Enumeration {
  type BuildingType = Value
  val Residence, Utility = Value
}

import hlib.mykhailenko.BuildingType.BuildingType

class Building (val id: Int, val buildingType: BuildingType, val assosiactedValue: Int, val plan: Array[Array[Boolean]]) {
  var coord = Coord()
}

case class Coord(var r: Int = 0, var c: Int = 0) {
  def dist(another: Coord) = Math.abs(r - another.r) + Math.abs(c - another.c)
}

class Challenge (val r: Int, val c: Int, val maxDistance: Int) {
  var buildings: List[Building] = List()
}

class BuildCoord(val id: Int, val r: Int, val c: Int)

class Solution (var buildings : List[BuildCoord] = List())