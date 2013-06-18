package controllers;

import models.ZenUser;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Login extends Controller {

    public static Result index() {
        return ok(views.html.login.render(LoginForm));
    }

    public final static Form<LoginModel> LoginForm = new Form(LoginModel.class);

    public static class LoginModel {

        public String email;
        public String password;

        public String validate() {
            if (ZenUser.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

    }

    public static Result authenticate() {
        Form<LoginModel> loginForm = LoginForm.bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(routes.Application.index());
        }
    }
}
