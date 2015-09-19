package com.neilconcepts.battlespace

import com.neilconcepts.battlespace.domain.ErrorHandling
import com.neilconcepts.battlespace.routes.{ GameRoutes, RegistrationRoutes }
import com.neilconcepts.battlespace.storage.Database
import com.twitter.finagle.Service
import com.twitter.finagle.httpx.{ Request, Response }

/**
 * Endpoint ::
 * The Endpoint object extends all the routes and wraps
 * them in the currently deprecated toService __makeService__
 * method
 */
object Endpoint
    extends ErrorHandling
    with GameRoutes
    with RegistrationRoutes {

  def makeService(db: Database): Service[Request, Response] =
    (
      getRegUser(db) :+:
      createRegUser(db) :+:
      attackBoard(db) :+:
      boardStatus(db)
    ).toService

}

