package com.neilconcepts.battlespace.domain

import io.finch.request._
import io.finch.route.Extractor

object reader {
  implicit val boardReader: RequestReader[String] = param("board")
}

//this is only temporary, since it got added into finch
object uuid extends Extractor("uuid", java.util.UUID.fromString)
