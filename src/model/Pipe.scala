package model

import math.Calculation

import scala.annotation.tailrec
class Pipe(
            val length: Double,
            val diameter: Double,
            val verticalQuantity: Int,
            val horizontalQuantity: Int
          ) {
  private val deltaDiameter = diameter / (horizontalQuantity - 1)
  private val deltaLength = length / (verticalQuantity - 1)
  private var points: List[List[Point]] = initializePoints()

  def getPoints: List[List[Point]] = points

  private def initializePoints(): List[List[Point]] = {
    val startPoint = Point(0, 0, 0, 0, 0, false)
    List.tabulate(horizontalQuantity, verticalQuantity) { (i, j) =>
      if (i > 0 && j > 0) startPoint else startPoint
    }
  }

  def calculatePoints(startVerticalPoints: List[Point], startPoint: Point): Unit = {
    val startTemperature = startVerticalPoints.head.temperature
    val startDensity = startVerticalPoints.head.density
    @tailrec
    def loop(i: Int, acc: List[List[Point]] = Nil): List[List[Point]] = {
      if (i >= horizontalQuantity) acc
      else {
        val newPointsRow = (0 until verticalQuantity).map { j =>
          val prevPoint = if (i > 0 && j > 0) acc.head(j - 1) else startPoint
          val velocity = Calculation.calcVelocity(startPoint.velocity, diameter, i, deltaDiameter)
          Point(
            velocity,
            Calculation.calcTemp(startTemperature, deltaLength, diameter, velocity),
            Calculation.calcPressure(prevPoint.pressure, startPoint.velocity, diameter, deltaLength),
            Calculation.calcFlow(prevPoint.velocity, prevPoint.density),
            startDensity,
            Calculation.isBorderPoint(i, startVerticalPoints.size)
          )
        }.toList
        loop(i + 1, newPointsRow :: acc)
      }
    }
    points = loop(0).zip(startVerticalPoints).map {
      case (row, value) => value :: row
    }
  }
}

object Pipe {
  def apply(startVerticalPoints: List[Point],
            length: Double, verticalQuantity: Int, diameter: Double, horizontalQuantity: Int,
            density: Double): Pipe = {
    val pipe = new Pipe(length, diameter, verticalQuantity, horizontalQuantity)
    pipe.calculatePoints(startVerticalPoints, startVerticalPoints(verticalQuantity / 2))
    pipe
  }
}