package models

import java.time.LocalDateTime

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Trip (
  tripNo: Int,
  idCompFk: Int,
  plane: String,
  townFrom: String,
  townTo: String,
  timeOut: Option[LocalDateTime],
  timeIn: Option[LocalDateTime]
)

class TripTable(tag: Tag) extends Table[Trip](tag, "trips"){
  val tripNo = column[Int]("trip_no", O.PrimaryKey)
  val idCompFk = column[Int]("id_comp_fk")
  val plane = column[String]("plane")
  val townFrom = column[String]("town_from")
  val townTo = column[String]("town_to")
  val timeOut = column[Option[LocalDateTime]]("time_out")
  val timeIn = column[Option[LocalDateTime]]("time_in")

  val passengerFk = foreignKey("passenger_id_fk", idCompFk, TableQuery[CompanyTable])(_.idComp)
  def * = (tripNo, idCompFk, plane, townFrom, townTo, timeOut, timeIn) <> (Trip.apply _ tupled, Trip.unapply)
}

object TripTable{
  val table = TableQuery[TripTable]
}

class TripRepository(db: Database) {
  val tripTableQuery = TableQuery[TripTable]

  def create(trip: Trip): Future[Trip] =
    db.run(tripTableQuery returning tripTableQuery += trip)
}
