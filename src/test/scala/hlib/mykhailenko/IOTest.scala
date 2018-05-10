package hlib.mykhailenko

import java.io.File

import org.junit.Assert.assertEquals
import org.junit.{After, Test}
import hlib.mykhailenko.Asserts.assertEquals2DArray
import hlib.mykhailenko.IO.{readChallenge, readSolution}
import hlib.mykhailenko.Util.{strTo2DArrayBoolean, tempFile}


class IOTest {

  implicit val tempFileName = "./temp"

  @Test def testReadSolution() : Unit = {
    tempFile(
      """3
        |0 0 0
        |1 2 0
        |2 2 2""".stripMargin)

    val solution = readSolution(tempFileName)

    assertEquals(3, solution.buildings.size)
    val Array(b0, b1, b2) = solution.buildings.toArray

    assertEquals(0, b0.id)
    assertEquals(0, b0.r)
    assertEquals(0, b0.c)

    assertEquals(1, b1.id)
    assertEquals(2, b1.r)
    assertEquals(0, b1.c)

    assertEquals(2, b2.id)
    assertEquals(2, b2.r)
    assertEquals(2, b2.c)
  }

  @Test def testReadChallenge(): Unit = {

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


    val challenge = readChallenge(tempFileName)

    assertEquals(4, challenge.r)
    assertEquals(7, challenge.c)
    assertEquals(2, challenge.maxDistance)
    assertEquals(3, challenge.buildings.size)


    val Array(b0, b1, b2) = challenge.buildings.toArray

    assertEquals(0, b0.id)
    assertEquals(BuildingType.Residence, b0.buildingType)
    assertEquals(25, b0.assosiactedValue)

    assertEquals2DArray(
      """.#
        |##
        |.#""".stripMargin, b0.plan)

    assertEquals(1, b1.id)
    assertEquals(BuildingType.Utility, b1.buildingType)
    assertEquals(1, b1.assosiactedValue)

    assertEquals2DArray("####", b1.plan)

    assertEquals(2, b2.id)
    assertEquals(BuildingType.Utility, b2.buildingType)
    assertEquals(5, b2.assosiactedValue)

    assertEquals2DArray(
      """##
        |##""".stripMargin, b2.plan)

  }

  @After def tearDown() {
    new File(tempFileName).delete()
  }
}