package com.felipeleme.Dashboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felipeleme.Dashboard.model.entity.Atividade;
import com.felipeleme.Dashboard.model.entity.Carreira;
import com.felipeleme.Dashboard.model.entity.Empreendimento;
import com.felipeleme.Dashboard.model.entity.Ideia;
import com.felipeleme.Dashboard.model.entity.Leitura;
import com.felipeleme.Dashboard.model.entity.Meme;
import com.felipeleme.Dashboard.model.entity.Tarefa;
import com.felipeleme.Dashboard.model.entity.Usuario;
import com.felipeleme.Dashboard.model.service.AtividadeService;
import com.felipeleme.Dashboard.model.service.CarreiraService;
import com.felipeleme.Dashboard.model.service.EmailService;
import com.felipeleme.Dashboard.model.service.EmpreendimentoService;
import com.felipeleme.Dashboard.model.service.IdeiaService;
import com.felipeleme.Dashboard.model.service.LeituraService;
import com.felipeleme.Dashboard.model.service.MemeService;
import com.felipeleme.Dashboard.model.service.TarefaService;
import com.felipeleme.Dashboard.model.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private EmailService emailService;
	@Autowired
	private EmpreendimentoService empService;
	@Autowired
	private TarefaService tarefaService;
	@Autowired
	private IdeiaService ideiaService;
	@Autowired
	private CarreiraService carService;
	@Autowired
	private AtividadeService atividadeService;
	@Autowired
	private LeituraService leituraService;
	@Autowired
	private MemeService memeService;

	@GetMapping("/")
	public String getIndex(ModelMap model, Usuario usuario) {

		return "index";

	}

	@PostMapping("/logar")
    public String entrar(HttpSession session, RedirectAttributes attr, Usuario usuario) throws Exception {
	String username = usuario.getUsername();
	String senha = usuario.getSenha();
	String retorno = "index";
	Usuario sessaoatual = new Usuario();
	sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
	if (username.equals(null)) {
	    attr.addFlashAttribute("fail", "username ou senha incorretos. Tente novamente.");
	    return "index";
	}
	try {
		System.out.println(" *************** "+ sessaoatual +" ********************");

	    if (sessaoatual != null) {
		session.removeAttribute("usuarioLogado");
	    }
	    if (username.equals("admin")) {
			System.out.println(" *************** ADMIN ********************");

		sessaoatual = service.verifyLogin(username, senha);
		session.setAttribute("usuarioLogado", sessaoatual);
		return "administrativo/listarUsuarios";
		}
		System.out.println(" *************** Visitante? ********************");
	    if (!username.equals("admin")) {
		if (sessaoatual.isVisitante() && sessaoatual.isVisitante() != null) {
			System.out.println(" *************** "+ sessaoatual.isVisitante()+ "********************");

			sessaoatual = service.verifyLogin(username, senha);
		    Usuario pai = service.getUserByUsername(sessaoatual.getUsuarioCriador());
		    pai.setVisitante(true);
		    session.setAttribute("usuarioLogado", pai);
		    return  "dashboardIndex";
		}
		if (!sessaoatual.isVisitante() && sessaoatual.isVisitante() != null) {
		    System.out.println(" *************** Não sou visitante ********************");
		 	sessaoatual = service.verifyLogin(username, senha);
			session.setAttribute("usuarioLogado", sessaoatual);
			System.out.println("Eu sou" + sessaoatual.getUsername());

		    return "dashboardIndex";
	}
}
		

	} catch (Exception e) {
	    attr.addFlashAttribute("fail", "username ou senha incorretos. Tente novamente.");
	    return "index";
	}
	return "/";
    }

	@GetMapping("/administrativo")
	public String getPainelAdministrativo(HttpSession session, ModelMap model, Usuario usuario,
			RedirectAttributes attr) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
			if (sessaoatual != null) {
				model.addAttribute("imagem", usuario.getFoto());
				return "administrativo";
			} else {
				attr.addFlashAttribute("fail", "username ou senha incorretos. Tente novamente.");
				return "/";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@GetMapping("/cadastro")
	public String getCadastroUsuario(HttpSession session, ModelMap model, Usuario usuario) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
			if (sessaoatual.getUsername().equals("admin")) {
				return "administrativo/cadastro";
			} else {
				return "error";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/cadastroUsuario")
	public String cadastro(HttpSession session, RedirectAttributes attr, Usuario usuario) throws Exception {

		Usuario sessaoatual = new Usuario();
		sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {
			String senhaAleatoria;
			senhaAleatoria = service.gerarSenhaAleatoria();
			usuario.setSenha(senhaAleatoria);
			usuario.setVisitante(false);
			usuario.setUsuarioCriador(sessaoatual.getUsername());
			try {
				service.cadastrar(usuario);
				emailService.sendEmail(usuario);
			} catch (Exception e) {
				return "error";
			}
		}
		return "administrativo/cadastro";
	}

	@GetMapping("/listarUsuarios")
	public String listUsuarios(HttpSession session, ModelMap model, Usuario usuario) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");

			return "administrativo/listarUsuarios";
		} catch (Exception e) {
			return "error";
		}
	}

	@GetMapping("/editarPerfilAdministrador")
	public String editPerfilAdmin(HttpSession session, ModelMap model, Usuario usuario) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
			if (sessaoatual.getUsername() != null) {
				return "administrativo/editarPerfilAdministrador";
			} else {
				return "error";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@GetMapping("/trocarSenhaAdministrador")
	public String changeSenhaAdministrador(HttpSession session, ModelMap model, Usuario usuario) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");

			return "trocarSenhaAdministrador";
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/editarAdministrador")
	public String editar(@RequestParam String nome, @RequestParam String email,
			@RequestParam MultipartFile uploadImagem, HttpSession session, RedirectAttributes attr, ModelMap model) {
		Usuario sessaoatual = new Usuario();
		Usuario usuario = new Usuario();

		sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			usuario.setEmail(email);
			usuario.setNome(nome);
			usuario.setId(sessaoatual.getId());
			try {
				String path = service.saveImagem(uploadImagem, usuario.getId());
				usuario.setFoto(path);
				service.editarAdministrador(usuario);
				attr.addFlashAttribute("success", "Perfil editado!");
				session.setAttribute("usuarioLogado", usuario);
				model.addAttribute("imagem", usuario.getFoto());
				if (sessaoatual.getUsername() == "admin") {
					return "administrativo/editarPerfilAdministrador";
				} else {
					return "redirect:/dashboardIndex";
				}
			} catch (Exception e) {
				attr.addFlashAttribute("fail", "Não foi possível editar seu perfil. Tente novamente.");
				return "administrativo/editarPerfilAdministrador";
			}
		}
		return "editarAdministrador";
	}

	@GetMapping("/dashboardIndex")
	public String getdashboardIndex(HttpSession session, ModelMap model, Usuario usuario, RedirectAttributes attr,
			Tarefa tarefa) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
			if (sessaoatual != null) {
				List<Empreendimento> empList = empService.getEmpreendimentos(sessaoatual.getId());
				model.addAttribute("imagem", usuario.getFoto());
				model.addAttribute("tarefa", new Tarefa());
				model.addAttribute("empreendimento", new Empreendimento());
				model.addAttribute("empreendimentoList", empList);

				return "dashboardIndex";
			} else {
				attr.addFlashAttribute("fail", "username ou senha incorretos. Tente novamente.");
				return "/";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/salvarEmpreendimento")
	public String salvarEmpreendimento(String nome, HttpSession session, RedirectAttributes attr) throws Exception {
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			Empreendimento empreendimento = new Empreendimento();
			empreendimento.setNome(nome);
			empreendimento.setUsuarioId(sessaoatual.getId());
			empService.salvarEmpreendimento(empreendimento);
		}
		return "redirect:/dashboardIndex";
	}

	@PostMapping("/salvarTarefa")
	public String salvarTarefa(String descricao, String nomeTarefa, String idEmpreendimento, Boolean concluida,
			HttpSession session, RedirectAttributes attr) throws Exception {
		Tarefa tarefa = new Tarefa();
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			tarefa.setEmpreendimentoId(empService.getByNameAndUserId(idEmpreendimento, sessaoatual.getId()));
			tarefa.setNome(nomeTarefa);
			tarefa.setDescricao(descricao);
			tarefa.setUsuarioId(sessaoatual.getId());
			if (concluida != null) {
				tarefa.setConcluida(concluida);
			}
			tarefaService.salvarTarefa(tarefa);
		}
		return "redirect:/dashboardIndex";
	}

	@PostMapping("/salvarIdeia")
	public String salvarIdeia(String descricao, String nomeIdeia, String idEmpreendimento, Boolean utilizada,
			HttpSession session, RedirectAttributes attr) throws Exception {
		Ideia ideia = new Ideia();
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			ideia.setEmpreendimentoId(empService.getByNameAndUserId(idEmpreendimento, sessaoatual.getId()));
			ideia.setNome(nomeIdeia);
			ideia.setDescricao(descricao);
			ideia.setUsuarioId(sessaoatual.getId());
			if (utilizada != null) {
				ideia.setUtilizada(utilizada);
			}
			ideiaService.salvarIdeia(ideia);
		}
		return "redirect:/dashboardIndex";
	}

	@GetMapping("/carreira")
	public String getCarreira(HttpSession session, ModelMap model, Usuario usuario, RedirectAttributes attr,
			Tarefa tarefa) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
			if (sessaoatual != null) {
				List<Empreendimento> empList = empService.getEmpreendimentos(sessaoatual.getId());
				model.addAttribute("imagem", usuario.getFoto());
				return "Carreira";
			} else {
				attr.addFlashAttribute("fail", "username ou senha incorretos. Tente novamente.");
				return "/";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/salvarCarreira")
	public String salvarCarreira(String nome, HttpSession session, RedirectAttributes attr) throws Exception {
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			Carreira carreira = new Carreira();
			carreira.setNome(nome);
			carreira.setUsuarioId(sessaoatual.getId());
			carService.salvarCarreira(carreira);
		}
		return "redirect:/carreira";
	}

	@PostMapping("/salvarAtividade")
	public String salvarAtividade(String nomeAtividade, String cargaHoraria, String cargaHorariaCumprida,
			String idCarreira, HttpSession session, RedirectAttributes attr) throws Exception {
		Atividade atividade = new Atividade();
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			atividade.setCarreiraId(carService.getByNameAndUserId(idCarreira, sessaoatual.getId()));
			atividade.setNome(nomeAtividade);
			atividade.setCargaHoraria(Double.parseDouble(cargaHoraria));
			atividade.setCargaHorariaCumprida(Double.parseDouble(cargaHorariaCumprida));
			atividade.setUsuarioId(sessaoatual.getId());
			atividadeService.salvarAtividade(atividade);
		}
		return "redirect:/carreira";
	}

	@PostMapping("/salvarLeitura")
	public String salvarLeitura(String nomeLeitura, String idCarreira, String totalPaginas, String paginasLidas,
			HttpSession session, RedirectAttributes attr) throws Exception {
		Leitura leitura = new Leitura();
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {
			leitura.setCarreiraId(carService.getByNameAndUserId(idCarreira, sessaoatual.getId()));
			leitura.setNome(nomeLeitura);
			leitura.setUsuarioId(sessaoatual.getId());
			leitura.setTotalPaginas(Double.parseDouble(totalPaginas));
			leitura.setPaginasLidas(Double.parseDouble(paginasLidas));

			leituraService.salvarLeitura(leitura);
		}
		return "redirect:/carreira";
	}

	@PostMapping("/editarAtividade")
	public String editarAtividade(String nomeAtividade, String editId, String cargaHoraria, String cargaHorariaCumprida,
			String idCarreira, HttpSession session, RedirectAttributes attr) throws Exception {
		Atividade atividade = new Atividade();
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			atividade.setNome(nomeAtividade);
			atividade.setId(Long.parseLong(editId));
			atividade.setCargaHoraria(Double.parseDouble(cargaHoraria));
			atividade.setCargaHorariaCumprida(Double.parseDouble(cargaHorariaCumprida));
			atividade.setUsuarioId(sessaoatual.getId());

			atividadeService.editarAtividade(atividade);
		}
		return "redirect:/carreira";
	}

	@PostMapping("/editarLeitura")
	public String editarLeitura(String editNomeLeitura, String editLeituraId, String editTotalPaginas,
			String editPaginasLidas, HttpSession session, RedirectAttributes attr) throws Exception {
		Leitura leitura = new Leitura();
		Usuario sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {

			leitura.setNome(editNomeLeitura);
			leitura.setUsuarioId(sessaoatual.getId());
			leitura.setId(Long.parseLong(editLeituraId));
			leitura.setTotalPaginas(Double.parseDouble(editTotalPaginas));
			leitura.setPaginasLidas(Double.parseDouble(editPaginasLidas));

			leituraService.editarLeitura(leitura);
		}
		return "redirect:/carreira";
	}

	@GetMapping("/espiral")
	public String getEspiral(HttpSession session, ModelMap model, Usuario usuario, RedirectAttributes attr,
			Tarefa tarefa) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
			if (sessaoatual != null) {
				// List<Empreendimento> empList =
				// empService.getEmpreendimentos(sessaoatual.getId());
				model.addAttribute("imagem", usuario.getFoto());
				return "espiral";
			} else {
				attr.addFlashAttribute("fail", "username ou senha incorretos. Tente novamente.");
				return "/";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@GetMapping("/calendario")
	public String getCalendario(HttpSession session, ModelMap model, Usuario usuario, RedirectAttributes attr,
			Tarefa tarefa) {
		Usuario sessaoatual = new Usuario();
		try {
			sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
			if (sessaoatual != null) {
				// List<Empreendimento> empList =
				// empService.getEmpreendimentos(sessaoatual.getId());
				model.addAttribute("imagem", usuario.getFoto());
				return "calendario";
			} else {
				attr.addFlashAttribute("fail", "username ou senha incorretos. Tente novamente.");
				return "/";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/importarTeste")
	public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile, HttpSession session,
			RedirectAttributes attr, ModelMap model) throws IOException {
		Usuario sessao = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessao.isVisitante()) {

			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(1);
			memeService.deleteByUser(sessao.getId());

			try {
				for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {
					Meme meme = new Meme();
					XSSFRow row = worksheet.getRow(i);
					meme.setNome(row.getCell(0).toString());
					meme.setResultado(Double.toString(row.getCell(1).getNumericCellValue()));
					meme.setUsuarioId(sessao.getId());
					if (row.getCell(0).toString().equals("VERMELHO")) {
						meme.setCor("#FD0000");
					} else if (row.getCell(0).toString().equals("ROXO")) {
						meme.setCor("#8500FD");
					} else if (row.getCell(0).toString().equals("AZUL")) {
						meme.setCor("#0004FD");
					} else if (row.getCell(0).toString().equals("VERDE")) {
						meme.setCor("#2EFF00");
					} else if (row.getCell(0).toString().equals("AMARELO")) {
						meme.setCor("#FDFF00");
					} else if (row.getCell(0).toString().equals("TURQUESA")) {
						meme.setCor("#00FFBA");
					} else if (row.getCell(0).toString().equals("LARANJA")) {
						meme.setCor("#FF7100");
					}
					memeService.salvarMeme(meme);
				}
				attr.addFlashAttribute("success", "Planilha salva.");
			} catch (

			Exception e) {
				attr.addFlashAttribute("fail", "Não foi possível editar o resultado, tente novamente mais tarde.");
			}
		}
		return "redirect:/espiral";
	}

	@PostMapping("/compartilhar")
	public String compartilhar(HttpSession session, RedirectAttributes attr, String nome, String username, String email)
			throws Exception {

		Usuario sessaoatual = new Usuario();
		Usuario usuario = new Usuario();
		sessaoatual = (Usuario) session.getAttribute("usuarioLogado");
		if (!sessaoatual.isVisitante()) {
			String senhaAleatoria;
			senhaAleatoria = service.gerarSenhaAleatoria();
			usuario.setSenha(senhaAleatoria);
			usuario.setEmail(email);
			usuario.setNome(nome);
			usuario.setUsername(username);
			usuario.setVisitante(true);
			usuario.setUsuarioCriador(sessaoatual.getUsername());
			try {
				service.cadastrar(usuario);
				emailService.sendEmailCompartilhar(usuario, sessaoatual);
			} catch (Exception e) {
				return "error";
			}
		}
		return "dashboardIndex";
	}

	@RequestMapping("/sair")
	public String exit(HttpSession session) {
		session.removeAttribute("usuarioLogado");
		return "redirect:/";
	}

}