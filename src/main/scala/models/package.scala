import slick.jdbc.PostgresProfile.api._


package object models {

  implicit val dateTimeToTimeStampMapper =
    MappedColumnType.base[java.time.LocalDateTime, java.sql.Timestamp](java.sql.Timestamp.valueOf, _.toLocalDateTime)

  /*: JdbcType[LocalDateTime] =
    MappedColumnType.base[LocalDateTime, Timestamp](
      d => Timestamp.from(d.toInstant(ZoneOffset.ofHours(0))),
      d => d.toLocalDateTime
    )*/

}
