package tasks

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import models._
import slick.jdbc.PostgresProfile.api._
import utils.Main.formatter

object Queries {
  val db = Database.forConfig("scalaxdb")

  def queryForTask63 = {
    val subquery = PassInTripTable.table.groupBy(p => (p.place, p.idPsgFk))
      .map { case ((place, id), group) => (id, group.length) }
      .filter { case (id, count) => count > 1 }
      .map { case (id, count) => id }

    val query = PassengerTable.table.filter(_.idPsg in subquery).map(_.name)

    db.run(query.result)
  }

  def queryForTask67 = {
    val query = TripTable.table.groupBy(t => (t.townFrom, t.townTo))
      .map { case ((townFrom, townTo), group) => (group.length, townFrom, townTo)}
      .sortBy{case (count, townFrom, townTo) => count}
      .take(1)

    db.run(query.length.result)
  }

  def queryForTask68 = {
    val subquery1 = TripTable.table.filter(t => t.townFrom >= t.townTo)
      .groupBy(t => (t.townFrom, t.townTo))
      .map{ case ((townFrom, townTo), group) => (group.length, townFrom, townTo)}

    val subquery2 = TripTable.table.filter(t => t.townTo > t.townFrom)
      .groupBy(t => (t.townFrom, t.townTo))
      .map{ case ((townFrom, townTo), group) => (group.length, townFrom, townTo)}

    val t = subquery1 union subquery2
    val tt = t.map { case subquery1 => (subquery1._1, subquery1._2, subquery1._3) }
      .groupBy { case (sub1, sub2, sub3) => (sub2, sub3) }
      .map { case ((sub2, sub3), group) => (group.map(_._1).sum, sub2, sub3) }
      .sortBy { case (sum, sub2, sub3) => sum }
      .take(1)
    db.run(tt.length.result)
  }

  def queryForTask72 = {
    val t = (for {
      pit <- PassInTripTable.table
      t <- TripTable.table if pit.tripNoFk === t.tripNo
    } yield (pit, t))
      .groupBy{case (pit,t) => (pit.idPsgFk, t.idCompFk)}
      .map{case ((idPsgFk, idCompFk), group) => (idPsgFk, idCompFk, group.length)}

    val tt = t.filter{case (idPsgFk, idCompFk, count) => count === 1}
      .groupBy{ case (idPsgFk, idCompFk, count) => idPsgFk}
      .map{ case (idPsgFk, group) => (idPsgFk, group.map(_._1).max)}

    val query = (for {
      p <- PassengerTable.table
      t <- tt if p.idPsg === t._1
    } yield (p, t))
      .map{ case (p,t) => (p.name, t._2)}
      .sortBy(_._2)
      .take(1)
    db.run(query.result)
  }

  def queryForTask77 = {
    val x = (for {
      pit <- PassInTripTable.table
      t <- TripTable.table if pit.tripNoFk === t.tripNo
    } yield (pit, t))
      .filter{case (pit, t) => (t.townFrom === "Rostov")}
      .groupBy{case (pit, t) => (pit.tripNoFk, pit.date)}
      .map{case ((tripNoFk, date), group) => (date, group.countDistinct)}

    val query = x.sortBy(_._1.desc).take(1)
    db.run(query.result)
  }

  /*!!!!!!!!!!!!!!!!!!!!*/
  def queryForTask95 = {
    val query = (for {
      c <- CompanyTable.table
      t <- TripTable.table if c.idComp === t.idCompFk
      pit <- PassInTripTable.table if t.tripNo === pit.tripNoFk
    } yield (c, t, pit))
      .groupBy { case (c, t, pit) => (c.idComp, c.name) }
     // .map { case ((c, t, pit), group) => (group.map(_._1.name).countDistinct, group.map(_._2.tripNo).countDistinct,
      //  group.map(_._2.plane).countDistinct, group.map(_._3.idPsgFk).countDistinct, group.length)}

    db.run(query.result)
  }

