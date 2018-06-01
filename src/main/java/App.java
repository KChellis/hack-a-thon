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


    }
}
