package models

import java.time.LocalDateTime

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Passenger(
 idPsg: Int,
 name: String
)

class PassengerTable(tag: Tag) extends Table[Passenger](tag, "passengers"){
  val idPsg = column[Int]("id_psg", O.PrimaryKey)
  val name = column[String]("name")

  def * = (idPsg, name) <> (Passenger.apply _ tupled, Passenger.unapply)
}

object PassengerTable{
  val table = TableQuery[PassengerTable]
}

class PassengerRepository(db: Database) {
  val passengerTableQuery = TableQuery[PassengerTable]

  def create(passenger: Passenger): Future[Passenger] =
    db.run(passengerTableQuery returning passengerTableQuery += passenger)
}

case class PassInTrip(
  tripNoFk: Int,
  date: Option[LocalDateTime],
  idPsgFk: Int,
  place: String
)

class PassInTripTable(tag: Tag) extends Table[PassInTrip](tag, "passengers_in_trip"){
  val tripNoFk = column[Int]("trip_no_fk")
  val date = column[Option[LocalDateTime]]("date")
  val idPsgFk = column[Int]("id_psg_fk")
  val place = column[String]("place")

  def pk = primaryKey("pk_pass_in", (date, tripNoFk, idPsgFk))

  val passengerIdFk = foreignKey("passenger_id_fk", idPsgFk, TableQuery[PassengerTable])(_.idPsg)
  val tripIdFk = foreignKey("trip_id_fk", tripNoFk, TableQuery[TripTable])(_.tripNo)
  def * = (tripNoFk, date, idPsgFk, place) <> (PassInTrip.apply _ tupled, PassInTrip.unapply)
}

object PassInTripTable{
  val table = TableQuery[PassInTripTable]
}

class PassInTripRepository(db: Database) {
  val passInTripTableQuery = TableQuery[PassInTripTable]

  def create(passInTrip: PassInTrip): Future[PassInTrip] =
    db.run(passInTripTableQuery returning passInTripTableQuery += passInTrip)
}