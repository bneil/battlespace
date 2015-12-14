package com.neilconcepts.battlespace

import com.neilconcepts.battlespace.routes.{ GameRoutes, RegistrationRoutes }
import com.neilconcepts.battlespace.storage.Database
import com.twitter.finagle.Service
import com.twitter.finagle.http.{ Request, Response }
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

/**
 * Endpoint ::
 * The Endpoint object extends all the routes and wraps
 * them in the currently deprecated toService __makeService__
 * method
 */
object Endpoint
    extends GameRoutes
    with RegistrationRoutes {

  def makeService(db: Database): Service[Request, Response] =
    (
      getRegUser(db) :+:
      createRegUser(db) :+:
      attackBoard(db)
    ).toService

}

