package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;
import play.mvc.Security;
import controllers.Secured;

@Security.Authenticated(Secured.class)
@Entity
public class Project extends Model {
    private static final long serialVersionUID = 1L;

    @Id
    public Long id;
    public String name;
    public String folder;
    @ManyToMany(cascade = CascadeType.REMOVE)
    public List<ZenUser> members = new ArrayList<ZenUser>();

    public Project(String name, String folder, ZenUser owner) {
        this.name = name;
        this.folder = folder;
        this.members.add(owner);
    }

    public static Model.Finder<Long, Project> find = new Model.Finder(Long.class, Project.class);

    public static Project create(String name, String folder, String owner) {
        Project project = new Project(name, folder, ZenUser.find.ref(owner));
        project.save();
        project.saveManyToManyAssociations("members");
        return project;
    }

    public static List<Project> findInvolving(String useremail) {
        return find.where().eq("members.email", useremail).findList();
    }

    public static boolean isMember(Long project, String user) {
        return find.where().eq("members.email", user).eq("id", project).findRowCount() > 0;
    }

    public static String rename(Long projectId, String newName) {
        Project project = find.ref(projectId);
        project.name = newName;
        project.update();
        return newName;
    }

}