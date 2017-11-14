package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Company(
  idComp: Int,
  name: String
)

class CompanyTable(tag: Tag) extends Table[Company](tag, "companies"){
  val idComp = column[Int]("id_comp", O.PrimaryKey)
  val name = column[String]("name")

  def * = (idComp, name) <> (Company.apply _ tupled, Company.unapply)
}

object CompanyTable{
  val table = TableQuery[CompanyTable]
}

class CompanyRepository(db: Database) {
  val companyTableQuery = TableQuery[CompanyTable]

  def create(company: Company): Future[Company] =
    db.run(companyTableQuery returning companyTableQuery += company)

  def createInBatch(companies: List[Company]) =
    for(company <- companies) create(company)
    //companies.foreach(create(_))
}