package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.GameObjects.GameObject

/**
 * board ::
 * The game board is made of a Matrix which
 * contains GameSpace areas, really nothing
 * more is known about space so it should be
 * left at that.
 */
object Board {
  type X = Int
  type Y = Int
  type Z = Int
  type GameSpace = Option[GameObject]

  case class Point(x: X, y: Y, z: Z)
  case class BattleSpaceBoard(gb: Map[Point, GameSpace])
}