package com.example.nullam.controllers;

import com.example.nullam.dto.CompanyDto;
import com.example.nullam.dto.MemberDto;
import com.example.nullam.models.Company;
import com.example.nullam.models.Event;
import com.example.nullam.models.Member;
import com.example.nullam.repository.CompanyRepository;
import com.example.nullam.repository.EventRepository;
import com.example.nullam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class  EventController {
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    @GetMapping("/")
    public String events(Model model) {
        model.addAttribute("future_events", eventRepository.findAllByTimeAfter(LocalDateTime.now()));
        model.addAttribute("happened_events", eventRepository.findAllByTimeBefore(LocalDateTime.now()));
        return "homepage";
    }

    @GetMapping("/event/{id}")
    public String eventInfo(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id).orElse(new Event());
        model.addAttribute("event", event);
        return "event-info";
    }

    @GetMapping("/event/create")
    public String createEvent() {
        return "create-event";
    }

    @PostMapping("/event/create")
    public String createEvent(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "redirect:/";
    }

    @GetMapping("/event/remove/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/member/{id}/{eventId}")
    public String memberInfo(@PathVariable Long id, @PathVariable Long eventId, Model model) {
        model.addAttribute("members", memberRepository.getReferenceById(id));
        model.addAttribute("event", eventRepository.getReferenceById(eventId));
        return "memberinfo";
    }

    @GetMapping("/member/create")
    public String createMember() {
        return "event-info";
    }

    @PostMapping("/member/create/{id}")
    public String createMember(@ModelAttribute MemberDto member, @PathVariable Long id, Model model) {
        Member member1 = Member.builder()
                .name(member.getName())
                .secondname(member.getSecondname())
                .regcode(member.getRegcode())
                .payment(member.getPayment())
                .info(member.getInfo()).build();
        Event event = eventRepository.findById(id).orElse(new Event());
        event.getMembers().add(member1);
        eventRepository.save(event);
        model.addAttribute("event", event);
        return "event-info";
    }

    @PostMapping("/event/change-member/{eventId}/{memberId}")
    public String changeMemberData(@ModelAttribute MemberDto member, @PathVariable Long memberId, @PathVariable Long eventId) {
        Member member1 = memberRepository.getReferenceById(memberId);
        member1.setInfo(member.getInfo());
        member1.setName(member.getName());
        member1.setRegcode(member.getRegcode());
        member1.setSecondname(member.getSecondname());
        member1.setPayment(member.getPayment());
        memberRepository.save(member1);
        return "redirect:/event/" + eventId;
    }

    @GetMapping("/member/remove/{id}/{eventId}")
    public String deleteMember(@PathVariable Long id, @PathVariable Long eventId) {
        Event event = eventRepository.getReferenceById(eventId);
        event.getMembers().removeIf(m -> m.getId().equals(id));
        eventRepository.save(event);
        return "redirect:/";
    }

    @GetMapping("/company/{id}/{eventId}")
    public String companyInfo(@PathVariable Long id, @PathVariable Long eventId, Model model) {
        model.addAttribute("company", companyRepository.getReferenceById(id));
        model.addAttribute("event", eventRepository.getReferenceById(eventId));
        return "companyinfo";
    }

    @GetMapping("/company/create")
    public String companyMember() {
        return "event-info";
    }

    @PostMapping("/company/create/{id}")
    public String createCompany(@ModelAttribute CompanyDto company, @PathVariable Long id, Model model) {
        Company company1 = Company.builder()
                .name(company.getName())
                .regnumber(company.getRegnumber())
                .ammount(company.getAmmount())
                .payment(company.getPayment())
                .info(company.getInfo()).build();
        Event event = eventRepository.findById(id).orElse(new Event());
        event.getCompanies().add(company1);
        eventRepository.save(event);
        model.addAttribute("event", event);
        return "event-info";
    }

    @PostMapping("/event/change-company/{eventId}/{companyId}")
    public String changeCompanyData(@ModelAttribute CompanyDto company, @PathVariable Long companyId, @PathVariable Long eventId) {
        Company company1 = companyRepository.getReferenceById(companyId);
        company1.setInfo(company.getInfo());
        company1.setName(company.getName());
        company1.setRegnumber(company.getRegnumber());
        company1.setAmmount(String.valueOf(company.getAmmount()));
        company1.setPayment(company.getPayment());
        companyRepository.save(company1);
        return "redirect:/event/" + eventId;
    }

    @GetMapping("/company/remove/{id}/{eventId}")
    public String deleteCompany(@PathVariable Long id, @PathVariable Long eventId) {
        Event event = eventRepository.getReferenceById(eventId);
        event.getCompanies().removeIf(c -> c.getId().equals(id));
        eventRepository.save(event);
        return "redirect:/";
    }
}