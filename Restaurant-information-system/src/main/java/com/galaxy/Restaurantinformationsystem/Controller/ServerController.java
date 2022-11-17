package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.Service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/server")
public class ServerController {


    private ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @ResponseBody
    @PostMapping("/update-kids")
    public String updateKids(){

        return serverService.updatekids();
    }

}
