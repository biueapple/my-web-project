package com.airplane.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/delete")
public class UserDeleteController {

    @Autowired
    private UserService userService;

    @GetMapping  
    public String showDeleteForm(Model model) {
        model.addAttribute("userDeleteCommand", new UserDeleteCommand());
        return "user/userDelete";  
    }

    @PostMapping
    public String deleteUser(
            HttpSession session,
            @Valid @ModelAttribute("userDeleteCommand") UserDeleteCommand cmd,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "user/userDelete";
        }

        LoginRequestCommand loginUser = (LoginRequestCommand) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        String id = loginUser.getId();
        User user = userService.search(id);
        int userId = user.getUserId();

        boolean success = userService.deleteUser(userId, cmd.getPassword());

        if (success) {
            session.invalidate();
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "비밀번호 불일치");
            return "redirect:/user/delete";
        }
    }
}