package com.neilconcepts.battlespace.storage.mem

import com.neilconcepts.battlespace.domain.Messages._
import com.neilconcepts.battlespace.domain.bst.{ Player, PlayerId }
import com.neilconcepts.battlespace.storage.RegistrationStorage
import com.twitter.util.Future

import scala.collection.mutable

class InMemRegistration extends RegistrationStorage {
  private[this] val _registration = mutable.Map.empty[PlayerId, Player]

  override def createRegistration(id: PlayerId): Future[RegMessage] = Future(
    _registration.synchronized {
      _registration.get(id) match {
        case Some(player) =>
          RegCreated(player)
        case None =>
          val player = Player(id)
          _registration(id) = player
          RegCreated(player)
      }
    }
  )

  override def removeRegistration(id: PlayerId): RegResponse = Future(
    _registration.synchronized {
      if (_registration.contains(id)) {
        _registration.remove(id)
        Left(RegRemoved)
      } else {
        Right(RegFailed("Not able to remove in memory"))
      }
    }
  )

  override def readRegistration(id: PlayerId): Future[Option[Player]] = Future(
    _registration.get(id)
  )

  override def updateRegistration(oldID: PlayerId, updatedPlayer: Player): RegResponse = Future(
    _registration.synchronized {
      _registration.get(oldID) match {
        case Some(player) =>
          _registration.remove(oldID)
          _registration(updatedPlayer.id) = updatedPlayer
          Left(RegUpdated)

        case None =>
          Right(RegFailed("Not able to update in mem"))
      }
    }
  )
}
