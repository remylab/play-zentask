package pages;

import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.domain.FluentWebElement;

import pages.components.DrawerGroup;

public class Drawer {

    private final FluentWebElement element;

    public Drawer(FluentWebElement element) {
        this.element = element;
    }

    public static Drawer from(Fluent fluent) {
        return new Drawer(fluent.findFirst("nav"));
    }

    public DrawerGroup group(String name) {
        return new DrawerGroup(element.findFirst("#projects > li[data-group=" + name + "]"));
    }
}