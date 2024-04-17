package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@RequestMapping("/first/*")
public class FirstController {

    /* 핸들러 메소드에 파라미터로 특정 몇 가지 타입을 선언하게 되면 핸들러 메소드 호출 시 인자로 값을 전달해준다. */

    /* 컨트롤러 핸들러 메소드의 반환 값을 void로 설정하면 요청 주소가 view의 이름이 된다.
     * => /first/regist 요청이 들어오면 /first/regist 뷰를 응답한다.
     */
    @GetMapping("regist")
    public void regist(){}

    /* 1. WebRequest로 요청 파라미터 전달 받기
    *     파라미터 선언부에 WebRequest 타입을 선언하면 해당 메소드 호출 시 인자로 값을 전달해 준다.
    *     핸들러 메소드 매개변수로 HttpServletRequest, HttpServletResponse도 사용 가능하다.
    *     상위 타입인 ServletRequest, ServletResponse 도 사용 가능하다.
    *     WebRequest는 HttpServletRequest의 요청 정보를 거의 대부분 그대로 가지고 있는 API로 Servlet에 종속적이지 않다.
    *     HttpServletRequest는 Servlet API의 일부이고,
    *     WebRequest는 Spring의 일부이기 떄문에 Spring 기반의 프로젝트에서 더 자주 사용된다.
    */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

        /* WebRequest 객체의 getParameter 등의 메소드를 통해 클라이언트로부터 전달 된 파라미터를 가져올 수 있다. */
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        /* 클라이언트로부터 전달 받은 값을 통해 응답할 화면의 메세지를 생성한다. */
        String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에" +price+ "원으로 등록하였습니다.";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @GetMapping("modify")
    public void modify(){}

    /* 2. @RequestParam 으로 요청 파라미터 전달 받기
    *     요청 파라미터를 매핑하여 호출 시 값을 넣어주는 어노테이션으로 매개 변수 앞에 작성한다.
    *     form의 name 속성값과 매개변수의 이름이 다른 경우 @RequestParam("이름")을 설정하면 된다.
    *     또한 어노테이션은 생략 가능하지만 명시적으로 작성하는 것이 의미 파악에 쉽다.
    *
    *     전달하는 form의 name 속성이 일치하는 것이 없는 경우 400에러가 발생하는데 이는 required 속성의 기본 값이 true이기 때문이다.
    *     required 속성을 false로 하게 되면 해당 name 값이 존재하지 않아도 null로 처리하며 에러가 발생하지 않는다.
    *
    *    값이 넘어오지 않게 되면 "" 빈 문자열이 넘어오게 되는데, 이 때 parsing 관련 에러가 발생할 수 있다.
    *    값이 넘어오지 않는 경우 defaultValue를 이용하게 되면 기본값으로 사용할 수 있다.
    */
    @PostMapping("modify")
    public String modifyMenuName(Model model,
                                 @RequestParam String modifyName,
                                 @RequestParam int modifyPrice){
        String message = modifyName + " 메뉴의 가격을 " +modifyPrice+ " 원으로 가격 변경하였습니다.";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    /* 파라미터가 여러 개인 경우 맵으로 한 번에 처리할 수도 있다.
    *  이 때 맵의 키는 form의 name 속성값이 된다.
    */
    @PostMapping("modifyAll")
    public String modifyAll(Model model, @RequestParam Map<String, String> parameters) {

        String modifyMenu = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = "메뉴의 이름을 " +modifyMenu+ " (으)로, 가격을 " +modifyPrice+ " 원으로 변경하였습니다.";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }
}
