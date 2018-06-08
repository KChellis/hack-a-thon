import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.staticfiles.StaticFilesFolder;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

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
            model.put("teams", teamDao.getAll());
            return new ModelAndView(model, "teams.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView (model, "addteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/add", (request, response) -> {
            String name = request.queryParams("name");
            String description = request.queryParams("description");
            Team newTeam = new Team (name, description);
            teamDao.add(newTeam);
            response.redirect("/teams");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            Team currentTeam = teamDao.findById(teamId);
            model.put("team", currentTeam);
            List<Member> members = memberDao.findByTeam(teamId);
            model.put("members", members);
            model.put("full", currentTeam.isIsFull());
            return new ModelAndView (model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:teamId/members/add", (request, response) -> {
            int teamId = Integer.parseInt(request.params("teamId"));
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String phone = request.queryParams("phone");
            Member newMember = new Member(name, email, phone, teamId);
            if(memberDao.findByTeam(teamId).size() == 6){
                teamDao.updateIsFull(teamId, true);
            }
            response.redirect("/teams/" + teamId);
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            Team currentTeam = teamDao.findById(teamId);
            model.put("team", currentTeam);
            return new ModelAndView (model, "addteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:teamId/update", (request, response) -> {
            String newName = request.queryParams("name");
            String newDescription = request.queryParams("description");
            int teamId = Integer.parseInt(request.params("teamId"));
            teamDao.update(teamId, newName, newDescription);
            response.redirect("/teams/" +teamId);
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamId/delete", (request, response) -> {
            int teamId = Integer.parseInt(request.params("teamId"));
            for(Member member: memberDao.findByTeam(teamId)){
                memberDao.deleteById(member.getId());
            }
            teamDao.deleteById(teamId);
            response.redirect("/teams");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamId/members/:memberId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            Team currentTeam = teamDao.findById(teamId);
            Member currentMember = memberDao.findById(memberId);
            model.put("team", currentTeam);
            model.put("member", currentMember);
            return new ModelAndView (model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/teamId/members/:memberId/delete", (request, response) -> {
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            memberDao.deleteById(memberId);
            teamDao.updateIsFull(teamId, false);
            response.redirect("/teams/" + teamId);
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamId/members/:memberId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            Team currentTeam = teamDao.findById(teamId);
            Member currentMember = memberDao.findById(memberId);
            model.put("team", currentTeam);
            model.put("member", currentMember);
            return new ModelAndView (model, "addteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:teamId/members/:memberId/update", (request, response) -> {
            int teamId = Integer.parseInt(request.params("teamId"));
            int memberId = Integer.parseInt(request.params("memberId"));
            String newName = request.queryParams("name");
            String newEmail = request.queryParams("email");
            String newPhone = request.queryParams("phone");
            memberDao.update(memberId, newName, newEmail, newPhone, teamId);
            response.redirect("/teams/" + teamId + "/members/" + memberId);
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
