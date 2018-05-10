package hlib.mykhailenko

import hlib.mykhailenko.Scorer.isOccupied
import hlib.mykhailenko.Util.tempFile
import org.junit.{Assert, Test}

class ScorerTest {

  implicit val tempFileName = "./temp"

  @Test def testScoreTrivial(): Unit = {
    tempFile(
      """4 7 2 3
        |R 3 2 25
        |.#
        |##
        |.#
        |U 1 4 1
        |####
        |U 2 2 5
        |##
        |##""".stripMargin)

    val challenge = IO.readChallenge(tempFileName)

    tempFile(
      """3
        |0 0 0
        |1 0 3
        |2 2 3""".stripMargin)

    val solution = IO.readSolution(tempFileName)

    Assert.assertEquals(50, Scorer.score(challenge, solution))

  }

  @Test def testIsOccupied(): Unit = {
    val building = new Building(0, BuildingType.Residence, 25,
      Util.strTo2DArrayBoolean(
        """.#.
          |###
          |.##""".stripMargin))
    building.coord = Coord(5, 8)

    Assert.assertTrue(isOccupied(building, Coord(6, 9)))


    Assert.assertFalse(isOccupied(building, Coord(3, 8)))
    Assert.assertFalse(isOccupied(building, Coord(6, 7)))

    Assert.assertFalse(isOccupied(building, Coord(8, 7)))
    Assert.assertFalse(isOccupied(building, Coord(6, 11)))

  }
}
