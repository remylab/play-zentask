package controllers;

import models.Project;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class ProjectController extends Controller {

    private static final DynamicForm dynForm = play.data.Form.form();

    public static Result add() {

        Project newProject = Project.create("New project", dynForm.bindFromRequest().get("folder"), request().username());
        return ok(views.html.projects.item.render(newProject));
    }

    public static Result rename(Long project) {
        if (isMemberOf(project)) {
            return ok(Project.rename(project, dynForm.bindFromRequest().get("name")));
        } else {
            return forbidden();
        }
    }

    public static Result delete(Long project) {
        if (isMemberOf(project)) {
            Project.find.ref(project).delete();
            return ok();
        } else {
            return forbidden();
        }
    }

    private static boolean isMemberOf(Long project) {
        return Project.isMember(project, Context.current().request().username());
    }
}
