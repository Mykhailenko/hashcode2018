package hlib.mykhailenko

import hlib.mykhailenko.Direction.{Direction, Down, Left, Right, Up}

object SolverRandom extends Solver {

  override def solve(challenge: Challenge) : Solution = {




    null
  }

  def addBuilding(currentSolution : Challenge, near: Building, newBuilding: Building): Boolean = {
    true
  }

  def canIPlace(challenge: Challenge, building: Building, coord: Coord) : Boolean = {
    val insidePlan = challenge.insidePlan(building.coord) && challenge.insidePlan(building.getRightBottomCorner)

    import Stream._



    val intersect = challenge.buildings
      .filter(_.isPlaced)
      .exists(placed => {
        Scorer.cartesian(placed.occupiedCoords, building.occupiedCoords)
          .exists(pair => pair._1.dist(pair._2) == 0)
      })

    insidePlan && !intersect

  }

  def next(current: Direction) : Direction = current match {
    case Up => Right
    case Right => Down
    case Down => Left
    case Left => Up
  }


  def streamOfAttemp(point: Coord, direction: Direction, attemptLeft: Int) : List[Coord] = {
    if(attemptLeft > 0) {
      val step = 4
      var newR = point.r
      var newC = point.c
      direction match {
        case Up => newR -= step
        case Right => newC += step
        case Down => newR += step
        case Left => newC -= step
      }
      point :: streamOfAttemp(Coord(newR, newC), next(direction), attemptLeft - 1)
    } else {
      Nil
    }
  }

}
