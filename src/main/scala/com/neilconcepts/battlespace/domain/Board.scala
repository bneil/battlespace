package com.neilconcepts.battlespace.domain

import argonaut.Argonaut._
import argonaut.{ DecodeJson, CodecJson }
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

  val maxDimensions = 50
  case class Point(x: X, y: Y, z: Z)
  case class BattleSpaceBoard(gb: Map[Point, GameSpace])
  case class GameSpace(gs: Option[GameObject])

  def generateBoard(): BattleSpaceBoard = {
    val gameSpace: Vector[(Point, GameSpace)] = (for (
      x <- 1 to maxDimensions;
      y <- 1 to maxDimensions;
      z <- 1 to maxDimensions
    ) yield (Point(x, y, z), GameSpace(None))).toVector

    val board: Map[Point, GameSpace] = gameSpace.map(g => g._1 -> g._2).toMap
    BattleSpaceBoard(gb = board)
  }

  implicit val pointCodec: CodecJson[Point] =
    casecodec3(Point.apply, Point.unapply)("x", "y", "z")

  implicit def battleSpaceBoardCodec: DecodeJson[BattleSpaceBoard] =
    DecodeJson(c => for {
      gb <- (c --\ "gb").as[Map[Point, GameSpace]]
    } yield BattleSpaceBoard(gb))

  implicit val gameSpaceCodec: CodecJson[GameSpace] =
    casecodec1(GameSpace.apply, GameSpace.unapply)("gs")
}