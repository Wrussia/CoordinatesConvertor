package ru.kkostikov

package object coordinatesconverter {
  object SK42 extends EarthParams {
    override val a: Double = 6378245.0
    override val alpha: Double = 1.0 / 298.3
  }

  object WGS84 extends EarthParams {
    override val a: Double = 6378137.0
    override val alpha: Double = 1.0 / 298.257223563
  }

  object PZ90 extends EarthParams {
    override val a: Double = 6378136
    override val alpha: Double = 1.0 / 298.25784
  }

  val sk42ToWgs84 = TransformParams(
    23.92,
    -141.27,
    -80.9,
    0,
    -0.35,
    -0.82,
    -0.12 * Math.pow(10, -6)
  )
}
