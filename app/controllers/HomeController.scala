package controllers


import javax.inject._
import models._
import play.api.data.Form
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class HomeController @Inject()(repository: PersonRepository,
  cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  def index() = Action.async {implicit request =>
    repository.list().map { people =>
      Ok(views.html.index(
        "People Data.", people
      ))
    }
  }

  def show(id:Int) = Action.async {implicit request =>
    repository.get(id).map { person =>
      Ok(views.html.show(
        "People Data.", person
      ))
    }
  }

  def add() = Action {implicit request =>
    Ok(views.html.add(
      "フォームを記入して下さい。",
      Person.personForm
    ))
  }


  def create() = Action.async { implicit request =>
    Person.personForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.add("error.", errorForm)))
      },
      person => {
        repository.create(person.name, person.mail, person.tel).map { _ =>
          Redirect(routes.HomeController.index)
        }
      }
    )
  }

  def edit(id:Int) = Action.async {implicit request =>
    repository.get(id).map { person =>
      val fdata:Form[PersonForm] = Person.personForm
        .fill(models.PersonForm(person.name, person.mail, person.tel))
      Ok(views.html.edit(
        "Edit Person.", fdata, id
      ))
    }
  }


  def update(id:Int) = Action.async { implicit request =>
    Person.personForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.edit("error.", errorForm, id)))
      },
      person => {
        repository.update(id, person.name, person.mail, person.tel).map { _ =>
          Redirect(routes.HomeController.index)
        }
      }
    )
  }
}
