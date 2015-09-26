package com.neilconcepts.battlespace.routes

import java.util.UUID

import com.neilconcepts.battlespace.domain.Board.Point
import com.neilconcepts.battlespace.domain.Messages._
import com.neilconcepts.battlespace.domain.bst.GameId
import com.neilconcepts.battlespace.domain.{Board, uuid}
import com.neilconcepts.battlespace.storage.Database
import com.twitter.finagle.httpx.Response
import io.finch.argonaut._
import io.finch.request._
import io.finch.response._
import io.finch.route.{ Router, _ }

/**
 * GameRoutes ::
 * These routes will control all the external actions that happen within
 * the game state. __fireTarget__
 *
 * attackBoard = {x:0, y:0, z:0} => Board
 */
trait GameRoutes extends GameRouteActions {

  def attackBoard(db: Database): Router[Response] =
    post("g" / uuid ? body.as[Point]) { (gameID: UUID, p: Point) =>
      for (
        gameStateMessage <- db.gameState.retrieveGameState(gameID)
      ) yield {
        handleAttackGameBoard(p, gameStateMessage)
      }
    }

  def boardStatus(db: Database): Router[Response] =
    get("g" / uuid / "status") { gameID: GameId =>
      for (
        gameState <- db.gameState.retrieveGameState(gameID)
      ) yield {
        extractGameStateResponse(gameState)
      }
    }

}

trait GameRouteActions {
  def handleAttackGameBoard(p: Point, gm: Either[GameStateMessage, GameStateError]): Response = {
    gm match {
      case Left(gameStateMessage) => handleAttackGameBoardRetrieved(p, gameStateMessage)
      case Right(gameStateError) => handleGameStateError(gameStateError)
    }
  }

  def extractGameStateResponse: Either[GameStateMessage, GameStateError] => Response = {
    case Left(gameStateMessage) => handleGameStateRetrieved(gameStateMessage)
    case Right(gameStateError) => handleGameStateError(gameStateError)
  }

  def handleAttackGameBoardRetrieved(p: Point, gs: GameStateMessage): Response = {
    gs match {
      case GameStateRetrieved(gameState) =>
        val boardAfterAttack = Board.attackBoard(p, gameState.gameBoard)
        Ok("done attacking")
    }
  }

  def handleGameStateRetrieved: GameStateMessage => Response = {
    case GameStateRetrieved(gameState) =>
      val board = gameState.gameBoard.gb.mkString(",")
      Ok(board)
    case GameStateSaved =>
      Ok("game saved")
  }

  def handleGameStateError: GameStateError => Response = {
    case GameStateRetrievalFailed(err) =>
      Ok(err)
    case GameStateSaveFailed(err) =>
      Ok(err)
  }
}
