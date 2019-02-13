package controllers

import play.api.data._
import play.api.data.Forms._


object MyForm {

  case class Data(name: String, pass: String)

  val myform = Form(
    mapping(
      "name" -> text,
      "pass" -> text
    )(Data.apply)(Data.unapply)
  )
}
