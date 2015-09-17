package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.bst.GameID
import com.twitter.finagle.Failure
import com.twitter.finagle.httpx.Response
import com.twitter.util.Try
import io.finch.request._
import io.finch.response.BadRequest
import io.finch.route.Extractor

import scala.util.Success

object reader {
  implicit val boardReader: RequestReader[String] = param("board")
}

object uuid extends Extractor("uuid", java.util.UUID.fromString)
