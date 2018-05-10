package hlib.mykhailenko

import org.junit.Assert.assertEquals

object Asserts {

  def assertEquals2DArray[T](expected: Array[Array[T]], actual: Array[Array[T]]): Unit = {
    assertEquals(expected.length, actual.length);

    expected.zip(actual).foreach(twoArrays => {
      assertEquals(twoArrays._1.length, twoArrays._2.length)

      twoArrays._1.zip(twoArrays._2).foreach(twoItems => {
        assertEquals(twoItems._1, twoItems._2)
      })
    })
  }
}
