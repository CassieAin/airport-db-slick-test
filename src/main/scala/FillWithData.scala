import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import Main.{companyRepository, passInTripRepository, passengerRepository, tripRepository}
import models.{Company, PassInTrip, Passenger, Trip}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object FillWithData {

  val formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")

  def fillCompanyTable(): Unit ={
    Await.result(companyRepository.create(Company(1,"Don_avia")), Duration.Inf)
    Await.result(companyRepository.create(Company(2,"Aeroflot")), Duration.Inf)
    Await.result(companyRepository.create(Company(3,"Dale_avia")), Duration.Inf)
    Await.result(companyRepository.create(Company(4,"air_France")), Duration.Inf)
    Await.result(companyRepository.create(Company(5,"British_AW")), Duration.Inf)
  }

  def fillTripsTable(): Unit = {
    Await.result(tripRepository.create(Trip(1100,4,"Boeing","Rostov","Paris",Option(LocalDateTime.parse("19000101 14:30:00",formatter)),Option(LocalDateTime.parse("19000101 17:50:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1101,4,"Boeing","Paris","Rostov",        Option(LocalDateTime.parse("19000101 08:12:00",formatter)),Option(LocalDateTime.parse("19000101 11:45:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1123,3,"TU-154","Rostov","Vladivostok",  Option(LocalDateTime.parse("19000101 16:20:00",formatter)),Option(LocalDateTime.parse("19000101 03:40:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1124,3,"TU-154","Vladivostok","Rostov",  Option(LocalDateTime.parse("19000101 09:00:00",formatter)),Option(LocalDateTime.parse("19000101 19:50:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1145,2,"IL-86","Moscow","Rostov",        Option(LocalDateTime.parse("19000101 09:35:00",formatter)),Option(LocalDateTime.parse("19000101 11:23:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1146,2,"IL-86","Rostov","Moscow",        Option(LocalDateTime.parse("19000101 17:55:00",formatter)),Option(LocalDateTime.parse("19000101 20:01:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1181,1,"TU-134","Rostov","Moscow",       Option(LocalDateTime.parse("19000101 06:12:00",formatter)),Option(LocalDateTime.parse("19000101 08:01:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1182,1,"TU-134","Moscow","Rostov",       Option(LocalDateTime.parse("19000101 12:35:00",formatter)),Option(LocalDateTime.parse("19000101 14:30:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1187,1,"TU-134","Rostov","Moscow",       Option(LocalDateTime.parse("19000101 15:42:00",formatter)),Option(LocalDateTime.parse("19000101 17:39:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1188,1,"TU-134","Moscow","Rostov",       Option(LocalDateTime.parse("19000101 22:50:00",formatter)),Option(LocalDateTime.parse("19000101 00:48:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1195,1,"TU-154","Rostov","Moscow",       Option(LocalDateTime.parse("19000101 23:30:00",formatter)),Option(LocalDateTime.parse("19000101 01:11:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(1196,1,"TU-154","Moscow","Rostov",       Option(LocalDateTime.parse("19000101 04:00:00",formatter)),Option(LocalDateTime.parse("19000101 05:45:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7771,5,"Boeing","London","Singapore",    Option(LocalDateTime.parse("19000101 01:00:00",formatter)),Option(LocalDateTime.parse("19000101 11:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7772,5,"Boeing","Singapore","London",    Option(LocalDateTime.parse("19000101 12:00:00",formatter)),Option(LocalDateTime.parse("19000101 02:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7773,5,"Boeing","London","Singapore",    Option(LocalDateTime.parse("19000101 03:00:00",formatter)),Option(LocalDateTime.parse("19000101 13:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7774,5,"Boeing","Singapore","London",    Option(LocalDateTime.parse("19000101 14:00:00",formatter)),Option(LocalDateTime.parse("19000101 06:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7775,5,"Boeing","London","Singapore",    Option(LocalDateTime.parse("19000101 09:00:00",formatter)),Option(LocalDateTime.parse("19000101 20:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7776,5,"Boeing","Singapore","London",    Option(LocalDateTime.parse("19000101 18:00:00",formatter)),Option(LocalDateTime.parse("19000101 08:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7777,5,"Boeing","London","Singapore",    Option(LocalDateTime.parse("19000101 18:00:00",formatter)),Option(LocalDateTime.parse("19000101 06:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(7778,5,"Boeing","Singapore","London",    Option(LocalDateTime.parse("19000101 22:00:00",formatter)),Option(LocalDateTime.parse("19000101 12:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(8881,5,"Boeing","London","Paris",        Option(LocalDateTime.parse("19000101 03:00:00",formatter)),Option(LocalDateTime.parse("19000101 04:00:00", formatter)))), Duration.Inf)
    Await.result(tripRepository.create(Trip(8882,5,"Boeing","Paris","London",        Option(LocalDateTime.parse("19000101 22:00:00",formatter)),Option(LocalDateTime.parse("19000101 23:00:00", formatter)))), Duration.Inf)
  }

  def fillPassengerTable(): Unit = {
    Await.result(passengerRepository.create(Passenger(1,"Bruce Willis")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(2,"George Clooney")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(3,"Kevin Costner")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(4,"Donald Sutherland")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(5,"Jennifer Lopez")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(6,"Ray Liotta")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(7,"Samuel L. Jackson")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(8,"Nikole Kidman")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(9,"Alan Rickman")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(10,"Kurt Russell")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(11,"Harrison Ford")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(12,"Russell Crowe")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(13,"Steve Martin")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(14,"Michael Caine")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(15,"Angelina Jolie")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(16,"Mel Gibson")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(17,"Michael Douglas")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(18,"John Travolta")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(19,"Sylvester Stallone")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(20,"Tommy Lee Jones")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(21,"Catherine Zeta-Jones")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(22,"Antonio Banderas")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(23,"Kim Basinger")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(24,"Sam Neill")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(25,"Gary Oldman")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(26,"Clint Eastwood")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(27,"Brad Pitt")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(28,"Johnny Depp")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(29,"Pierce Brosnan")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(30,"Sean Connery")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(31,"Bruce Willis")), Duration.Inf)
    Await.result(passengerRepository.create(Passenger(37,"Mullah Omar")), Duration.Inf)
  }

  def fillPassInTrips():Unit = {

    Await.result(passInTripRepository.create(PassInTrip(1100,Option(LocalDateTime.parse("20030429 00:00:00",formatter)),1,"1a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1123,Option(LocalDateTime.parse("20030405 00:00:00",formatter)),3,"2a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1123,Option(LocalDateTime.parse("20030408 00:00:00",formatter)),1,"4c")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1123,Option(LocalDateTime.parse("20030408 00:00:00",formatter)),6,"4b")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1124,Option(LocalDateTime.parse("20030402 00:00:00",formatter)),2,"2d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1145,Option(LocalDateTime.parse("20030405 00:00:00",formatter)),3,"2c")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1181,Option(LocalDateTime.parse("20030401 00:00:00",formatter)),1,"1a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1181,Option(LocalDateTime.parse("20030401 00:00:00",formatter)),6,"1b")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1181,Option(LocalDateTime.parse("20030401 00:00:00",formatter)),8,"3c")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1181,Option(LocalDateTime.parse("20030413 00:00:00",formatter)),5,"1b")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1182,Option(LocalDateTime.parse("20030413 00:00:00",formatter)),5,"4b")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1187,Option(LocalDateTime.parse("20030414 00:00:00",formatter)),8,"3a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1188,Option(LocalDateTime.parse("20030401 00:00:00",formatter)),8,"3a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1182,Option(LocalDateTime.parse("20030413 00:00:00",formatter)),9,"6d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1145,Option(LocalDateTime.parse("20030425 00:00:00",formatter)),5,"1d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(1187,Option(LocalDateTime.parse("20030414 00:00:00",formatter)),10,"3d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(8882,Option(LocalDateTime.parse("20051106 00:00:00",formatter)),37,"1a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7771,Option(LocalDateTime.parse("20051107 00:00:00",formatter)),37,"1c")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7772,Option(LocalDateTime.parse("20051107 00:00:00",formatter)),37,"1a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(8881,Option(LocalDateTime.parse("20051108 00:00:00",formatter)),37,"1d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7778,Option(LocalDateTime.parse("20051105 00:00:00",formatter)),10,"2a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7772,Option(LocalDateTime.parse("20051129 00:00:00",formatter)),10,"3a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7771,Option(LocalDateTime.parse("20051104 00:00:00",formatter)),11,"4a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7771,Option(LocalDateTime.parse("20051107 00:00:00",formatter)),11,"1b")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7771,Option(LocalDateTime.parse("20051109 00:00:00",formatter)),11,"5a")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7772,Option(LocalDateTime.parse("20051107 00:00:00",formatter)),12,"1d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7773,Option(LocalDateTime.parse("20051107 00:00:00",formatter)),13,"2d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7772,Option(LocalDateTime.parse("20051129 00:00:00",formatter)),13,"1b")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(8882,Option(LocalDateTime.parse("20051113 00:00:00",formatter)),14,"3d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7771,Option(LocalDateTime.parse("20051114 00:00:00",formatter)),14,"4d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7771,Option(LocalDateTime.parse("20051116 00:00:00",formatter)),14,"5d")), Duration.Inf)
    Await.result(passInTripRepository.create(PassInTrip(7772,Option(LocalDateTime.parse("20051129 00:00:00",formatter)),14,"1c")), Duration.Inf)

  }
 }
