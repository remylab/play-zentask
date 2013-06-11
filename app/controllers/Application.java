package controllers;

import models.Project;
import models.Task;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(Project.find.all(), Task.find.all()));
    }
}