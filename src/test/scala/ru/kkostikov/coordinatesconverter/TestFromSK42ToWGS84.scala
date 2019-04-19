package ru.kkostikov.coordinatesconverter

import org.scalatest.{Matchers, WordSpec}

class TestFromSK42ToWGS84  extends WordSpec with Matchers {

  "SK42 to WGS84" in {
    val lat = 55.99277
    val lon = 37.25861
    val H = 0

    FromSK42ToWGS84.transform(lat, lon, H) shouldBe (55.9928040312883,37.25671731150299,4.815679345096206)
  }
}