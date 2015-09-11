package com.neilconcepts.battlespace

import com.neilconcepts.battlespace.storage.{ Database, Registration }
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
  val db: Database = new Database()
  db.registration.createRegistration(id = java.util.UUID.randomUUID)
  db.registration.createRegistration(id = java.util.UUID.randomUUID)
  db.registration.createRegistration(id = java.util.UUID.randomUUID)

  val service = Endpoint.makeService(db)

  val server = Httpx.serve(":8080", service) //creates service

  println("server starting on 8080")
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