package models

import java.sql.Timestamp

case class Message (
  id: Int,
  personId:Int,
  message: String,
  created: Timestamp
)
