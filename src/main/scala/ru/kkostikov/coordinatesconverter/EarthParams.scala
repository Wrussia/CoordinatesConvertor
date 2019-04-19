package ru.kkostikov.coordinatesconverter

abstract class EarthParams {
  val a: Double
  val alpha: Double
  lazy val e2: Double = 2 * alpha - alpha * alpha
}

