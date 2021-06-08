package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints.min
import play.api.i18n.I18nSupport
import play.api.mvc._
import views.html.Index

import javax.inject._

case class DateField(day: Int, month: Int, year: Int)

@Singleton
class HomeController @Inject() (
  val controllerComponents: ControllerComponents,
  indexPage: Index
) extends BaseController
    with I18nSupport {

  var form = Form( // just trying to demo things you can do, not be a recommendation
    "dateOfBirth" -> mapping(
      "day"   -> number.verifying(min(1, errorMessage = "day of month must be over 1")),
      "month" -> number,
      "year"  -> number
    )(DateField.apply)(DateField.unapply)
      .verifying("dateOfBirth.tooYoung", dateOfBirth => dateOfBirth.year < 2000)
  )

  def index() = Action { implicit request =>
    Ok(
      indexPage(
        form.bind( // bind rather than fill to trigger validation, and show errors on GET request
          Map(
            "dateOfBirth.day"   -> "0", // to show that dateOfBirth.tooYoung isn't reached
            "dateOfBirth.month" -> "10",
            "dateOfBirth.year"  -> "2001" // this is invalid, but it isn't checked if validation of nested field fails
          )
        )
      )
    )
  }
}
