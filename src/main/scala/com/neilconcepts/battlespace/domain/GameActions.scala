package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.Board.BattleSpaceBoard

/**
 * GameActions ::
 * All the game actions for game objects
 * Each action has a three phase lifecycle
 * - birth : The object when assigned to the game board
 * - life : Any special actions while on the board
 * - death : What happens when destroyed by a missle
 */
object GameActions {
  sealed trait GameAction

  private def getRandomBoardSpot: Int = {
    val r = scala.util.Random
    r.nextInt(Board.maxDimensions)
  }

  object Takes extends GameAction {
    def apply(size: Int): Option[GameAction] = {
      //Determine x,y,z interactions
      None
    }
  }

  object Explode extends GameAction {
    def apply(size: Int): Option[GameAction] = {
      //Fire missles at x,y,z for a random range
      None
    }
  }

  object Illuminate extends GameAction {
    def apply(size: Int): Option[GameAction] = {
      //make visible x,y,z to both players
      None
    }
  }

  object RandomTransport extends GameAction {
    def apply(size: Int): Option[GameAction] = {
      None
    }
  }
}
