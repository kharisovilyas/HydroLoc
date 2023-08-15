import math.Calculation
import model.{Pipe, Point}

import scala.util.Random


object Main extends App {

  val generationSyntacticVelocity: Double = Random.nextDouble()*1000

  def generationSyntacticData: Point = Point(
    generationSyntacticVelocity,
    Random.nextDouble()*1000,
    Random.nextDouble()*100,
    Random.nextDouble()*10,
    Random.nextDouble(),
    isBorderPoint = Random.nextBoolean()
  )
  val listPoints = List.fill(5)(generationSyntacticData)
  val pipe: Pipe = Pipe(
    listPoints,
    length = 5,
    diameter = 5,
    horizontalQuantity = 5,
    verticalQuantity = 5, // deltaLen = length / verticalQuantity
    density = 10
  )
  val matrixHeaders = List("Velocity", "Flow", "Pressure", "Temperature", "Density")
  val matrixData = matrixHeaders.map { header =>
    pipe.getPoints.map(row => row.map(point =>
      header match {
        case "Velocity" => point.velocity.toString
        case "Flow" => point.flow.toString
        case "Pressure" => point.pressure.toString
        case "Temperature" => point.temperature.toString
        case "Density" => point.density.toString
        case _ => ""
      }).mkString("\t")
    ).mkString("\n")
  }

  matrixData.zipWithIndex.foreach { case (data, index) =>
    val header = matrixHeaders(index)
    println(s"-----------$header-----------")
    println(data)
    println()
  }

}
