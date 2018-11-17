package br.com.cadsys;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import br.com.cadsys.config.Constants;
import br.com.cadsys.config.DefaultProfileUtil;
import br.com.cadsys.config.property.CadSysApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CadSysApiProperty.class)
public class CadSysApplication {

	private static final Logger log = LoggerFactory.getLogger(CadSysApplication.class);
	
	@Autowired
    private Environment env;
	
	@PostConstruct
	public void initApplication() {
    	Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.PROFILE_DEV) && activeProfiles.contains(Constants.PROFILE_PROD)) {
            log.error("Escolha somente um perfil para executar sua aplica\u00e7\u00e3o, DEV ou PROD");
        }
        if (activeProfiles.contains(Constants.PROFILE_DEV) && activeProfiles.contains(Constants.PROFILE_CLOUD)) {
            log.error("Escolha somente um perfil para executar sua aplica\\u00e7\\u00e3o, DEV ou CLOUD");
        }
	}

	/**
	 * M\u00e9todo principal para executar a aplica\u00e7\u00e3o
	 *
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(CadSysApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "External: \t{}://{}:{}\n\t" +
                "Database: \t{} / {} \n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            env.getProperty("server.port"),
            protocol,
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            env.getProperty("spring.datasource.url"),
            env.getProperty("spring.datasource.username"),
            env.getActiveProfiles());

        String configServerStatus = env.getProperty("configserver.status");
        log.info("\n----------------------------------------------------------\n\t" +
                "Config Server: \t{}\n----------------------------------------------------------",
            configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
	}
}
