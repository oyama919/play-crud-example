package models

import play.api.data.Form
import play.api.data.Forms._

object PersonForm {
  val t = text.verifying(error = "必須項目です", constraint = _.nonEmpty)

  val form: Form[PersonForm] = Form {
    mapping(
      "name" -> t
        .verifying(error = "３文字以上入力してください", constraint = 3 <= _.length)
        .verifying(error = "10文字以内入力してください", constraint = _.length <= 10),
      "mail" -> t
        .verifying(error = "メールアドレスを入力してくださいgi",
                   constraint =
                     _.matches("""([a-zA-Z0-9\.\_-]+)@([a-zA-Z0-9\.\_-]+)""")),
      "tel" -> t
        .verifying(error = "半角の数値とハイフンのみ入力可。",
                   constraint = _.matches("""[1-9-]+"""))
    )(PersonForm.apply)(PersonForm.unapply)
  }

  val findForm: Form[PersonFind] = Form {
    mapping(
      "find" -> t
        .verifying(error = "10文字以内入力してください", constraint = _.length <= 10),
    )(PersonFind.apply)(PersonFind.unapply)
  }
}

case class PersonForm(name: String, mail: String, tel: String)
case class PersonFind(find: String)
