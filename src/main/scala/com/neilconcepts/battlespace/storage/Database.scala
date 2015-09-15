package com.neilconcepts.battlespace.storage

import com.neilconcepts.battlespace.storage.mem.InMemRegistration

class Database {
  val registration: RegistrationStorage = new InMemRegistration()
}
