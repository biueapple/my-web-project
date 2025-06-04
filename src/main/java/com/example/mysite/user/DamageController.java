package com.example.mysite.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.airplane.user.LoginRequestCommand;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/damage")
public class DamageController {

    @Autowired
    UploadService uploadService;

    @Autowired
    Upload upload;

    @Autowired
    RefundUserService refundUserService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String damageResult() {
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String damageClaim(@RequestParam(value = "ids", required = false) List<Integer> ids,
                              HttpSession httpSession, Model model) {

        if (ids == null || ids.isEmpty()) {
            model.addAttribute("message", "선택된 예약이 없습니다.");
            return "redirect:/"; // 또는 에러페이지
        }

        httpSession.setAttribute("ticketId", ids);
        model.addAttribute("damageRequest", new DamageDto());

        return "Refunduser/damageUpload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String damageSubmit(@RequestParam("damagePhotos") List<MultipartFile> damagePhotos,
                                @ModelAttribute DamageDto damageDto,
                                HttpSession httpSession,
                                Model model) {

        LoginRequestCommand lrc = (LoginRequestCommand) httpSession.getAttribute("loginUser");
        if (lrc == null) {
            return "redirect:/login";
        }

        List<Integer> ids = (List<Integer>) httpSession.getAttribute("ticketId");
        List<String> savePath = new ArrayList<>();
        String realPath = null;
        for (int id : ids) {
            boolean updated = true;//refundUserService.updateStatus(id);
            if (updated) {
                for (MultipartFile f : damagePhotos) {
                    if (!f.isEmpty()) {
                         realPath = upload.fileUpload("D:/my-web-project/upload/", f);
                        uploadService.service(ids, realPath);
                        savePath.add("/upload/" + f.getName());
                    }
                }
            } else {
                model.addAttribute("message", "예약 상태 업데이트 실패 (id: " + id + ")");
                return "Refunduser/damageUpload";
            }
        }

        if (savePath.isEmpty()) {
            model.addAttribute("message", "파일을 선택해주세요.");
            return "Refunduser/damageUpload";
        }

        model.addAttribute("message", "보상 신청이 완료되었습니다.");
        model.addAttribute("savePath", "/upload/" + realPath);

        return "Refunduser/showPicture";
    }
}
