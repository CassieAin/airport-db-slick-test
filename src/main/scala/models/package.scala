import java.time.{LocalDateTime, ZoneOffset}
import java.sql.Timestamp

import slick.jdbc.JdbcType
import slick.jdbc.PostgresProfile.api._


package object models {

  implicit val dateTimeToLongMapper: JdbcType[LocalDateTime] =
    MappedColumnType.base[LocalDateTime, Timestamp](
      d => Timestamp.from(d.toInstant(ZoneOffset.ofHours(0))),
      d => d.toLocalDateTime
    )
}
