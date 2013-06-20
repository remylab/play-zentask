package controllers;

import models.Project;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Login.index());
    }

    public static boolean isMemberOf(Long project) {
        return Project.isMember(project, Context.current().request().username());
    }
}