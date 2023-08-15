package math

object Calculation {
  val CONST_1 = 0
  val CONST_2 = 4200
  val CONST_3 = 0.3
  val CONST_4 = 1000

  val CONST_5 = 1.9

  def calcFlow(velocityStartPoint: Double, deltaDiameter: Double): Double = {
    // РАСЧЕТ ПОТОКА
    Math.PI * velocityStartPoint * deltaDiameter
  }

  def calcTemp(startTemp: Double, deltaLength: Double, diameter: Double, velocity: Double): Double = {
    //diameter -> deltaD
    val temperature = 20 + (startTemp - 20) * Math.exp(-1 * (((1 + CONST_1) * deltaLength) / (CONST_2 * CONST_3 * Math.PI * (diameter / 4) * CONST_4) * velocity))
    temperature
  }

  def calcVelocity(velocityStartPoint: Double, diameter: Double, position: Int, deltaDiameter: Double): Double = {
    velocityStartPoint * (1 - ((diameter / 2) - position * deltaDiameter) / Math.pow(diameter / 2, 2))
  }

  def calcPressure(startPressure: Double, startVelocity: Double, diameter: Double, deltaLength: Double): Double = {
    // РАСЧЕТ ДАВЛЕНИЯ
    val pressure = startPressure - (CONST_5 * CONST_4 * Math.pow(startVelocity, 2) * deltaLength) / (2 * diameter)
    pressure
  }

  def isBorderPoint(row: Int, length: Int): Boolean = {
    row == 0 || row == length
  }
}
