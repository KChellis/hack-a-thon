import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Team;
import spark.ModelAndView;
import spark.staticfiles.StaticFilesFolder;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView (model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("teams", Team.getAll());
            return new ModelAndView(model, "teams.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
