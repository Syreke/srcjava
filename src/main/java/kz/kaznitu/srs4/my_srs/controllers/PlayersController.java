package kz.kaznitu.srs4.my_srs.controllers;

import kz.kaznitu.srs4.my_srs.models.Info;
import kz.kaznitu.srs4.my_srs.models.Player;
import kz.kaznitu.srs4.my_srs.models.Team;
import kz.kaznitu.srs4.my_srs.repositories.InfoRepository;
import kz.kaznitu.srs4.my_srs.repositories.PlayerRepository;
import kz.kaznitu.srs4.my_srs.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayersController {
    @Autowired
    private InfoRepository infoRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping("/player/{id}")
    public String player(@PathVariable("id")int id,Model model){
        model.addAttribute("player",playerRepository.findById(id).get());
        model.addAttribute("infos",infoRepository.findAll());
        model.addAttribute("teams",teamRepository.findAll());
        return "player";
    }

    @RequestMapping(value = "/players",method = RequestMethod.GET)
    public String playersList(Model model){
        model.addAttribute("players",playerRepository.findAll());
        return "players";
    }

    @RequestMapping(value = "/players",method = RequestMethod.POST)
    public String playersAdd(@RequestParam String firstname,@RequestParam String lastname, Model model){
        Player newPlayer = new Player();
        newPlayer.setFirstname(firstname);
        newPlayer.setLastname(lastname);
        playerRepository.save(newPlayer);

        model.addAttribute("player",newPlayer);
        model.addAttribute("infos",infoRepository.findAll());
        model.addAttribute("teams",teamRepository.findAll());
        return "redirect:/player/"+newPlayer.getId();
    }

    @RequestMapping(value = "player/{id}/information", method = RequestMethod.POST)
    public String playersAddInfo(@PathVariable Integer id,@RequestParam Integer infoId,
                                     @RequestParam Integer teamId, Model model){
        Info info = infoRepository.findById(infoId).get();
        Team team = teamRepository.findById(teamId).get();
        Player player = playerRepository.findById(id).get();

        if(player!=null){
            if(!player.hasInfo(info) & !player.hasTeam(team)){
                player.getInfos().add(info);
                player.getTeams().add(team);
            }
            playerRepository.save(player);
            model.addAttribute("player",playerRepository.findById(id).get());
            model.addAttribute("infos",infoRepository.findAll());
            model.addAttribute("teams",teamRepository.findAll());
            return "redirect:/player/"+player.getId();
        }
        model.addAttribute("players",playerRepository.findAll());
        return "redirect:/players";
    }

    @RequestMapping(value = "/infos",method = RequestMethod.GET)
    public String infosAdd(Model model){
        model.addAttribute("info", new Info());
        return "infos";
    }

    @RequestMapping(value = "/infos",method = RequestMethod.POST)
    public String infosAdd(@ModelAttribute Info info){
        infoRepository.save(info);
        return "infos";
    }

    @RequestMapping(value = "/teams",method = RequestMethod.GET)
    public String teamsAdd(Model model){
        model.addAttribute("team", new Team());
        return "teams";
    }

    @RequestMapping(value = "/teams",method = RequestMethod.POST)
    public String teamsAdd(@ModelAttribute Team team){
        teamRepository.save(team);
        return "teams";
    }

}
