package utils

import java.time.format.DateTimeFormatter

import models._
import slick.jdbc.PostgresProfile.api._
import tasks.Queries

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main {
  val db = Database.forConfig("scalaxdb")
  val companyRepository = new CompanyRepository(db)
  val tripRepository = new TripRepository(db)
  val passengerRepository = new PassengerRepository(db)
  val passInTripRepository = new PassInTripRepository(db)
  val formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")

  def main(args: Array[String]): Unit = {
    init()
    fillDatabase()
    Queries.queryForTask63
  }

  def init(): Unit ={
    Await.result(db.run(CompanyTable.table.schema.create), Duration.Inf)
    Await.result(db.run(PassengerTable.table.schema.create), Duration.Inf)
    Await.result(db.run(TripTable.table.schema.create), Duration.Inf)
    Await.result(db.run(PassInTripTable.table.schema.create), Duration.Inf)
  }

  def fillDatabase(): Unit = {
    FillWithData.fillCompanyTable()
    FillWithData.fillPassengerTable()
    FillWithData.fillTripsTable()
    FillWithData.fillPassInTrips()
  }

}