package controllers


import java.sql._

import javax.inject._
import play.api.db.Database
import play.api.mvc._


@Singleton
class HomeController @Inject()(db: Database, cc: MessagesControllerComponents)
  extends MessagesAbstractController(cc) {

  def index() = Action {implicit request =>
    var msg = "database record:<br><ul>"
    try {
      db.withConnection { conn =>
        val stmt = conn.createStatement
        val rs = stmt.executeQuery("SELECT * from people")
        while (rs.next) {
          msg += "<li>" + rs.getInt("id") + ":" + rs.getString("name") + "</li>"
        }
        msg += "</ul>"
      }
    } catch {
      case e:SQLException =>
        msg = "<li>no record...</li>"
    }
    Ok(views.html.index(
      msg
    ))
  }
}
