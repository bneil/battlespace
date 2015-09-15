package com.neilconcepts.battlespace.storage

import com.neilconcepts.battlespace.domain.Messages.{ RegError, RegMessage }
import com.neilconcepts.battlespace.domain.bst.{ Player, PlayerID }
import com.twitter.util.Future

/**
 * Registration ::
 * Your standard crud impl for the registration routes
 */
trait RegistrationStorage {
  type RegResponse = Future[Either[RegMessage, RegError]]

  def createRegistration(id: PlayerID): Future[RegMessage]
  def removeRegistration(id: PlayerID): RegResponse
  def updateRegistration(oldID: PlayerID, updatedPlayer: Player): RegResponse
  def readRegistration(id: PlayerID): Future[Option[Player]]
}
