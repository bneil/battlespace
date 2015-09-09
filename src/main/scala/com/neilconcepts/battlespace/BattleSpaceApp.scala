package com.neilconcepts.battlespace

import com.neilconcepts.battlespace.storage.Registration
import com.neilconcepts.battlespace.storage.mem.InMemRegistration
import com.twitter.finagle.Httpx
import com.twitter.util.{ Future, Await }
import io.finch.route._
import io.finch.request._

/**
 * BattleSpaceApp ::
 * The start to all the api methods
 */
class BattleSpaceApp {
  val db: Registration = new InMemRegistration()
  db.createRegistration(id = java.util.UUID.randomUUID)

  val service = Endpoint.makeService(db)

  val server = Httpx.serve(":8080", service) //creates service

  Await.ready(server)

  def close(): Future[Unit] = {
    Await.ready(server.close())
  }
}

/**
 * Launches the PetstoreAPI service when the system is ready.
 */
object BattleSpaceApp extends BattleSpaceApp with App {
  Await.ready(server)
}