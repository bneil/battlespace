package com.neilconcepts.battlespace.domain

import com.neilconcepts.battlespace.domain.bst.Player

object Messages {
  sealed trait RegMessage
  case object RegUpdated extends RegMessage
  case class RegCreated(player: Player) extends RegMessage
  case object RegRemoved extends RegMessage

  type ErrorMsg = String
  sealed trait Error
  sealed trait RegError extends Error
  case class RegFailed(msg: ErrorMsg) extends RegError
}
