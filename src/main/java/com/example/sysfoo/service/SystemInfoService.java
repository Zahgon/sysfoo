package com.example.sysfoo.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.sql.DataSource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SystemInfoService {

    @ConfigProperty(name = "app.version")
    String appVersion;

    @Inject
    DataSource dataSource;

    public String getHostname() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public String getIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public boolean isRunningInDocker() {
        return new java.io.File("/.dockerenv").exists();
    }

    public boolean isRunningInKubernetes() {
        return System.getenv("KUBERNETES_SERVICE_HOST") != null;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public Map<String, String> getDatabaseInfo() {
        try (Connection connection = dataSource.getConnection()) {
            // This executes a simple query to check the connection
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT 1")) {
                resultSet.next();
            }
            String databaseProductName = connection.getMetaData().getDatabaseProductName();
            return Map.of(
                "status", "Connected",
                "databaseType", databaseProductName
            );
        } catch (Exception e) {
            return Map.of(
                "status", "Disconnected",
                "databaseType", "Unknown"
            );
        }
    }

}
