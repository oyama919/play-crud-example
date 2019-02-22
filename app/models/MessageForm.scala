package models

import play.api.data.Form
import play.api.data.Forms._

object MessageForm {
  val form: Form[MessageForm] = Form {
    mapping(
      "personId" -> number,
      "message" -> nonEmptyText
    )(MessageForm.apply)(MessageForm.unapply)
  }
}

case class MessageForm(personId:Int, message:String)
case class MessageWithPerson(message:Message, person:Person)
