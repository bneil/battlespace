package com.neilconcepts.battlespace.storage

import com.neilconcepts.battlespace.domain.Messages.{ RegError, RegMessage }
import com.neilconcepts.battlespace.domain.bst.{ Player, PlayerId }
import com.twitter.util.Future

/**
 * Registration ::
 * Your standard crud impl for the registration routes
 */
trait RegistrationStorage {
  type RegResponse = Future[Either[RegError, RegMessage]]

  def createRegistration(id: PlayerId): Future[RegMessage]
  def removeRegistration(id: PlayerId): RegResponse
  def updateRegistration(oldID: PlayerId, updatedPlayer: Player): RegResponse
  def readRegistration(id: PlayerId): Future[Option[Player]]
  def retrieveRegistrations: Future[Seq[Player]]
}
