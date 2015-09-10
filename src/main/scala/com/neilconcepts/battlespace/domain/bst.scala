package com.neilconcepts.battlespace.domain

import java.util.UUID

object bst {
  type PlayerID = UUID
  case class Player(id: PlayerID) {
    override def toString() = id.toString
  }
}
