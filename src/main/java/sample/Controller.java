package sample;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.DAO.SurveyDAO;
import sample.DAO.UserDAO;
import sample.entity.Survey;
import sample.entity.User;

import java.util.List;


@RestController
public class Controller {
    @RequestMapping(value = "/api/user",method = RequestMethod.POST)
    public String addUser(@RequestHeader(value="username") String name) {
     return SurveyController.newUser(name);
    }

    @RequestMapping(value="/api/survey",method = RequestMethod.POST)
    public String index(@RequestHeader(value="user_id")int id, @RequestHeader(value="title")String title, @RequestHeader(value="question")String question){

       return SurveyController.newSurvey(id,title,question);
    }

    @RequestMapping(value="/api/survey/user/{ID}",method=RequestMethod.GET)
    public String userSurveys(@PathVariable("ID")int id){
      return SurveyController.userSurveys(id);
    }

    @RequestMapping(value="/api/survey",method=RequestMethod.GET)
    public String allSurveys(){
        return SurveyController.allSurveys();
    }

    @RequestMapping(value="/api/survey/{ID}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("ID")int id){
        if(SurveyController.deleteById(id)){
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        else{return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);}
    }

    @RequestMapping(value="/api/answer",method=RequestMethod.POST)
    public String newAnswer(@RequestHeader(value="survey_id")int id, @RequestHeader(value="rating")int rating, @RequestHeader(value="user_id")int us_id){
        return SurveyController.newAnswer(id,rating,us_id);
    }

    @RequestMapping(value="/api/answer/{ID}",method=RequestMethod.GET)
    public String getAnswer(@PathVariable("ID")int id){
        return SurveyController.getAnswer(id);
    }

    @RequestMapping(value="/api/answer/{ID}",method=RequestMethod.PUT)
    public String changeRating(@PathVariable("ID")int id, @RequestHeader(value="rating")int rating){
        return SurveyController.changeRating(id,rating);
    }


    @RequestMapping(value="/api/stats/user/{ID}",method=RequestMethod.GET)
    public String userStats(@PathVariable("ID")int id){
        return SurveyController.userStats(id);
    }
    @RequestMapping(value="/api/stats",method=RequestMethod.GET)
    public String userStats(){
        return SurveyController.systemStats();
    }




}




