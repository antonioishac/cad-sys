package br.com.cadsys.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public class DefaultProfileUtil {

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    public DefaultProfileUtil() {

    }

    /**
     * Caso nenhum profile estiver ativo seta o padrao DEV
     *
     * @param app
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();

        defProperties.put(SPRING_PROFILE_DEFAULT, Constants.PROFILE_DEV);
        app.setDefaultProperties(defProperties);
    }

    /**
     * Obter os profiles e aplicar
     *
     * @param env spring environment
     * @return profiles
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }

}