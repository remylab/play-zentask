package controllers;

import models.Project;
import models.Task;
import models.ZenUser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(views.html.index.render(Project.findInvolving(request().username()), Task.findTodoInvolving(request().username()), ZenUser.find.byId(request().username())));

    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Login.index());
    }

}
