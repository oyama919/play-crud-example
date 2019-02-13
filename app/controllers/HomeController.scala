package controllers

import controllers.MyForm._
import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: MessagesControllerComponents)
    extends MessagesAbstractController(cc) {
  def index() = Action {implicit request =>
    Ok(views.html.index(
      "Please Write name and pass",
      myform
    ))
  }

  def form() = Action { implicit request =>
    val form = myform.bindFromRequest
    println(form.data)
    val data = form.data
    Ok(views.html.index(
      "name:" + data.get("name").getOrElse("") + ", pass:" + data.get("password").getOrElse(""),
      form
    ))
  }
}
