package com.neilconcepts.battlespace

import java.util.UUID

import com.neilconcepts.battlespace.domain.Board
import com.neilconcepts.battlespace.domain.bst.{ GameId, GameState }
import com.neilconcepts.battlespace.storage.{ Database, RegistrationStorage }
import com.neilconcepts.battlespace.storage.mem.InMemRegistration
import com.twitter.finagle.Httpx
import com.twitter.util.{ Future, Await }
import io.finch.route._
import io.finch.request._

/**
 * BattleSpaceApp ::
 * The start to all the api methods, you'll notice the registration
 * and I got that idea from the finch petstore app, to prepopulate
 * the registration db.
 */
class BattleSpaceApp {
  val db: Database = new Database()
  SeedData.init(db)

  val service = Endpoint.makeService(db)

  val server = Httpx.serve(":8080", service) //creates service

  println("server starting on 8080")
  Await.ready(server)

  def close(): Future[Unit] = {
    Await.ready(server.close())
  }
}

/**
 * Launches the BattleSpaceApp service when the system is ready.
 */
object BattleSpaceApp extends BattleSpaceApp with App {
  Await.ready(server)
}

object SeedData {
  import scalaz._; import Scalaz._

  def newState(): GameState = {
    val newGameId: GameId = UUID.randomUUID()
    val newBoard = Board.generateBoard()
    val newGameState: GameState = GameState(newGameId, newBoard)
    newGameState
  }
  def newPlayer = java.util.UUID.randomUUID

  def newPlayerAndState = {
    val state = newState()
    val player = newPlayer
    val gameId = state.gameId
    println(s"gameId: $gameId player: $player")
    (player, state)
  }

  def init(db: Database) {
    //filling up the db
    for (i <- 1 |-> 5) {
      val np = newPlayerAndState
      db.registration.createRegistration(np._1)
      db.gameState.saveGameState(np._2)
    }
  }
}
