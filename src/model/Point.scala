package model

case class Point(velocity: Double, flow: Double, pressure: Double, temperature: Double, density: Double, isBorderPoint: Boolean) {
  def this(velocity: Double, flow: Double, pressure: Double, temperature: Double, density: Double) =
    this(velocity, flow, pressure, temperature, density, false)
}
