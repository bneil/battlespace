package com.neilconcepts.battlespace

import java.util.UUID
import com.neilconcepts.battlespace.domain.Board.{ BattleSpaceBoard, Point }
import com.neilconcepts.battlespace.domain.Messages._
import com.neilconcepts.battlespace.domain.{ uuid, ErrorHandling }
import com.neilconcepts.battlespace.domain.bst.{ GameState, GameID, PlayerID }
import com.neilconcepts.battlespace.game.BattleSpaceGame
import com.neilconcepts.battlespace.storage.{ Database, RegistrationStorage }
import com.twitter.finagle.Service
import com.twitter.finagle.httpx.{ Request, Response }
import io.circe.syntax._
import io.finch.circe._
import io.finch.request._
import io.finch.response._
import io.finch.route.{ Router, string, _ }
import io.circe.generic.auto._

/**
 * Endpoint ::
 * This object is going to be where all the finch routes get
 * composed
 */
object Endpoint extends ErrorHandling {

  import GameRoutes._
  import RegistrationRoutes._

  def makeService(db: Database): Service[Request, Response] =
    (
      getRegUser(db) :+:
      createRegUser(db) :+:
      attackBoard(db)
    ).toService

}

/**
 * RegistrationRoutes ::
 * These are all responsible for registering new accounts or reporting back that
 * the accounts are no accessable. Registrations are apart of the [[RegistrationStorage]]
 * case class and are in the bs types domain.
 *
 * getRegUser -> this is currently a testing route and will be deprecated once I
 *               get used to the rest of the system.
 *
 */
object RegistrationRoutes {

  implicit def str2uuid: (String) => PlayerID = (x: String) => UUID.fromString(x)

  def getRegUser(db: Database): Router[Response] =
    get("registration" / string) { id: String =>
      for (regPlayer <- db.registration.readRegistration(id)) yield {
        regPlayer match {
          case Some(registeredPlayer) =>
            Created(Map("player" -> registeredPlayer.toString).asJson.noSpaces)
          case None =>
            db.registration.createRegistration(id)
            Created(Map("player" -> "1").asJson.noSpaces)
        }
      }
    }

  def createRegUser(db: Database): Router[Response] =
    get("registration" / "c") {
      val newID = UUID.randomUUID()
      db.registration.createRegistration(newID)
      Created(Map("player" -> newID.toString).asJson.noSpaces)
    }
}

/**
 * GameRoutes ::
 * These routes will control all the external actions that happen within
 * the game state. __fireTarget__
 *
 * attackBoard = {x:0, y:0, z:0} => Board
 */
object GameRoutes {
  def attackBoard(db: Database): Router[Response] =
    post("g" / uuid ? body.as[Point]) { (gameID: UUID, p: Point) =>
      for (
        game <- db.gameState.retrieveGameState(gameID)
      ) yield {
        Ok("you done it")
      }
    }

  def extractGameStateResponse: Either[GameStateMessage, GameStateError] => Response = {
    case Left(gameStateMessage) => handleGameStateRetrieved(gameStateMessage)
    case Right(gameStateError)  => handleGameStateError(gameStateError)
  }

  def handleGameStateRetrieved: GameStateMessage => Response = {
    case GameStateRetrieved(gameState) =>
      val board = gameState.gameBoard.gb.mkString(",") // pretty up later
      Ok(board.asJson.noSpaces)
    case GameStateSaved =>
      Ok("game saved")
  }

  def handleGameStateError: GameStateError => Response = {
    case GameStateRetrievalFailed(err) =>
      Ok(err.asJson.noSpaces)
    case GameStateSaveFailed(err) =>
      Ok(err.asJson.noSpaces)
  }

  def boardStatus(db: Database): Router[Response] =
    get("g" / uuid / "status") { gameID: GameID =>
      for (
        gameState <- db.gameState.retrieveGameState(gameID)
      ) yield {
        extractGameStateResponse(gameState)
      }
    }

}
