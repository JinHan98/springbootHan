package likelion.springboothan.controller;

import likelion.springboothan.domain.Member;
import likelion.springboothan.service.MemberService;
import likelion.springboothan.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller /*  MVC pattern 에서의 Controller 사용자의 요청을 받아 Service가 처리를 해서 나온 결과값을 주로 View에 반환 */
@RequestMapping("members") /*  members로 시작하는 URI는 여기에 mapping 한다는 뜻 Mapping 중에서도 requestMapping인 이유는
 밑에 Method들이 Getmapping과 Postmapping등 하나로 고정되어 있지 않기 때문*/
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl){
        this.memberService=memberServiceImpl;
    }
    /* 위의 생성자는 Bean 주입에 관한 생성자이다. Bean 주입을 하는 방법에는 여러가지가 있지만 위의 방법이 가장 안정성있는 방법이라고 알고 있다.
    다른 class에서 접근하지 않기 떄문에 final, 한번 주입이 되면 변하지 않기 때문에 final 그리고 생성자를 통해서 bean 주입을 할때는 Autowired 어노테이션을 사용한다
     Bean을 사용하는 이유는 원래대로 라면 class를 객체로 받아와서 실행을 하면 초기화 부터 메모리 free까지의 생명주기를 개발자가 관리해야하는데 IoC container가
     관리 해주기 때문에 Bean 주입을 쓴다. 의존성을 주입하면 느슨한 결합이 되어 더욱 장려가 된다. IoC Controller에서는 객체를 Singleton으로 저장하며
     */
    @GetMapping("new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new Member());
        return "members/createMemberForm";
    }
    /*
    * GetMapping이므로 Get method로 members/new URI를 요청할때 처리해주는 Controller이다.
    * Parameter인 Model 객체인 model 변수는 Controller에서 생성된 객체를 담아서 View로 전달해주는 역할을 한다.
    * 그 다음 문장을 보면 Model class의 Method인 addAttribute method를 실행시키는 것을 볼 수 있다. attribute는 memberForm이고
    * 이는 html에 th:object="${memberForm} 문장을 통해 전달된다. 그리고 각각의 attribute는 field로 매핑이된다.
    * 그 후에 templates/members/createMemberForm.html을 반환한다. 즉 view를 교체해주는것이다.
    *
    * */

    @PostMapping("new")
    public String create(Member member){
        memberService.save(member);
        return "redirect:/home";
    }
    /*
    * 위와 URI는 같지만 PostMapping이기 때문에 Post 요청이 들어왔을 때 실행한다.
    * 위에서 종속성을 주입한 MemberService instance에 save method를 실행한다.
    * 바로 위에 getMapping에서 새롭게 member instance를 생성했기 때문에 그 member에 썼던 값들이 Member 객체가 되어 method의 parameter가 된다.
    * 즉 고객이 입력한 정보들이 기존에 설정한 h2 database에 들어가는 것이다.
    * 정확히는 MemberRepository의 구성값으로 저장이되면서 h2에 자동으로 저장이 된다.
    * 마지막으로 redirect는 말 그대로 해당 URL로 re(다시) direct(가리킨다)는 의미이다.
    * 즉 이 요청이 끝나면 특정페이지로 이동해라 정도의 기능을 한다고 이해하는게 좋을것 같다.*/
    @GetMapping("")
    public String findAll(Model model){
        List<Member> memberList=memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "members/memberList";
    }
    /*
    * GetMapping안에 아무것도 없다는 의미는 이 API의 URI가 ~/members라는 의미이다.
    * memberService 객체의 findAll() method는 지금까지 memberRepository에 저장되어있는 모든 member instance를
    * List 형태로 가져온다는 의미이다. 위의 createForm 함수처럼 Model객체를 parameter로 받았는데
    * 이 역시 templates/members/memberList.html의 object memberList에 memberService.findAll()함수로
    * 생성한 memberList의 값을 넣으라는 의미이다. 그 후에 members/memberList.html로 view를 바꾼다.
    *
    * */
}
