package model

import math.Calculation

class Pipe(
            val length: Double,
            val diameter: Double,
            val verticalQuantity: Int,
            val horizontalQuantity: Int,
            val density: Double
          ) {
  private val deltaDiameter = diameter / horizontalQuantity
  private val deltaLength = length / verticalQuantity
  private var points: List[List[Point]] = List(List())

  def getPoints: List[List[Point]] = points

  def calculatePoints(startVerticalPoints: List[Point], startPointVelocity: Double): Unit = {
    //calculateVelocity(startPoinVelocity)
    /*
       val middleRow = numRows / 2
       val pointsList: List[List[Point]] = if (middleRow % 2 == 1) (0 until middleRow).map { i =>
         (0 until numColumns).map { j =>
           val velocity =
             Calculation.calcVelocity(startPoint.velocity, diameter, position = i, deltaDiameter)
           if (i == middleRow) startPoint
           else Point(
             velocity,
             Calculation.calcTemp(startPoint.temperature, deltaLength, diameter, velocity),
             Calculation.calcPressure(startPoint.pressure, startPoint.velocity, diameter, deltaLength),
             Calculation.calcFlow(startPoint.velocity, deltaDiameter),
             Calculation.isBorderPoint(i, numRows)
           )

         }.toList
       }.toList
       else (0 until middleRow - 1).map { i =>
         (0 until numColumns).map { j =>
           val velocity =
             Calculation.calcVelocity(startPoint.velocity, diameter, position = i, deltaDiameter)
           if (i == middleRow - 1) startPoint
           else Point(
             velocity,
             Calculation.calcTemp(startPoint.temperature, deltaLength, diameter, velocity),
             Calculation.calcPressure(startPoint.pressure, startPoint.velocity, diameter, deltaLength),
             Calculation.calcFlow(startPoint.velocity, deltaDiameter),
             Calculation.isBorderPoint(i, numRows)
           )

         }.toList
       }.toList

       points = pointsList
     }*/
  }
}

object Pipe {
  def apply(startVerticalPoints: List[Point],
            length: Double, verticalQuantity: Int, diameter: Double, horizontalQuantity: Int,
            density: Double): Pipe = {
    val pipe = new Pipe(length, diameter, verticalQuantity, horizontalQuantity, density)
    pipe.calculatePoints(startVerticalPoints)
    pipe
  }
}
