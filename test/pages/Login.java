package pages;

import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

import org.fluentlenium.core.FluentPage;

import controllers.routes;

public class Login extends FluentPage {
    @Override
    public String getUrl() {
        return routes.Login.index().url();
    }

    @Override
    public void isAt() {
        assertThat(find("h1", withText("Sign in"))).hasSize(1);
    }

    public void login(String email, String password) {
        fill("input").with(email, password);
        click("button");
    }
}
