package pl.stud.pw.EQSERVICE.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.stud.pw.EQSERVICE.Entity.Employee;
import pl.stud.pw.EQSERVICE.Entity.WebUser;
import pl.stud.pw.EQSERVICE.Service.OfferService;
import pl.stud.pw.EQSERVICE.Service.WebUserService;

import javax.validation.Valid;

@Controller
public class WebUserController {

    private final WebUserService webUserService;
    private final OfferService offerService;

    public WebUserController(WebUserService webUserService, OfferService offerService) {
        this.webUserService = webUserService;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String redirectToFrontPage() {
        if (isAuthenticated()) {
            return "redirect:/logged";
        }
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String showFrontPage() {
        return "front-page";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        if (isAuthenticated()) {
            return "redirect:/logged";
        }
        return "login-form";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        if (!isAuthenticated()) {
            model.addAttribute("theUser", new WebUser());
            return "register-form";
        }
        return "redirect:/logged";
    }

    @PostMapping("/save")
    public String processRegistration(@Valid @ModelAttribute("theUser") WebUser theUser,
                                      BindingResult bindingResult) {
        if (isAuthenticated()) {
            return "redirect:/welcome";
        }
        if (!bindingResult.hasFieldErrors()) {
            if (!webUserService.isUsernameOrEmailDuplicated(theUser)) {
                webUserService.saveWebUser(theUser);
                return "front-page";
            }
            theUser.setNonBlankPassAfterError();
            return "register-form";
        }
        theUser.clearPassBeforePopulatingForm();
        return "register-form";
    }

    @PostMapping("/logged/updateUserData")
    public String processUpdatingUserData(@Valid @ModelAttribute("theUser") WebUser theUser,
                                          BindingResult bindingResult) {
        if (isAuthenticated()) {
            if (!bindingResult.hasFieldErrors()) {
                if (webUserService.isUpdateForFieldsAvailable(theUser)) {
                    webUserService.updateWebUserData(theUser);
                    return "redirect:/logged/update?userId=" + theUser.getId();
                }
                theUser.fillFieldsWithLoggedUserDetails(webUserService.getLoggedUser());
                return "update-form";
            }
            theUser.clearPassBeforePopulatingForm();
            return "update-form";
        }
        return "front-page";
    }

    @GetMapping("/logged")
    public String showSignedUpPage(Model theModel) {
        WebUser theUser = webUserService.getLoggedUser();
        theModel.addAttribute("loggedUser", theUser);
        return "logged-form";
    }

    @GetMapping("/logged/update")
    public String showUpdateForm(@RequestParam("userId") Long theId, Model theModel) {
        WebUser webUser = webUserService.findUserAndClearPassForm(theId);
        if (webUser != null) {
            theModel.addAttribute("theUser", webUser);
            return "update-form";
        }
        return "redirect:/logged";
    }

    @GetMapping("/logged/offers")
    public String showOffers(Model theModel) {
        theModel.addAttribute("employees", webUserService.getEmployees());
        theModel.addAttribute("offers", offerService.findAllOffers());
        return "offers-form";
    }

    @GetMapping("/logged/cart")
    public String showShoppingCart(Model theModel) {
        WebUser webUser = webUserService.getLoggedUser();
        theModel.addAttribute("reservations", offerService.findUsersReservations(webUser));
        return "shoppingCart";
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/logged/tasks")
    public String showEmployeeTasks(Model theModel) {
        Employee employee = webUserService.getLoggedUser().getEmployee();
        theModel.addAttribute("reservationInfo", offerService.findEmployeeReservedTasks(employee));
        return "employee-tasks";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/logged/systemUsers")
    public String showSystemUsers(Model theModel) {
        theModel.addAttribute("systemUsers", webUserService.getWebSystemUsers());
        return "admin-list-users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/logged/removeUser")
    public String removeUser(@RequestParam(name = "userToDelete") Long webUserId) {
        WebUser webUser = webUserService.getUserById(webUserId);
        webUserService.deleteUser(webUser);
        return "redirect:/logged/systemUsers";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

}
