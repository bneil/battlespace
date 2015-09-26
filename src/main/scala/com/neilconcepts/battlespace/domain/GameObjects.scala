package com.neilconcepts.battlespace.domain

import argonaut.{ EncodeJson, CodecJson }
import com.neilconcepts.battlespace.domain.GameObjects.GameObject

/**
 * GameObjects ::
 * All the things that take up space. And well
 * try to deter that space.
 * Other then that they each do thar own thing
 * when assigned to the gameboard.
 *
 * WormHole = Any projectile that enters
 */
object GameObjects {
  import GameActions._

  sealed trait Horizontal
  sealed trait Vertical
  type Direction = (Horizontal, Vertical)

  type Size = Int

  val objects = Seq(
    WormHole,
    FuelPlanet,
    MegaSun,
    Space
  )

  abstract class GameObject {
    val num: Int = 1
    val size: Size
    val birth: Option[GameAction]
    val life: Option[GameAction]
    val death: Option[GameAction]
    override def toString = "Generic Game Object"
  }

  case object WormHole extends GameObject {
    val size = 1
    val birth = Takes(3)
    val life = RandomTransport(3)
    val death = None //Immortal
    override def toString = "WormHole"
  }

  case object FuelPlanet extends GameObject {
    val size = 1
    val birth = None
    val life = None
    val death = Explode(5)
    override def toString = "FuelPlanet"
  }

  case object MegaSun extends GameObject {
    val size = 1
    val birth = Illuminate(5)
    val life = None
    val death = None
    override def toString = "MegaSun"
  }

  case object Probe extends GameObject {
    val size = 1
    val birth = None
    val life = Illuminate(5)
    val death = None
    override def toString = "Probe"
  }

  case object Space extends GameObject {
    val size = 1
    val birth = None
    val life = None
    val death = None
    override def toString = "Space"
  }
}
