package ru.kkostikov.coordinatesconverter

object FromSK42ToWGS84 extends CoordsTransform(SK42, WGS84, sk42ToWgs84) {
  def main(args: Array[String]): Unit = {

    val lat = args(0).toDouble
    val lon = args(1).toDouble
    val H = args(2).toDouble

    transform(lat, lon, H)
  }
}