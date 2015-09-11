package com.neilconcepts.battlespace.domain

import java.util.UUID

object bst {
  type PlayerID = UUID
  type Email = String

  case class Player(id: PlayerID) {
    override def toString() = id.toString
  }
  case class Registration(email: Email)
}
