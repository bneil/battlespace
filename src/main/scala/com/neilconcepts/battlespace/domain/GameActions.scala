package com.neilconcepts.battlespace.domain

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

  object Takes extends GameAction {
    def apply(size:Int): Unit ={
      //Determine x,y,z interactions
    }
  }

  object Explode extends GameAction {
    def apply(size: Int): Unit = {
      //Fire missles at x,y,z for a random range
    }
  }

  object Illuminate extends GameAction {
    def apply(size: Int): Unit = {
      //make visible x,y,z to both players
    }
  }
}
