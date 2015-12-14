package com.neilconcepts.battlespace.routes

import java.util.UUID

import com.neilconcepts.battlespace.domain.Board
import com.neilconcepts.battlespace.domain.Messages.RegCreated
import com.neilconcepts.battlespace.domain.bst.{ GameId, GameState, Player, PlayerId }
import com.neilconcepts.battlespace.storage.Database
import io.finch._
import io.circe.generic.auto._
import io.finch.circe._
/**
 * RegistrationRoutes ::
 * These are all responsible for registering new accounts or reporting back that
 * the accounts are no accessable. Registrations are apart of the [[com.neilconcepts.battlespace.storage.RegistrationStorage]]
 * case class and are in the bs types domain.
 *
 * getRegUser -> this is currently a testing route and will be deprecated once I
 *               get used to the rest of the system.
 *
 * createRegUser -> simple route to create a new player and gameState
 *
 */
trait RegistrationRoutes extends RegistrationRouteActions {
  implicit def str2uuid: (String) => PlayerId = (x: String) => UUID.fromString(x)

  def getRegUser(db: Database): Endpoint[Player] =
    get("c" / string) { id: String =>
      db.registration.readRegistration(id).map { player =>
        Created(extractRegPlayer(player))
      }
    }.handle {
      case e: Exception => NotFound(e)
    }

  def createRegUser(db: Database): Endpoint[RegCreated] =
    get("c") {
      val newId: PlayerId = UUID.randomUUID()
      val newPlayer: Player = Player(newId)
      val newGameId: GameId = UUID.randomUUID()
      val newBoard = Board.generateBoard()
      val newGameState: GameState = GameState(newGameId, newBoard)

      db.registration.createRegistration(newId)
      db.gameState.saveGameState(newGameState)

      Ok(RegCreated(newPlayer.toString, newGameId.toString))
    }
}

trait RegistrationRouteActions {
  def extractRegPlayer: Option[Player] => Player = {
    case Some(registeredPlayer) =>
      registeredPlayer
    case None =>
      throw new Exception("player not found")

  }
}
