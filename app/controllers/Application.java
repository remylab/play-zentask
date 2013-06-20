package controllers;

import models.Project;
import models.Task;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class Application extends Controller {

	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(views.html.index.render(Project.find.all(), Task.find.all()));
	}

}