  def queryForTask102 = {
    val subquery = (for {
      pit <- PassInTripTable.table
      t <- TripTable.table if pit.tripNoFk === t.tripNo
    } yield (pit, t))
      .map{ case (pit, t) => ( pit, Case.If(t.townFrom <= t.townTo).Then(t.townFrom ++ t.townTo).Else(t.townFrom ++ t.townTo) )}
      .groupBy(_._1.idPsgFk)
      // .filter { case (pit, t1) => ( t1.map(_._2).countDistinct === 1)}
      .map(_._1)
    val query = PassengerTable.table.filter(_.idPsg in subquery).map(_.name)
    db.run(query.result)
  }

  def queryForTask103 = {
    val query = (for {
      t <- TripTable.table
      tt <- TripTable.table  if t.tripNo < tt.tripNo
      ttt <- TripTable.table  if tt.tripNo < ttt.tripNo
    } yield (t.tripNo, tt.tripNo, ttt.tripNo))
      .groupBy{case (t, tt, ttt) => (t, tt, ttt)}
      .map{ case ((t, tt, ttt),group) => (group.map(_._1).min, group.map(_._1).max,
        group.map(_._2).min, group.map(_._2).max, group.map(_._3).min, group.map(_._3).max)}
    db.run(query.result)
  }

  def queryForTask107 = {
    val date = Option(LocalDateTime.parse("20030401 00:00:00", formatter))
    val subquery = (for {
      c <- CompanyTable.table
      t <- TripTable.table if c.idComp === t.idCompFk
      pit <- PassInTripTable.table if t.tripNo === pit.tripNoFk
    } yield (c, t, pit))
      .filter { case (c, t, pit) => (t.townFrom === "Rostov") && (t.timeOut <= date) }
        .map { case (c, t, pit) => (t.timeOut, pit.idPsgFk, c.name, t.tripNo) }
          .sortBy { case (date, pid, name, tripNo) => (date, pid) }
            .take(5)
              .drop(4)

    val query = subquery.map { case (date, pid, name, tripNo) => (date, name, tripNo) }

    db.run(query.result)
  }

  /*!!!!!!!!!!!!!!!!!!!!*/
  def queryForTask110 = {

    def days(t1: LocalDateTime, t2: LocalDateTime) = ChronoUnit.DAYS.between(t1, t2)

    val subquery = (for {
      pit <- PassInTripTable.table
      t <- TripTable.table if pit.tripNoFk === t.tripNo
    } yield (pit, t))
      .filter{ case (pit,t) => (t.timeIn < t.timeOut) }
      .map(_._1.idPsgFk)

    val query = PassengerTable.table.filter(_.idPsg in subquery).map(_.name)
    db.run(query.result)

    /*

   select name from passenger where id_psg in
      (select id_psg
       from pass_in_trip pit join trip t on pit.trip_no = t.trip_no
       where time_in < time_out and datepart(dw, date) = 7
      )
    */
  }

  /*!!!!!!!!!!!!!!!!!!!!*/
  def queryForTask114 = {
    val b = PassInTripTable.table
      .groupBy{ case pit => (pit.idPsgFk, pit.place)}
      .map{ case (id, place) => (id, place.length)}
    val bmax = b.map(_._2) //.max
    val b1 = b.filter{ case (id, count) => (count === count)}
      .map{ case (id, count) => (id._1, count)}


    val query = (for {
      p <- PassengerTable.table
      b1 <- b1 if p.idPsg === b1._1
    } yield (p, b1))
      .map{ case (p, b1) => (p.name, b1._2)}

    db.run(query.result)

    /*
      WITH b AS
      (SELECT ID_psg, COUNT(*) as cnt FROM Pass_In_Trip GROUP BY ID_psg, place),
      b1 AS
        (SELECT DISTINCT ID_psg, cnt FROM b WHERE cnt =(SELECT MAX(cnt) FROM b))
      SELECT name, cnt FROM b1 JOIN Passenger p ON (b1.ID_psg = p.ID_psg)
    */
  }
}
