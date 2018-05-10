package hlib.mykhailenko

object Scorer {

  def score(challenge: Challenge, solution: Solution): Int = {

    val buildingsWithCoord = joinBuildingsWithCoords(challenge, solution)

    chechIfPlacementValid(challenge.r, challenge.c, buildingsWithCoord)

    val residences = buildingsWithCoord.filter(_.buildingType == BuildingType.Residence)
    val utilities = buildingsWithCoord.filter(_.buildingType == BuildingType.Utility)


    residences.map(residence => {

      residence.assosiactedValue * utilities.filter(utility => {
        cartesian(residence.occupiedCoords, utility.occupiedCoords)
          .exists(pair => pair._1.dist(pair._2) <= challenge.maxDistance)
      }).map(_.assosiactedValue)
        .toSet.size

    }).reduce(_ + _)
  }


  def cartesian[T](left: Traversable[T], right: Traversable[T]) : Traversable[(T, T)] = {
    for { l <- left
          r <- right } yield {
      (l, r)
    }
  }


  def chechIfPlacementValid(R: Int, C: Int, buildings: List[Building]): Unit= {
    for(b <- buildings){
      if(b.coord.r < 0 || b.coord.r >= R
      || b.coord.c < 0 || b.coord.c >= C
      || b.getRightBottomCorner.r < 0 || b.getRightBottomCorner.r >= R
      || b.getRightBottomCorner.c < 0 || b.getRightBottomCorner.c >= C){
        throw new Exception("Buidling leaved city: " + b.id)
      }
    }

    for(r <- 0 until R){
      for(c <- 0 until C){
        val occupants = buildings.filter(isOccupied(_, Coord(r, c)))
        if(occupants.size > 1){

          throw new Exception("Buildings with ids ["+
            occupants.map(_.id).mkString(", ") +"] intersect")
        }

      }
    }
  }

  def isOccupied(b: Building, coord: Coord): Boolean = {
    if(coord.r < b.coord.r || coord.c < b.coord.c){
      return false
    }
    val rightBottomCorner = b.getRightBottomCorner
    if(coord.r > rightBottomCorner.r || coord.c > rightBottomCorner.c){
      return false
    }

    val relativeCoord = Coord(coord.r - b.coord.r, coord.c - b.coord.c)

    b.plan(relativeCoord.r)(relativeCoord.c)
  }

  protected def joinBuildingsWithCoords(challenge: Challenge, solution: Solution) = {
    challenge.buildings
      .sortBy(_.id)
      .zip(solution.buildings
        .sortBy(_.id)).map(pair => {
      val building = pair._1
      val coord = pair._2

      building.coord.r = coord.r
      building.coord.c = coord.c
      building
    })
  }
}
