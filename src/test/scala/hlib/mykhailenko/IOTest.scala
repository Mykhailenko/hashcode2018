package hlib.mykhailenko

import java.io.{File, PrintWriter}

import org.junit.Assert.assertEquals

import hlib.mykhailenko.IO.{readChallenge, readSolution}
import org.junit.{After, Test}

class IOTest {

  val tempFileName = "./temp"

  @Test def test(): Unit = {

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

    val b0 = challenge.buildings(0)

    assertEquals(0, b0.id)
    assertEquals(BuildingType.Residence, b0.buildingType)
    assertEquals(25, b0.assosiactedValue)

    assert2DArrayEquals(strTo2DArrayByte(
      """.#
        |##
        |.#""".stripMargin), b0.plan)
  }

  @After def tearDown() {
    new File(tempFileName).delete()
  }


  def assert2DArrayEquals[T](expected: Array[Array[T]], actual: Array[Array[T]]): Unit = {
    assertEquals(expected.length, actual.length);

    expected.zip(actual).foreach(pair => {
      assertEquals(pair._1.length, pair._2.length)

      pair._1.zip(pair._2).foreach(subpair => {
        assertEquals(subpair._1, subpair._2)
      })
    })
  }

  def strTo2DArrayByte(s: String): Array[Array[Boolean]] = {
    s.split("\n").map(line => {
      line.split("").map(_.equals("#"))
    })
  }

  def tempFile(content: String): Unit = {
    val writer = new PrintWriter(tempFileName)

    writer.print(content)

    writer.close()
  }


}
