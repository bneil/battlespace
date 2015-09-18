package com.neilconcepts.battlespace.storage

import com.neilconcepts.battlespace.storage.mem.{ InMemGameState, InMemRegistration }

class Database {
  val registration: RegistrationStorage = new InMemRegistration()
  val gameState: GameStateStorage = new InMemGameState()
}
