package net.soul.sp.controller;

import net.soul.sp.domain.Member;
import net.soul.sp.service.MemberServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sould on 2016-05-29.
 */
@Controller
public class MemberController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private final String TAG = getClass().getSimpleName();

    @Autowired
    MemberServiceImpl memberService;

    @RequestMapping(value = "/member/new", method = RequestMethod.POST)
    public String create(Member member){
        DateTime dateTime = new DateTime();
        member.setRegDate(dateTime);
        member.setUpdDate(dateTime);
        memberService.join(member);
        return "redirect:/login";
    }


    @RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public String login(Member member, Model model){
        boolean loginResult = memberService.login(member);
        if(loginResult){
            LOGGER.debug(TAG+" / 로그인 성공");
            return "redirect:/sample";
        }else{
            LOGGER.debug(TAG+" / 로그인 실패");
            return "redirect:/fail";
        }
    }


//
//    @RequestMapping(value = "create", method = RequestMethod.POST)
//    String create(@Validated JoinForm form, BindingResult result, Model model){
//        if(result.hasErrors()){
////            return list(model);
//            LOGGER.error(TAG, "join create Error!");
//        }
//        Member member = new Member();
//        DateTime dateTime = new DateTime();
//        member.setRegDate(dateTime);
//        member.setUpdDate(dateTime);
//        BeanUtils.copyProperties(form, member);
//        memberService.create(member);
//        return "redirect:/join";
//    }
}
