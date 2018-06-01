import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Member;
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

        get("/event", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView (model, "event.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("teams", Team.getAll());
            return new ModelAndView(model, "teams.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView (model, "addteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Team newTeam = new Team (name, description);
            model.put("add", "add");
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(id);
            model.put("team", currentTeam);
            return new ModelAndView (model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            String member = request.queryParams("member");
            Team currentTeam = Team.findById(id);
            currentTeam.addMember(member);
            if(currentTeam.getMembers().size() == 6){
                currentTeam.setFull(true);
            }
            model.put("team", currentTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(id);
            model.put("team", currentTeam);
            return new ModelAndView (model, "addteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            String newDescription = request.queryParams("description");
            int id = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(id);
            currentTeam.updateTeamDescription(newDescription);
            currentTeam.updateTeamName(newName);
            model.put("team", currentTeam);
            return new ModelAndView (model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(id);
            currentTeam.deleteTeam();
            Team.resetIds();
            return new ModelAndView (model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/:memberId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            int memberId = Integer.parseInt(request.params("memberId"));
            Team currentTeam = Team.findById(teamId);
            Member currentMember = Member.findById(memberId);
            int index = currentTeam.getMembers().indexOf(currentMember);
            currentTeam.getMembers().remove(index);
            currentMember.deleteMember();
            Member.resetIds();
            model.put("team", currentTeam);
            model.put("delete", "delete");
            return new ModelAndView (model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
