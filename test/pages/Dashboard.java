package pages;

import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

import org.fluentlenium.core.FluentPage;

public class Dashboard extends FluentPage {
    @Override
    public String getUrl() {
        return "/";
    }

    @Override
    public void isAt() {
        assertThat(find("h1", withText("Dashboard"))).hasSize(1);
    }

    public Drawer drawer() {
        return Drawer.from(this);
    }
}