package cn.agilean.cucumber;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MockController {

    boolean login = false;

    @RequestMapping(value = "/j_spring_security_check", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String post(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        if(username == password){
            login =true;
            '''
                        {
                        "result":"ok",
                        "code":200
                        }
                        ''';
        }else{
            '''{"result":false,"code":401}''';
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public String list() {
        if(login){
             '''
[
{"user":"alex"},
{"user":"jack"}
]
'''
        }else{
            throw new RuntimeException("401")
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> onException(Exception ex){
        if(ex instanceof RuntimeException){
            if('401' == ex.getMessage()){
                return ResponseEntity.status(401).body('{"result":false,"code":401}');
            }
        }
        return ResponseEntity.status(500);
    }
}
