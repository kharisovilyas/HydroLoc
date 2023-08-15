package model

case class Point(velocity: Double, flow: Double, pressure: Double, temperature: Double, isBorderPoint: Boolean) {
  def this(velocity: Double, flow: Double, pressure: Double, temperature: Double) =
    this(velocity, flow, pressure, temperature, false)
}
