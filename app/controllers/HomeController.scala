package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action {
    Ok(views.html.index(
      "this is form message",
    ))
  }


  def form() = Action { request =>
    val form:Option[Map[String,Seq[String]]] =
      request.body.asFormUrlEncoded
    val param:Map[String,Seq[String]] = form.getOrElse(Map())
    val name:String = param.get("name").get(0)
    val password:String = param.get("pass").get(0)
    Ok(views.html.index(
      "name:" + name + ", password:" + password
    ))
  }
}
