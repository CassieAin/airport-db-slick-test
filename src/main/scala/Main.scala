import models._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

object Main {
  val db = Database.forConfig("scalaxdb")
  val companyRepository = new CompanyRepository(db)
  val tripRepository = new TripRepository(db)
  val passengerRepository = new PassengerRepository(db)
  val passInTripRepository = new PassInTripRepository(db)


  def main(args: Array[String]): Unit = {
    init()
  }

  def init(): Unit ={
    Await.result(db.run(CompanyTable.table.schema.create), Duration.Inf)
    Await.result(db.run(PassengerTable.table.schema.create), Duration.Inf)
    Await.result(db.run(TripTable.table.schema.create), Duration.Inf)
    Await.result(db.run(PassInTripTable.table.schema.create), Duration.Inf)
  }


}
