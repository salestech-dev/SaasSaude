package com.sales.saassaude.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {


    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/empresas")
    public String empresas() {
        return "empresas";
    }

    @GetMapping("/funcionarios")
    public String funcionarios() {
        return "funcionarios";
    }

    @GetMapping("/avaliacoes")
    public String avaliacoes() {
        return "avaliacoes";
    }

    @GetMapping("/relatorios")
    public String relatorios() {
        return "relatorios";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }
}