package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class ZenUser extends Model {

    @Id
    public String email;
    public String name;
    public String password;

    public ZenUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static ZenUser authenticate(String email, String password) {
        return find.where().eq("email", email).eq("password", password).findUnique();
    }

    public static Finder<String, ZenUser> find = new Finder<String, ZenUser>(String.class, ZenUser.class);
}