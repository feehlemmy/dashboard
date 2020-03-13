package com.felipeleme.Dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felipeleme.Dashboard.model.entity.Atividade;
import com.felipeleme.Dashboard.model.entity.Carreira;
import com.felipeleme.Dashboard.model.entity.Empreendimento;
import com.felipeleme.Dashboard.model.entity.Evento;
import com.felipeleme.Dashboard.model.entity.Frase;
import com.felipeleme.Dashboard.model.entity.Ideia;
import com.felipeleme.Dashboard.model.entity.Leitura;
import com.felipeleme.Dashboard.model.entity.Meme;
import com.felipeleme.Dashboard.model.entity.Tarefa;
import com.felipeleme.Dashboard.model.entity.Usuario;
import com.felipeleme.Dashboard.model.service.AtividadeService;
import com.felipeleme.Dashboard.model.service.CarreiraService;
import com.felipeleme.Dashboard.model.service.EmpreendimentoService;
import com.felipeleme.Dashboard.model.service.EventoService;
import com.felipeleme.Dashboard.model.service.FrasesService;
import com.felipeleme.Dashboard.model.service.IdeiaService;
import com.felipeleme.Dashboard.model.service.LeituraService;
import com.felipeleme.Dashboard.model.service.MemeService;
import com.felipeleme.Dashboard.model.service.TarefaService;
import com.felipeleme.Dashboard.model.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    UserService service = new UserService();
    @Autowired
    EmpreendimentoService empService = new EmpreendimentoService();
    @Autowired
    TarefaService tarefaService = new TarefaService();
    @Autowired
    IdeiaService ideiaService = new IdeiaService();
    @Autowired
    CarreiraService carService;
    @Autowired
    AtividadeService atividadeService;
    @Autowired
    LeituraService leituraService;
    @Autowired
    FrasesService frasesService;
    @Autowired
    MemeService memeService;
    @Autowired
    EventoService eventoService;

    @RequestMapping("/getPerfil")
    public List<Usuario> getPerfil(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	List<Usuario> users = new ArrayList();
	users.add(sessaoAtual);
	return users;
    }

    @RequestMapping("/getUsuarios")
    public List<Usuario> getUsuarios(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	List<Usuario> users = new ArrayList();
	users = service.findAllUsers();
	return users;
    }

    @RequestMapping("/getEmpreendimento")
    public List<Empreendimento> getEmpreendimentos(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	List<Empreendimento> empList = empService.getEmpreendimentos(sessaoAtual.getId());
	return empList;
    }

    @RequestMapping("/getTarefas")
    public List<Tarefa> getTarefa(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	return tarefaService.findAllTarefas(sessaoAtual.getId());
    }

    @RequestMapping("/getIdeias")
    public List<Ideia> getIdeias(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	return ideiaService.findAllIdeias(sessaoAtual.getId());
    }

    @RequestMapping("/updateTarefa")
    public void updateTarefa(Long id, Boolean status, HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	if (!sessaoAtual.isVisitante()) {
	    tarefaService.updateTarefa(id, status);
	}
    }

    @RequestMapping("/updateIdeia")
    public void updateIdeia(Long id, Boolean status, HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	if (!sessaoAtual.isVisitante()) {
	    ideiaService.updateIdeia(id, status);
	}
    }

    @RequestMapping("/getCarreiras")
    public List<Carreira> getCarreiras(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	List<Carreira> empList = carService.getCarreiras(sessaoAtual.getId());
	return empList;
    }

    @RequestMapping("/getAtividades")
    public List<Atividade> getAtividades(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	return atividadeService.findAllAtividades(sessaoAtual.getId());
    }

    @RequestMapping("/getLeituras")
    public List<Leitura> getLeituras(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	return leituraService.findAllLeituras(sessaoAtual.getId());
    }

    @RequestMapping("/getFrases")
    public List<Frase> getFrases(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	return frasesService.findAllFrases();
    }

    @RequestMapping("/getMemes")
    public List<Meme> getMeme(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	return memeService.findAllMemes(sessaoAtual.getId());
    }

    @RequestMapping("/getEventos")
    public List<Evento> getEvents(HttpSession session) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	return eventoService.findAllEventos(sessaoAtual.getId());
    }

    @DeleteMapping(value = "/deleteEvento/{id}")
    public String deleteEvent(@PathVariable String id, HttpSession session) {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	if (!sessaoAtual.isVisitante()) {
	    eventoService.delete(Long.parseLong(id));
	}
	return "OK!";
    }

    @RequestMapping("/saveEvent")
    public void saveEvent(HttpSession session, String titulo, String data, String cor) throws Exception {
	Usuario sessaoAtual = (Usuario) session.getAttribute("usuarioLogado");
	if (!sessaoAtual.isVisitante()) {

	    Evento evento = new Evento();
	    evento.setCor(cor);
	    evento.setData(data);
	    evento.setTitulo(titulo);
	    System.out.println("HERE _______________" + evento);
	    eventoService.salvarEventos(sessaoAtual.getId(), evento);
	}
    }

    
}
