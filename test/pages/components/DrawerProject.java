package pages.components;

import org.fluentlenium.core.domain.FluentWebElement;

import com.google.common.base.Predicate;

public class DrawerProject {
    private final FluentWebElement element;

    public DrawerProject(FluentWebElement element) {
        this.element = element;
    }

    public String name() {
        FluentWebElement a = element.findFirst("a.name");
        if (a.isDisplayed()) {
            return a.getText().trim();
        }

        return null;
    }

    public Predicate<Object> isInEdit() {
        return new Predicate<Object>() {
            @Override
            public boolean apply(Object o) {
                return element.findFirst("input") != null && element.findFirst("input").isDisplayed();
            }
        };
    }
}
