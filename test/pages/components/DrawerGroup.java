package pages.components;

import java.util.ArrayList;
import java.util.List;

import org.fluentlenium.core.domain.FluentWebElement;

import com.google.common.base.Predicate;

public class DrawerGroup {
    private final FluentWebElement element;

    public DrawerGroup(FluentWebElement element) {
        this.element = element;
    }

    public List<DrawerProject> projects() {
        List<DrawerProject> projects = new ArrayList<DrawerProject>();
        for (FluentWebElement e : (Iterable<FluentWebElement>) element.find("ul > li")) {
            projects.add(new DrawerProject(e));
        }
        return projects;
    }

    public DrawerProject project(String name) {
        for (DrawerProject p : projects()) {
            if (p.name().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public Predicate hasProject(final String name) {
        return new Predicate() {
            @Override
            public boolean apply(Object o) {
                return project(name) != null;
            }
        };
    }

    public void newProject() {
        element.findFirst(".newProject").click();
    }
}