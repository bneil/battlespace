package com.neilconcepts.battlespace.routes

import java.util.UUID

import com.neilconcepts.battlespace.domain.Messages.{ RegMessage, RegError }
import com.neilconcepts.battlespace.domain.bst.{ Player, PlayerID }
import com.neilconcepts.battlespace.storage.Database
import com.twitter.finagle.httpx.Response
import io.circe.syntax._
import io.finch.circe._
import io.finch.response._
import io.finch.route.{ Router, string, _ }
import io.circe.generic.auto._

/**
 * RegistrationRoutes ::
 * These are all responsible for registering new accounts or reporting back that
 * the accounts are no accessable. Registrations are apart of the [[com.neilconcepts.battlespace.storage.RegistrationStorage]]
 * case class and are in the bs types domain.
 *
 * getRegUser -> this is currently a testing route and will be deprecated once I
 *               get used to the rest of the system.
 *
 * createRegUser -> simple route to create a new player and gameState
 *
 */
trait RegistrationRoutes extends RegistrationRouteActions {
  implicit def str2uuid: (String) => PlayerID = (x: String) => UUID.fromString(x)

  def getRegUser(db: Database): Router[Response] =
    get("c" / string) { id: String =>
      for (
        regPlayer <- db.registration.readRegistration(id)
      ) yield extractRegPlayer(regPlayer)
    }

  def createRegUser(db: Database): Router[Response] =
    get("c") {
      val newID = UUID.randomUUID()
      db.registration.createRegistration(newID)
      Created(Map("player" -> newID.toString).asJson.noSpaces)
    }
}

trait RegistrationRouteActions {
  def extractRegPlayer: Option[Player] => Response = {
    case Some(registeredPlayer) =>
      Created(Map("player" -> registeredPlayer.toString).asJson.noSpaces)
    case None =>
      Created(Map("player" -> "registration not found").asJson.noSpaces)
  }
}