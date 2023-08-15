import model.{Pipe, Point}

object Main extends App {

  val point: Point = Point(
    velocity = 10, flow = 10, pressure = 10, temperature = 2, isBorderPoint = true
  )
  val pipe: Pipe = Pipe(
    point, length = 10, diameter = 10, deltaLength = 2, deltaDiameter = 2, density = 10
  )
  println(pipe.getPoints)
}
