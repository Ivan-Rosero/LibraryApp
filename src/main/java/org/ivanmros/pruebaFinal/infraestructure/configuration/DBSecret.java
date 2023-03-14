package org.ivanmros.pruebaFinal.infraestructure.configuration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DBSecret {
    private final String url;
    private final String username;
}
