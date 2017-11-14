package tasks

import models._
import slick.jdbc.PostgresProfile.api._

class Queries(db: Database) {


  def queryForTask63 = {
    val subquery = PassInTripTable.table.groupBy(p => (p.place, p.idPsgFk))
      .map { case ((place, id), group) => (id, group.length) }
      .filter { case (id, count) => count > 1 }
      .map { case (id, count) => id }

    val query = PassengerTable.table.filter(_.idPsg in subquery).map(_.name)

    db.run(query.result)
  }

  def queryForTask68 = {
    val subquery1 = TripTable.table
      .filter(t => t.townFrom >= t.townTo)
      .groupBy(t => (t.townFrom, t.townTo))
      .map{ case ((townFrom, townTo), group) => (group.length ,townFrom, townTo)}
    val subquery2 = TripTable.table.filter(t => t.townTo > t.townFrom)
      .groupBy(t => (t.townFrom, t.townTo))
      .map{ case ((townFrom, townTo), group) => (group.length ,townFrom, townTo)}
    val t = subquery1 union subquery2

    db.run(t.result)
  }

  def queryForTask67 = {
    val query = TripTable.table.groupBy(t => (t.townFrom, t.townTo))
      .map { case ((townFrom, townTo), group) => (group.length, townFrom, townTo)}
      .sortBy{case (count, townFrom, townTo) => count}
      .take(1)

    db.run(query.length.result)
  }
}
