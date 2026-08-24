package com.example.sysfoo.controller;

import com.example.sysfoo.service.SystemInfoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class SystemInfoController {

    @Inject
    SystemInfoService systemInfoService;

    @ConfigProperty(name = "app.version")
    String appVersion; // Make sure this property is defined in application.properties

    @GET
    @Path("/system-info")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getSystemInfo() throws UnknownHostException {
        Map<String, Object> info = new HashMap<>();
        info.put("Hostname", systemInfoService.getHostname());
        info.put("IP Address", systemInfoService.getIpAddress());
        info.put("Running in Docker", systemInfoService.isRunningInDocker());
        info.put("Running in Kubernetes", systemInfoService.isRunningInKubernetes());
        info.put("App Version", systemInfoService.getAppVersion());
        return info;
    }

    @GET
    @Path("/version")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion() {
        return appVersion; // Returns the app version
    }

    @GET
    @Path("/database-info")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getDatabaseInfo() {
        return systemInfoService.getDatabaseInfo();
    }
}
