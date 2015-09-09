package com.neilconcepts.battlespace.storage.mem

import com.neilconcepts.battlespace.domain.Messages._
import com.neilconcepts.battlespace.domain.bst.{ Player, PlayerID }
import com.neilconcepts.battlespace.storage.Registration
import com.twitter.util.Future

import scala.collection.mutable

class InMemRegistration extends Registration {
  private[this] val _registration = mutable.Map.empty[PlayerID, Player]

  override def createRegistration(id: PlayerID): RegResponse = Future(
    _registration.synchronized {
      _registration.get(id) match {
        case Some(player) =>
          Left(RegCreated(player))
        case None =>
          Right(RegFailed("Not in memory"))
      }
    }
  )

  override def removeRegistration(id: PlayerID): RegResponse = Future(
    _registration.synchronized {
      if (_registration.contains(id)) {
        _registration.remove(id)
        Left(RegRemoved)
      } else {
        Right(RegFailed("Not able to remove in memory"))
      }
    }
  )

  override def readRegistration(id: PlayerID): Future[Option[Player]] = Future(
    _registration.get(id)
  )

  override def updateRegistration(oldID: PlayerID, updatedPlayer: Player): RegResponse = Future(
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
