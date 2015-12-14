package com.neilconcepts.battlespace.domain

import io.finch._

object reader {
  implicit val boardReader: RequestReader[String] = param("board")
}

