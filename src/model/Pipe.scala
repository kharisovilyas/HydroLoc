package model

import math.Calculation

import scala.annotation.tailrec

class Pipe(
            val length: Double,
            val diameter: Double,
            val horizontalQuantity: Int,
            val verticalQuantity: Int
          ) {
  // Расчет дельты диаметра и дельты длины для поперечных сечений
  private val deltaDiameter = diameter / (verticalQuantity - 1)
  private val deltaLength = length / (horizontalQuantity - 1)

  // Инициализация матрицы точек при создании объекта Pipe
  private var points: List[List[Point]] = initializePoints()

  // Метод для получения матрицы точек
  def getPoints: List[List[Point]] = points

  // Инициализация матрицы точек начальными значениями
  private def initializePoints(): List[List[Point]] = {
    val startPoint = Point(0, 0, 0, 0, 0, false)
    List.tabulate(horizontalQuantity, verticalQuantity) { (i, j) =>
      if (i > 0 && j > 0) startPoint else startPoint
    }
  }

  // Расчет матрицы точек на основе начальных вертикальных точек и начальной точки
  def calculatePoints(startVerticalPoints: List[Point], startPoint: Point): Unit = {
    val startTemperature = startVerticalPoints.head.temperature
    val startDensity = startVerticalPoints.head.density

    // Рекурсивная функция для заполнения матрицы точек
    @tailrec
    def loop(i: Int, acc: List[List[Point]] = Nil): List[List[Point]] = {
      if (i >= horizontalQuantity) acc
      else {
        // Расчет новой строки точек для текущей горизонтальной позиции (i)
        val newPointsRow = (0 until verticalQuantity).map { j =>
          val x: Double = j * deltaLength
          val y: Double = i * deltaDiameter
          val prevPoint = if (i > 0 && j > 0) acc.head(j - 1) else startPoint
          val velocity = Calculation.calcVelocity(startPoint.velocity, diameter, y, deltaDiameter)
          Point(
            velocity,
            Calculation.calcTemp(startTemperature, deltaLength, diameter, velocity),
            Calculation.calcPressure(prevPoint.pressure, startPoint.velocity, diameter, deltaLength),
            Calculation.calcFlow(prevPoint.velocity, prevPoint.density),
            startDensity,
            Calculation.isBorderPoint(i, startVerticalPoints.size)
          )
        }.toList

        // Рекурсивный вызов для следующей горизонтальной позиции (i+1)
        loop(i + 1, newPointsRow :: acc)
      }
    }

    // Обновление матрицы точек с учетом вертикальных начальных точек
    points = loop(0).zip(startVerticalPoints).map {
      case (row, value) => value :: row
    }
  }
}

object Pipe {
  def apply(startVerticalPoints: List[Point],
            length: Double,
            diameter: Double,
            horizontalQuantity: Int,
            verticalQuantity: Int,
            density: Double): Pipe = {
    // Создание объекта Pipe с заданными параметрами
    val pipe = new Pipe(
      length,
      diameter,
      horizontalQuantity,
      verticalQuantity
    )

    // Вывод дельт диаметра и длины для проверки
    println(pipe.deltaLength)
    println(pipe.deltaDiameter)

    // Расчет и обновление матрицы точек с учетом начальных вертикальных точек
    pipe.calculatePoints(startVerticalPoints, startVerticalPoints(verticalQuantity / 2))

    // Возврат объекта Pipe
    pipe
  }
}
