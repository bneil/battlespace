package com.neilconcepts.battlespace.routes

import java.util.UUID

import com.neilconcepts.battlespace.domain.Board.Point
import com.neilconcepts.battlespace.domain.Messages._
import com.neilconcepts.battlespace.domain.bst.GameId
import com.neilconcepts.battlespace.domain.Board
import com.neilconcepts.battlespace.storage.Database
import io.finch._
import io.circe.generic.auto._
import io.finch.circe._

/**
 * GameRoutes ::
 * These routes will control all the external actions that happen within
 * the game state. __fireTarget__
 *
 * attackBoard = {x:0, y:0, z:0} => Board
 */
trait GameRoutes extends GameRouteActions {

  def ping(): Endpoint[String] =
    get("g" / "ping") {
      Ok("pong")
    }

  def attackBoard(db: Database): Endpoint[String] =
    post("g" / uuid ? body.as[Point]) { (gameId: UUID, p: Point) =>
      db.gameState.retrieveGameState(gameId).map { gameStateMessage =>
        Ok(handleAttackGameBoard(p, gm = gameStateMessage))
      }
    }

  //def boardStatus(db: Database): Endpoint[String] =
  //  get("g" / uuid / "status") { gameId: GameId =>
  //    db.gameState.retrieveGameState(gameId).map { gameState =>
  //      Ok(extractGameStateResponse(gameState))
  //    }
  //  }

}

trait GameRouteActions {
  def handleAttackGameBoard(p: Point, gm: Either[GameStateError, GameStateMessage]): String = {
    gm match {
      case Right(gameStateMessage) => handleAttackGameBoardRetrieved(p, gameStateMessage)
      case Left(gameStateError)    => handleGameStateError(gameStateError)
    }
  }

  def extractGameStateResponse: Either[GameStateError, GameStateMessage] => String = {
    case Right(gameStateMessage) => gameStateMessage.toString
    case Left(gameStateError)    => throw new Exception(gameStateError.toString)
  }

  def handleAttackGameBoardRetrieved(p: Point, gs: GameStateMessage): String = {
    gs match {
      case GameStateRetrieved(gameState) =>
        val boardAfterAttack = Board.attackBoard(p, gameState.gameBoard)
        "done attacking"
      case _ =>
        "no game state to retrieve"
    }
  }

  def handleGameStateError: GameStateError => ErrorMsg = {
    case GameStateRetrievalFailed(err) => err
    case GameStateSaveFailed(err)      => err
  }
}
