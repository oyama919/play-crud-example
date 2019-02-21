package models

import play.api.data.Form
import play.api.data.Forms._

object PersonForm {
  val form: Form[PersonForm] = Form {
    mapping(
      "name" -> text,
      "mail" -> text,
      "tel" -> text
    )(PersonForm.apply)(PersonForm.unapply)
  }

  val findForm: Form[PersonFind] = Form {
    mapping(
      "find" -> text
    )(PersonFind.apply)(PersonFind.unapply)
  }
}

case class PersonForm(name:String, mail:String, tel:String)
case class PersonFind(find:String)
