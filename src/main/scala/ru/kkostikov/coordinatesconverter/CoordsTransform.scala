package ru.kkostikov.coordinatesconverter

import java.lang.Math.{cos, pow, sin, tan, PI}

abstract class CoordsTransform(from: EarthParams, to: EarthParams, transformParams: TransformParams) {

  import transformParams._

  private val ro = 206264.8062

  private lazy val A = (from.a + to.a) / 2.0
  private lazy val dA = to.a - from.a
  private lazy val e2 = (from.e2 + to.e2) / 2.0
  private lazy val de2 = to.e2 - from.e2


  def transform(lat: Double, lon: Double, H: Double): (Double, Double, Double) = {

    val B = lat * PI / 180
    val L = lon * PI / 180

    val M = A * (1 - e2) * pow(1 - e2 * pow(sin(B), 2), -1.5)
    val N = A * pow(1 - e2 * pow(sin(B), 2), -0.5)

    def dB: Double =
      ro / (M + H) * (N / A * e2 * sin(B) * cos(B) * dA + (pow(N, 2) / pow(A, 2) + 1) * N * sin(B) * cos(B) * de2 / 2 -
        (dx * cos(L) + dy * sin(L)) * sin(B) + dz * cos(B)) -
        wx * sin(L) * (1 + e2 * cos(2 * B)) +
        wy * cos(L) * (1 + e2 * cos(2 * B)) -
        ro * m * e2 * sin(B) * cos(B)


    def dL: Double = ro / (N + H) / cos(B) * (-dx * sin(L) + dy * cos(L)) +
      tan(B) * (1 - e2) * (wx * cos(L) + wy * sin(L)) - wz

    val dH = -A / N * dA +
      N * sin(B) * sin(B) * de2 / 2 +
      (dx * cos(L) + dy * sin(L)) * cos(B) +
      dz * sin(B) - N * e2 * sin(B) * cos(B) * (wx / ro * sin(L) -
      wy / ro * cos(L)) + ((A * A) / N + H) * m

    (lat + dB / 3600, lon + dL / 3600, H + dH)

  }
}