package com.neilconcepts.battlespace

import java.util.UUID

import com.neilconcepts.battlespace.domain.Board
import com.neilconcepts.battlespace.domain.bst.{ GameId, GameState }
import com.neilconcepts.battlespace.storage.Database
import com.twitter.app.Flag
import com.twitter.finagle.{ Http, Service }
import com.twitter.finagle.param.Stats
import com.twitter.finagle.stats.Counter
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

/**
 * BattleSpaceApp ::
 * The start to all the api methods, you'll notice the registration
 * and I got that idea from the finch petstore app, to prepopulate
 * the registration db.
 */
object BattleSpaceApp extends TwitterServer {
  val port: Flag[Int] = flag("port", 8081, "TCP port for HTTP server")
  val boards: Counter = statsReceiver.counter("battlespace")

  val db: Database = new Database()
  SeedData.init(db)

  val service = Endpoint.makeService(db)

  log.info(s"starting server on ${port()}")

  def main(): Unit = {
    val server =
      Http.server
        .configured(Stats(statsReceiver))
        .serve(s":${port()}", service)

    onExit {
      server.close()
    }

    Await.ready(server)
  }

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
