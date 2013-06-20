package controllers;

import models.Project;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

public class ProjectController extends Controller {

    private static final DynamicForm dynForm = play.data.Form.form();

    public static Result add() {

        Project newProject = Project.create("New project", dynForm.bindFromRequest().get("group"), request().username());
        return ok(views.html.projects.item.render(newProject));
    }

    public static Result rename(Long project) {
        if (Secured.isMemberOf(project)) {
            return ok(Project.rename(project, dynForm.bindFromRequest().get("name")));
        } else {
            return forbidden();
        }
    }
}
