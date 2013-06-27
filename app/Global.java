import models.ZenUser;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        if (ZenUser.find.findRowCount() == 0) {
            // Ebean.save((List) Yaml.load("initial-data.yml"));
        }
    }
}